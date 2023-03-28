package com.pradeep.tinyurlservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashMapConverterToJSON implements AttributeConverter<Map<String, Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> hashMap) {
        ObjectMapper objectMapper=new ObjectMapper();
        String details = null;
        try {
            details = objectMapper.writeValueAsString(hashMap);
        } catch (final JsonProcessingException e) {
            //logger.error("JSON writing error", e);
        }
        return details;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String jsonData) {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> details = null;
        try {
            details = objectMapper.readValue(jsonData, new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {
            //logger.error("JSON reading error", e);
        }
        return details;
    }
}
