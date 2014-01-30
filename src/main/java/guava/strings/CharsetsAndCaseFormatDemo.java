package guava.strings;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 14:52
 * Charsets and CaseFormat.
 */
public class CharsetsAndCaseFormatDemo {
    public static void main(String[] args) {
        // Charsets provides constant references to the six standard Charset implementations guaranteed to be supported
        // by all Java platform implementations. Use them instead of referring to charsets by their names.
        byte[] bytes = "abc".getBytes(Charsets.UTF_8);
        System.out.println(bytes[1]);

        // CaseFormat
        // naming conventions for programming languages
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "CONSTANT_NAME"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, "CONSTANT_NAME"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "CONSTANT_NAME"));
    }
}
