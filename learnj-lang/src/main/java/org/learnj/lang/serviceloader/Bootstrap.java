package org.learnj.lang.serviceloader;

import java.util.ServiceLoader;

/**
 * @author hongmiao.yu on 2016/8/1.
 */
public class Bootstrap {

    public static void main(String[] args) {
        ServiceLoader<PrintService> loader = ServiceLoader.load(PrintService.class);
        for (PrintService printService : loader) {
            printService.print();
        }
    }
}
