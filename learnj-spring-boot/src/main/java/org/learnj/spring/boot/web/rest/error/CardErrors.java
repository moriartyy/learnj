package org.learnj.spring.boot.web.rest.error;

import org.learnj.spring.boot.web.rest.RestError;

import static org.learnj.spring.boot.web.rest.RestErrors.of;

/**
 * @author Loster on 5/7/17.
 */
public class CardErrors {

    public static final RestError EmptyName = of(10001, "Card name cat not be empty");
    public static final RestError EmptyNumber = of(10002, "Card number cat not be empty");
    public static final RestError NotExist = of(10003, "Card not exist");
}
