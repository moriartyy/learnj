package org.learnj.spring.annotation.inheritance;

import org.springframework.stereotype.Component;

/**
 * @author hongmiao.yu 2016/7/4
 */
@Component
public class Config {
    public String name() {
        return "Hello";
    }

    public String getName() {
        return "Hello";
    }
}