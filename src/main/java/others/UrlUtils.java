package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {
    public static String getUrl(String urlString) {
        StringBuffer result = new StringBuffer();
        BufferedReader in = null;
        try {
            URL url = new URL(urlString);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                result.append(str);
            }
        } catch (MalformedURLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "";
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "";
        } finally {
            try {
                if (null != in)
                    in.close();
            } catch (IOException e) {
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(getUrl("http://www.baidu.com/"));
    }
}
