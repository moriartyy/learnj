package org.learnj.spring.boot.web.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * @author Loster on 5/6/17.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = RestResponseSerializer.class)
public class RestResponse {

    private RestStatus status;
    private Object data;
    private RestError error;

    private RestResponse() {

    }

    public static RestResponse error(RestStatus status) {
        RestResponse response = new RestResponse();
        response.status = status;
        return response;
    }

    public static RestResponse error(RestStatus status, RestError error) {
        RestResponse response = new RestResponse();
        response.status = status;
        response.error = error;
        return response;
    }

    public static RestResponse success(RestStatus status, Object data) {
        RestResponse response = new RestResponse();
        response.status = status;
        response.data = data;
        return response;
    }


}
