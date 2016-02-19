package others;

import java.lang.reflect.Field;

/**
 * JAVA STRING IS FINAL AND IMMUTABLE. YET, ITS CONTENTS CAN BE MODIFIED.
 * https://cupofjavasite.wordpress.com/2016/01/09/java-string-is-final-and-immutable-yet-its-contents-can-be-modified/
 */
public class StringModifier {
    public static void main(String[] str){
        try {
            String test="aaaa";
            String test2 =test;
            String test3 = new String(test);
            String test4 = new String(test.toCharArray());
            Field values = String.class.getDeclaredField("value");
 
            values.setAccessible(true);
            char[] ref = (char [])values.get(test);
            ref[0] = 'b';
 
            System.out.println("aaaa"+test+" "+test2+" "+test3+" "+test4);
        } catch (NoSuchFieldException|SecurityException|
            IllegalArgumentException|IllegalAccessException ex) {
        }
    }
}