package org.learnj.spring.boot.web.rest.exception;

import org.learnj.spring.boot.web.rest.RestError;
import org.learnj.spring.boot.web.rest.RestErrors;
import org.learnj.spring.boot.web.rest.RestException;
import org.learnj.spring.boot.web.rest.RestStatus;

/**
 * @author Loster on 5/5/17.
 */
public class ValidationException extends RestException {

    public ValidationException(RestError error) {
        super(RestStatus.BAD_REQUEST, error);
    }

    public ValidationException(String message) {
        this(RestErrors.of(message));
    }

    public ValidationException(int code, String message) {
        this(RestErrors.of(code, message));
    }
}
