package org.learnj.lang.serviceloader;

/**
 * @author hongmiao.yu on 2016/8/1.
 */
public class PrintWorldService implements PrintService {
    @Override
    public void print() {
        System.out.println("world");
    }
}
