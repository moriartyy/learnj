package org.learnj.spring.boot.web;

import org.learnj.spring.boot.web.rest.Bootstrap;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Loster on 5/4/17.
 */
public class TestSupport {

//    static {
//    }


    @SpringBootApplication(exclude = Bootstrap.class)
    class TestBootstrap {

    }
}
