
package com.hdw.system.config.json.jackson.serializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hdw.common.utils.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * Jackson Date反序列化器
 * </p>
 *
 * @author TuMingLong
 * @date 2018-11-08
 */
public class JacksonDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String string = null;
        if (date != null) {
            string = DateUtils.formatDate(date,"yyyy-MM-dd HH:mm:ss");
        }
        jsonGenerator.writeString(string);
    }
}
