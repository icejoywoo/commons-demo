
public abstract class TemplateClass {
    public abstract void before();

    public abstract void process();

    public abstract void after();

    public void run() {
        System.out.println("running...");
        this.before();
        System.out.println("processing...");
        this.process();
        System.out.println("processing finished");
        this.after();
        System.out.println("finished");
    }

    public static class Test extends TemplateClass {
        private int a;
        private int b;
        private int result;

        @Override
        public void before() {
            System.out.println("\tinit");
            a = 1;
            b = 3;
        }

        @Override
        public void process() {
            System.out.println("\tprocess");
            result = a + b;
        }

        @Override
        public void after() {
            System.out.println("\tresult = " + result);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.run();
    }
}
