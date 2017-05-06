package org.learnj.spring.boot.web.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

/**
 * @author Loster on 5/6/17.
 */
@JsonSerialize(using = RestError.RestErrorSerializer.class)
public interface RestError {

    String getMessage();

    class RestErrorSerializer extends JsonSerializer<RestError> {

        @Override
        public void serialize(RestError value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.getMessage());
        }
    }

    static RestError of(String message) {
        return () -> message;
    }

    static CodedRestError of(int code, String message) {
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

    @JsonSerialize(using = JsonSerializer.None.class)
    interface CodedRestError extends RestError {
        int getCode();
    }
}
