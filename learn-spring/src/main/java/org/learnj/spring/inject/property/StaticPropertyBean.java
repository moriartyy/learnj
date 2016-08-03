package org.learnj.spring.inject.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hongmiao.yu 2016/6/29
 */
@Component
public class StaticPropertyBean {

    static Injectee staticSetterInjectee;
    static Injectee instanceSetterInjectee;

    public String getInstanceInjecteeName() {
        return instanceInjecteeName;
    }

    public static String getStaticInjecteeName() {
        return staticInjecteeName;
    }

    private static String staticInjecteeName = Injectee.INSTANCE.name();

    private String instanceInjecteeName = Injectee.INSTANCE.name();

    public static Injectee getStaticSetterInjectee() {
        return staticSetterInjectee;
    }

    public static void setStaticSetterInjectee(Injectee staticSetterInjectee) {
        StaticPropertyBean.staticSetterInjectee = staticSetterInjectee;
    }

    public Injectee getInstanceInjectee() {
        return instanceSetterInjectee;
    }

    @Autowired
    public void setInstanceInjectee(Injectee instanceInjectee) {
        StaticPropertyBean.instanceSetterInjectee = instanceInjectee;
    }

    public boolean isStaticPropertyInjected() {
        return staticSetterInjectee != null;
    }

    public boolean isInstancePropertyInjected() {
        return instanceSetterInjectee != null;
    }
}
