package com.runbean.springboot01.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * 前端向后端提交的参数，使用 Deserializer  ，在其中处理转义逻辑
 */
public class XssStringJsonDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        System.out.println("进入 XssStringJsonDeserializer ...");
        if (jsonParser != null) {
            String encodedValue = HtmlUtils.htmlEscape(jsonParser.getValueAsString());
           return encodedValue;
        }
        return null;
    }
}
