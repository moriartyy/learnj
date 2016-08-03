package org.learnj.dubbo.consumer;

import java.io.IOException;

/**
 * Created by hongmiao.yu on 2015/12/7.
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        String s = "LSJDAAsdfsdflsd";
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            System.out.println(b + " = " + (b & 0xff));
        }

    }
}
