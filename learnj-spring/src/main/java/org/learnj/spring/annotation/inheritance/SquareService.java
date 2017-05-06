package org.learnj.spring.annotation.inheritance;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author hongmiao.yu 2016/7/4
 */
@Component
public class SquareService extends ShapeService {

    private String name;

    @PostConstruct
    public void init() {
        this.name = config.name();
    }

    public String getName() {
        return name;
    }
}
