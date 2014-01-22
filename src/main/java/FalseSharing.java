public final class FalseSharing implements Runnable {
	public static int NUM_THREADS = 4; // change
	public final static long ITERATIONS = 500L * 1000L * 1000L;
	private final int arrayIndex;
	private static VolatileLong[] longs;

	public FalseSharing(final int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public static void main(final String[] args) throws Exception {
		Thread.sleep(10000);
		System.out.println("starting....");
		if (args.length == 1) {
			NUM_THREADS = Integer.parseInt(args[0]);
		}

		longs = new VolatileLong[NUM_THREADS];
		for (int i = 0; i < longs.length; i++) {
			longs[i] = new VolatileLong();
		}
		final long start = System.nanoTime();
		runTest();
		System.out.println("duration = " + (System.nanoTime() - start));
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseSharing(i));
		}
		for (Thread t : threads) {
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}

	// Java程序的对象头固定占8字节(32位系统)或12字节(64位系统默认开启压缩, 不开压缩为16字节)
	public final static class VolatileLong {
		public volatile long value = 0L;
		public long p1, p2, p3, p4, p5, p6; // 注释这行与不注释做对比, 这块用于补齐, 防止对象在同一个cpu cache的缓存行
		// 注释后 duration = 36,106,177,609
		// 不注释 duration = 20,778,606,586
	}
}
