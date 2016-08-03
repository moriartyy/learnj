package org.learnj.lang.serviceloader;

/**
 * @author hongmiao.yu on 2016/8/1.
 */
public class PrintHelloService implements PrintService {
    @Override
    public void print() {
        System.out.println("hello");
    }
}
