package org.learnj.spring.inject.sequence;

import org.learnj.spring.inject.property.Bootstrap;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hongmiao.yu on 2016/7/22.
 */
public class Sequence {

    public static void main(String[] args) {
        String config = Sequence.class.getPackage().getName().replace('.', '/') + "/application.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        SubClass subClass = context.getBean(SubClass.class);
        subClass.Hello();
    }
}
