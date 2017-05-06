package org.learnj.spring.inject.property;

import org.springframework.stereotype.Component;

/**
 * @author hongmiao.yu 2016/6/29
 */
@Component
public class Injectee {

    private static Injectee INSTANCE;

    static Injectee getINSTANCE() {
        return INSTANCE;
    }

    static void setINSTANCE(Injectee instance) {
        INSTANCE = instance;
    }

    public Injectee() {
        setINSTANCE(this);
    }

    public String name() {
        return "I am an injectee.";
    }
}
