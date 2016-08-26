package org.learnj.spring.inject.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hongmiao.yu on 2016/7/22.
 */
@Component
public class SubClass extends Base {

    public SubClass() {
        System.out.println(injectee.name());
    }

    public void hello() {
        System.out.println("heelo");
    }
}
