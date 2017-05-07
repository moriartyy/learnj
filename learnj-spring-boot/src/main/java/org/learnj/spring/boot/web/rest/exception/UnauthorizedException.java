package org.learnj.spring.boot.web.rest.exception;

import org.learnj.spring.boot.web.rest.RestError;
import org.learnj.spring.boot.web.rest.RestErrors;
import org.learnj.spring.boot.web.rest.RestException;
import org.learnj.spring.boot.web.rest.RestStatus;

/**
 * @author Loster on 5/7/17.
 */
public class UnauthorizedException extends RestException {

    public UnauthorizedException(RestError error) {
        super(RestStatus.UNAUTHORIZED, error);
    }

    public UnauthorizedException(String message) {
        this(RestErrors.of(message));
    }

    public UnauthorizedException(int code, String message) {
        this(RestErrors.of(code, message));
    }
}
