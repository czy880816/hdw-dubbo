package com.hdw.system.config.json.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hdw.system.config.converter.StringToDoubleUtil;

import java.io.IOException;

/**
 * @author TuMingLong
 * @date 2018-11-08
 */
public class JacksonDoubleDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String string = jsonParser.getText();
        return StringToDoubleUtil.convert(string);
    }
}
