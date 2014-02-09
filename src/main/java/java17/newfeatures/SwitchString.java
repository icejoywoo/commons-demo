package java17.newfeatures;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-9
 * Time: 16:36
 * jdk1.7 new feature: switch string.
 */
public class SwitchString {
    public static void main(String[] args) {
        String type = "a";
        switch (type) {
            case "a":
                System.out.println("a");
                break;
            case "b":
                System.out.println("b");
                break;
            default:
                System.out.println("unknown");
        }
    }
}
