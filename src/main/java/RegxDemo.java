import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxDemo {
    static String HtmlFilter(String str) {
        Pattern pattern = Pattern.compile("(<([^>]*)>|&nbsp;)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String test = "<script>&nbsp;</script><h>test&nbsp;<h1>&nbsp;";
        System.out.println(HtmlFilter(test));
    }
}
