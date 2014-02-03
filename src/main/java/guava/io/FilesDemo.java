package guava.io;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-3
 * Time: 12:12
 * Guava io files demo.
 */
public class FilesDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("./src/main/resources/test.ftl");
        // Read the lines of a UTF-8 text file
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        System.out.println(Joiner.on('\n').join(lines));

        // Count distinct word occurrences in a file
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        for (String word : wordOccurrences) {
            System.out.println(word);
        }

        // SHA-1 a file
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        System.out.println(hash);

        // Copy the data from a URL to a file
        URL url = new URL("http://www.baidu.com");
        Resources.asByteSource(url).copyTo(Files.asByteSink(new File("src/main/resources/test.html")));

        String result = Files.asCharSource(new File("src/main/resources/test.html"), Charsets.UTF_8).readLines(new LineProcessor<String>() {

            private List<String> lines = new ArrayList<String>();

            @Override
            public boolean processLine(String line) throws IOException {
                for (String word : Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().trimResults().split(line))
                    lines.add(word);
                return true;
            }

            @Override
            public String getResult() {
                return Joiner.on('\n').join(lines);
            }
        });
        System.out.println(result);
    }
}
