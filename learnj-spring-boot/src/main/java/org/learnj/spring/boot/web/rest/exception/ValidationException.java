package org.learnj.spring.boot.web.rest.exception;

import org.learnj.spring.boot.web.rest.RestError;
import org.learnj.spring.boot.web.rest.RestException;
import org.learnj.spring.boot.web.rest.RestStatus;

/**
 * @author Loster on 5/5/17.
 */
public class ValidationException extends RestException {

    public ValidationException(RestError error) {
        super(RestStatus.VALIDATION_FAILED, error);
    }

    public ValidationException(String message) {
        this(RestError.of(message));
    }

    public ValidationException(int code, String message) {
        this(RestError.of(code, message));
    }
}
