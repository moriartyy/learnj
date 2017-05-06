package org.learnj.spring.boot.web.rest.rule;

import org.learnj.spring.boot.web.rest.RestError;
import org.learnj.spring.boot.web.rest.exception.ValidationException;

/**
 * @author Loster on 5/6/17.
 */
public class Validator {

    public static void NotEmpty(String target, String message) {
        NotEmpty(target, RestError.of(message));
    }

    public static void NotEmpty(String target, RestError error) {
        if (target == null || target.length() == 0) {
            throw new ValidationException(error);
        }
    }
}
