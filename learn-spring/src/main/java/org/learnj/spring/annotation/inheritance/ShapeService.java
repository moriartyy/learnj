package org.learnj.spring.annotation.inheritance;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hongmiao.yu 2016/7/4
 */
public abstract class ShapeService {

    @Autowired
    protected Config config;
}
