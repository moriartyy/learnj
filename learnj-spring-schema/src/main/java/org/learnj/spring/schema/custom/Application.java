package org.learnj.spring.schema.custom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Loster on 2016/8/24.
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        NodeConfig p = (NodeConfig)ctx.getBean("node");
        System.out.println(p.getId());
    }
}
