package guava.io;

import com.google.common.io.Closer;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-3
 * Time: 13:42
 * Guava closing Resources.
 */
public class ClosingResources {
    public static void main(String[] args) throws IOException {
        // jdk1.7 try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/test.ftl"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        // guava closer
        Closer closer = Closer.create();
        try {
            InputStream in = closer.register(new FileInputStream("./src/main/resources/test.ftl"));
            OutputStream out = closer.register(new FileOutputStream("./src/main/resources/test.output"));
            // do stuff with in and out
        } catch (Throwable e) { // must catch Throwable
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }
}
