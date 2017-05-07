package org.learnj.spring.boot.web.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Loster on 5/7/17.
 */
public class RestResponseSerializer extends JsonSerializer<RestResponse> {

    @Override
    public void serialize(RestResponse response, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeNumberField("status", response.getStatus().getCode());
        gen.writeStringField("message", response.getStatus().getDescription());
        if (response.getData() != null) {
            gen.writeObjectField("data", response.getData());
        } else if (response.getError() != null) {
            gen.writeObjectField("error", response.getError());
        }
        gen.writeEndObject();
    }
}
