package org.learnj.spring.boot.web.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Loster on 5/4/17.
 */
@SpringBootApplication
public class Bootstrap {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Bootstrap.class);
//        SpringApplication application = new WebApplication(Bootstrap.class, new String[0]);
//        application.run();
    }

}
