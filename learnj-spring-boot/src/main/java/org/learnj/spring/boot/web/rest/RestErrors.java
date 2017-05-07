package org.learnj.spring.boot.web.rest;

/**
 * @author Loster on 5/7/17.
 */
public class RestErrors {

    public static RestError of(String message) {
        return () -> message;
    }

    public static CodedRestError of(int code, String message) {
        return new CodedRestError() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }
}
