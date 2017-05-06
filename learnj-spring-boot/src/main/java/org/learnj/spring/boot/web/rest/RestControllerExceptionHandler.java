package org.learnj.spring.boot.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Loster on 5/5/17.
 */
@ControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(RestException.class)
    public Object handleRestException(RestException ex) {
        return RestResponse.error(ex.getStatus(), ex.getError());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex) {
        return RestResponse.error(RestStatus.INTERNAL_ERROR);
    }
}
