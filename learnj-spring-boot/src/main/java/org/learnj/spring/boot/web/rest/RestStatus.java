package org.learnj.spring.boot.web.rest;

import lombok.Getter;

/**
 * @author Loster on 5/6/17.
 */
@Getter
public enum RestStatus {

    OK(200, "OK"),
    CREATED(201, "Created"),
    NOT_FOUND(404, "Not Found"),
    VALIDATION_FAILED(422, "Validation Failed"),
    INTERNAL_ERROR(500, "Internal Error"),;


    private int code;
    private String description;

    RestStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
