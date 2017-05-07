package org.learnj.spring.boot.web.rest;

/**
 * @author Loster on 5/7/17.
 */
public interface CodedRestError extends RestError {
    int getCode();
}
