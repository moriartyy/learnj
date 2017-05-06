package org.learnj.spring.boot.web.rest;

import lombok.Getter;

/**
 * @author Loster on 5/6/17.
 */
@Getter
public class RestException extends RuntimeException {

    private final RestStatus status;
    private final RestError error;

    public RestException(RestStatus status, RestError error) {
        this.status = status;
        this.error = error;
    }

}
