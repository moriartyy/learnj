package org.learnj.spring.inject.singleton;

import org.learnj.spring.inject.property.StaticPropertyBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hongmiao.yu 2016/6/29
 */
public class Application {

    public static void main(String[] args) {

        String config = Application.class.getPackage().getName().replace('.', '/') + "/application.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        StaticSingletonBean instance = StaticSingletonBean.getInstance();

        System.out.println(instance.getName());


        context.close();
    }
}
