package org.learnj.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by hongmiao.yu on 2015/12/1.
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationProvider.xml");
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
