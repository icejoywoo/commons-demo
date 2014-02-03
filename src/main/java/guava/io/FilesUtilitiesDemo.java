package guava.io;

import com.google.common.collect.TreeTraverser;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-3
 * Time: 13:12
 * Guava Files Utilities.
 */
public class FilesUtilitiesDemo {
    public static void main(String[] args) throws IOException {
        // parent dirs: /tmp/test1/2/
        Files.createParentDirs(new File("/tmp/test1/2/3"));
        Files.createParentDirs(new File("/tmp/test1/3/3"));

        System.out.println(Files.getFileExtension("test.tar.gz"));
        System.out.println(Files.getNameWithoutExtension("test.tar.gz"));
        System.out.println(Files.simplifyPath("/tmp/1/2/3/../4"));

        TreeTraverser<File> traverser = Files.fileTreeTraverser();
        for (File f : traverser.children(new File("/tmp/"))) {
            System.out.println(f);
        }
    }
}
