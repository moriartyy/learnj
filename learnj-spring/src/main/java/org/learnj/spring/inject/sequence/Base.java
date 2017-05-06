package org.learnj.spring.inject.sequence;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hongmiao.yu on 2016/7/22.
 */
public class Base {


    @Autowired
    protected Injectee injectee;
}
