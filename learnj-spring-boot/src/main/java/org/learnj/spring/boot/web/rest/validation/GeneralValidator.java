package org.learnj.spring.boot.web.rest.validation;

import org.learnj.spring.boot.web.rest.RestError;
import org.learnj.spring.boot.web.rest.exception.MissingParameterException;
import org.learnj.spring.boot.web.rest.exception.ValidationException;

import static org.learnj.spring.boot.web.rest.RestErrors.of;

/**
 * @author Loster on 5/6/17.
 */
public class GeneralValidator {

    public static String checkNotEmpty(String parameterName, String parameterValue) {
        if (parameterValue == null || parameterValue.length() == 0) {
            throw new MissingParameterException(parameterName);
        }
        return parameterValue;
    }

    public static void checkExpression(boolean expression, String errorMessage) {
        checkExpression(expression, of(errorMessage));
    }

    public static void checkExpression(boolean expression, int errorCode, String errorMessage) {
        checkExpression(expression, of(errorCode, errorMessage));
    }

    public static void checkExpression(boolean expression, RestError error) {
        if (!expression) {
            throw new ValidationException(error);
        }
    }
}
