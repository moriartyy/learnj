package org.learnj.spring.boot.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.learnj.spring.boot.web.rest.error.CardError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Loster on 5/5/17.
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(RestException.class)
    public Object handleRestException(RestException ex) {
        return RestResponse.error(ex.getStatus(), ex.getError());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return RestResponse.error(RestStatus.BAD_REQUEST, RestErrors.of(CardError.MISSING_PARAMETER.getCode(),
                e.getParameterName()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex) {
        return RestResponse.error(RestStatus.INTERNAL_ERROR);
    }
}
