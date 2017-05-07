package org.learnj.spring.boot.web.rest.error;

import lombok.Getter;

/**
 * @author Loster on 5/7/17.
 */
@Getter
public enum  CardError {

    MISSING_PARAMETER(10001, "Missing parameter"),
    ENTITY_NOT_EXIST(10002, "Entity not exist"),
            ;

    private final String description;
    private final int code;

    CardError(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
