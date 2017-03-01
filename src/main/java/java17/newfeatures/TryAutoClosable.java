package java17.newfeatures;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-9
 * Time: 16:37
 * jdk1.7 new feature: try auto-closable resource.
 */
public class TryAutoClosable {

    static class MyFileOutputStream extends FileOutputStream {

        public MyFileOutputStream(String name) throws FileNotFoundException {
            super(name);
        }

        public void close() throws IOException {
            super.close();
            System.err.println("close method is invoked.");
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/test.ftl"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        try (OutputStream out = new MyFileOutputStream("/tmp/test")){
            out.write("そら".getBytes());
            throw new IOException();
        }
    }
}
