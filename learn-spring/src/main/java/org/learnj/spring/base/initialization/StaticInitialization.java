package org.learnj.spring.base.initialization;

/**
 * @author hongmiao.yu 2016/6/30
 */
public class StaticInitialization {

    private static String p1 = "By def.";

    static {
        p1 = "By static.";
    }

    public static void main(String[] args) {
        System.out.println(StaticInitialization.p1);
    }
}
