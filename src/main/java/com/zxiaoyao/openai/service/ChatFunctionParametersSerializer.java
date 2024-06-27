package com.zxiaoyao.openai.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kjetland.jackson.jsonSchema.JsonSchemaConfig;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;

import java.io.IOException;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 10:17 2023/10/9
 * @Version 1.0
 */
public class ChatFunctionParametersSerializer extends JsonSerializer<Class<?>> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonSchemaConfig config = JsonSchemaConfig.vanillaJsonSchemaDraft4();
    private final JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(mapper, config);

    @Override
    public void serialize(Class<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            try {
                JsonNode schema = jsonSchemaGenerator.generateJsonSchema(value);
                gen.writeObject(schema);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate JSON Schema", e);
            }
        }
    }
}
