package org.learnj.spring.boot.web.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Loster on 5/7/17.
 */
public class RestErrorSerializer extends JsonSerializer<RestError> {

    @Override
    public void serialize(RestError error, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (error instanceof CodedRestError) {
            CodedRestError codedError = (CodedRestError)error;
            gen.writeStartObject();
            gen.writeNumberField("code", codedError.getCode());
            gen.writeStringField("message", codedError.getMessage());
            gen.writeEndObject();
        } else {
            gen.writeString(error.getMessage());
        }
    }
}
