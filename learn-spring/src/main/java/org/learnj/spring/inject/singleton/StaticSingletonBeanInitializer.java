package org.learnj.spring.inject.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Loster on 8/26/16.
 */
@Component
public class StaticSingletonBeanInitializer {

    @Autowired
    public void setInstance(StaticSingletonBean instance) {
        StaticSingletonBean.setInstance(instance);
    }

}
