package org.learnj.lang;

/**
 * @author hongmiao.yu on 2016/8/2.
 */
public class Characters {
    public static String str = "中国a汉";

    public static void main(String[] args) {
        System.out.printf("str.length(): %d\n", str.length());
        System.out.printf("str.codePointCount(0, 1): %d\n", str.codePointCount(0, 1));
        System.out.printf("str.codePointCount(0, 2): %d\n", str.codePointCount(0, 2));
        System.out.printf("str.codePointCount(0, 3): %d\n", str.codePointCount(0, 3));
        System.out.printf("str.codePointCount(0, str.length()): %d\n", str.codePointCount(0, str.length()));
        System.out.printf("str: %s\n", str);
        System.out.printf("str.charAt(0): %c\n", str.charAt(0));
        System.out.printf("str.codePointAt(0): %d\n", str.codePointAt(0));
        System.out.printf("str.codePointAt(1): %d\n", str.codePointAt(1));
    }
}
