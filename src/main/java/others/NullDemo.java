package others;

import java.io.UnsupportedEncodingException;


public class NullDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = null;
        System.out.println(str + " test");
        // System.out.println(str.toString());

        String result = (str == null) ? "null" : str.toString();
        System.out.println(result);

        String test = "abcdef";
        System.out.println(test.length());
        System.out.println(test.substring(0, test.length() - 1));

        String a = "%E6%88%91%E8%BF%98%E6%98%AF%E4%B8%80%E4%B8%AA%E5%A5%BD%E4%BA%BA";
        System.out.println(java.net.URLDecoder.decode(a, "UTF-8"));
        String b = "test中文测试";
        System.out.println(java.net.URLEncoder.encode(a) == a);
    }
}
