package org.learnj.spring.inject.property;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hongmiao.yu 2016/6/29
 */
public class Bootstrap {

    public static void main(String[] args) {

        String config = Bootstrap.class.getPackage().getName().replace('.', '/') + "/application.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        System.out.println("getStaticInjecteeName: " + StaticPropertyBean.getStaticInjecteeName());
        StaticPropertyBean staticPropertyBean = context.getBean(StaticPropertyBean.class);
        System.out.println("getInstanceInjecteeName: " + staticPropertyBean.getInstanceInjecteeName());
        System.out.println("isStaticPropertyInjected: " + staticPropertyBean.isStaticPropertyInjected());
        System.out.println("isInstancePropertyInjected: " + staticPropertyBean.isInstancePropertyInjected());
        System.out.println(staticPropertyBean.getInstanceInjectee().name());

        context.close();
    }
}
