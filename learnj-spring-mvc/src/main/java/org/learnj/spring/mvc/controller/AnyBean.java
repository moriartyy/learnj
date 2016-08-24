package org.learnj.spring.mvc.controller;

import org.springframework.stereotype.Component;

/**
 * @author Loster on 2016/8/22.
 */
@Component
public class AnyBean {

    public String name() {
        return getClass().getSimpleName();
    }
}
