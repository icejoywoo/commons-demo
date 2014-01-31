package guava.networking;

import com.google.common.net.InternetDomainName;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-31
 * Time: 22:00
 * Guava networking InternetDomainName.
 */
public class InternetDomainNameDemo {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("mail.google.com", "news.baidu.com", "www.foo.uk.co", "coolshell.cn");
        for (String url : urls) {
            System.out.println("url: " + url);
            InternetDomainName privateDomain = InternetDomainName.from(url).topPrivateDomain();
            System.out.println(privateDomain + "\t" + privateDomain.isTopPrivateDomain());
            if (InternetDomainName.from(url).hasPublicSuffix()) {
                InternetDomainName publicSuffix = InternetDomainName.from(url).publicSuffix();
                System.out.println(publicSuffix + "\t" + publicSuffix.isPublicSuffix());
            }
        }
    }
}
