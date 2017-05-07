package org.learnj.spring.boot.web.rest.exception;

import org.learnj.spring.boot.web.rest.RestErrors;

/**
 * @author Loster on 5/7/17.
 */
public class InvalidParameterException extends ValidationException {

    public InvalidParameterException(String message) {
        super(RestErrors.of(message));
    }

    public InvalidParameterException(int errorCode, String errorMessage) {
        super(RestErrors.of(errorCode, errorMessage));
    }
}
