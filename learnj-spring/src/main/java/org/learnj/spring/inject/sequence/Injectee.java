package org.learnj.spring.inject.sequence;

import org.springframework.stereotype.Component;

/**
 * @author hongmiao.yu 2016/6/29
 */
@Component
public class Injectee {

    public static Injectee INSTANCE;

    public Injectee() {
        INSTANCE = this;
    }

    public String name() {
        return "I am an injectee.";
    }
}
