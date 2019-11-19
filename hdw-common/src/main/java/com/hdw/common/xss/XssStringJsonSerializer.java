package com.hdw.common.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hdw.common.utils.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * @Description Jackson响应参数字符串转义处理
 * @Author TuMinglong
 * @Date 2018/12/10 14:41
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(StringEscapeUtils.escapeHtml4(s));
    }

}