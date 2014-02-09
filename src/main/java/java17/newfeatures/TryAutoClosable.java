package java17.newfeatures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-9
 * Time: 16:37
 * jdk1.7 new feature: try auto-closable resource.
 */
public class TryAutoClosable {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/test.ftl"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
