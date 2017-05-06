package org.learnj.spring.boot.web.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * @author Loster on 5/6/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private RestStatus status;
    @Getter
    private T data;
    @Getter
    private RestError error;

    public int getStatus() {
        return status.getCode();
    }

    public String getMessage() {
        return status.getDescription();
    }

    public static <T> RestResponse<T> error(RestStatus status) {
        RestResponse<T> response = new RestResponse<>();
        response.status = status;
        return response;
    }

    public static <T> RestResponse<T> error(RestStatus status, RestError error) {
        RestResponse<T> response = new RestResponse<>();
        response.status = status;
        response.error = error;
        return response;
    }

    public static <T> RestResponse<T> success(RestStatus status, T data) {
        RestResponse<T> response = new RestResponse<>();
        response.status = status;
        response.data = data;
        return response;
    }

}
