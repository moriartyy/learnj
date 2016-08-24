package org.learnj.nio.tcp;

/**
 * @author hongmiao.yu on 2016/8/9.
 */
public class Boot {

    public static void main(String[] args) {
        byte a = (byte)0x80;
        byte b = ~0x7f;
        System.out.println(a == b);
        print((byte)3);
    }

    static void print(byte b) {
        System.out.println(b);
    }
}
