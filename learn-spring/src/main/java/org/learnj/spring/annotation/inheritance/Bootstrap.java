package org.learnj.spring.annotation.inheritance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hongmiao.yu 2016/7/4
 */
public class Bootstrap {

    public static void main(String[] args) {

        double d1 = 2342.234234234;
        System.out.println((long)d1);

        String config = Bootstrap.class.getPackage().getName().replace('.', '/') + "/application.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        SquareService squareService = context.getBean(SquareService.class);

        System.out.println(squareService.getName());

        context.close();
    }
}
