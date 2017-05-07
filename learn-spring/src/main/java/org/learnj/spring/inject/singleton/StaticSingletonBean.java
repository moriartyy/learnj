package org.learnj.spring.inject.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Loster on 8/25/16.
 */
@Component
public class StaticSingletonBean {

    private String name;

    public void setFullName(String fullname) {
        this.name = fullname;
    }

    public String getName() {
        return name;
    }

    private static StaticSingletonBean instance;

    public static StaticSingletonBean getInstance() {
        return instance;
    }

    protected static void setInstance(StaticSingletonBean instance) {
        StaticSingletonBean.instance = instance;
    }


}
