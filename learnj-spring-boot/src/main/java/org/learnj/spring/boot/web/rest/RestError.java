package org.learnj.spring.boot.web.rest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Loster on 5/6/17.
 */
@JsonSerialize(using = RestErrorSerializer.class)
public interface RestError {

    String getMessage();

}
