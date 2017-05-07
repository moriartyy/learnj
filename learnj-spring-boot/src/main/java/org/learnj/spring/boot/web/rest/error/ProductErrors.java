package org.learnj.spring.boot.web.rest.error;

import org.learnj.spring.boot.web.rest.RestError;

import static org.learnj.spring.boot.web.rest.RestErrors.of;

/**
 * @author Loster on 5/6/17.
 */
public class ProductErrors {

    public static final RestError EmptyName = of(100, "Product name cat not be empty");

}
