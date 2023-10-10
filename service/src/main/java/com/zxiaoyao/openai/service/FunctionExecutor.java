package com.zxiaoyao.openai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.zxiaoyao.openai.api.completion.chat.ChatFunction;
import com.zxiaoyao.openai.api.completion.chat.ChatFunctionCall;
import com.zxiaoyao.openai.api.completion.chat.ChatMessage;
import com.zxiaoyao.openai.api.completion.chat.ChatMessageRole;

import java.util.*;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 10:49 2023/10/9
 * @Version 1.0
 */
public class FunctionExecutor {

    private ObjectMapper MAPPER = new ObjectMapper();
    private final Map<String, ChatFunction> FUNCTIONS = new HashMap<>();

    public FunctionExecutor(List<ChatFunction> functions) {
        setFunctions(functions);
    }

    public FunctionExecutor(List<ChatFunction> functions, ObjectMapper objectMapper) {
        setFunctions(functions);
        setObjectMapper(objectMapper);
    }

    public Optional<ChatMessage> executeAndConvertToMessageSafely(ChatFunctionCall call) {
        try {
            return Optional.ofNullable(executeAndConvertToMessage(call));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public ChatMessage executeAndConvertToMessageHandlingExceptions(ChatFunctionCall call) {
        try {
            return executeAndConvertToMessage(call);
        } catch (Exception exception) {
            exception.printStackTrace();
            return convertExceptionToMessage(exception);
        }
    }

    public ChatMessage convertExceptionToMessage(Exception exception) {
        String error = exception.getMessage() == null ? exception.toString() : exception.getMessage();
        return new ChatMessage(ChatMessageRole.FUNCTION.value(), "{\"error\": \"" + error + "\"}", "error");
    }

    public ChatMessage executeAndConvertToMessage(ChatFunctionCall call) {
        return new ChatMessage(ChatMessageRole.FUNCTION.value(), executeAndConvertToJson(call).toPrettyString(), call.getName());
    }

    public JsonNode executeAndConvertToJson(ChatFunctionCall call) {
        try {
            Object execution = execute(call);
            if (execution instanceof TextNode) {
                JsonNode objectNode = MAPPER.readTree(((TextNode) execution).asText());
                if (objectNode.isMissingNode()) {
                    return (JsonNode) execution;
                }
                return objectNode;
            }
            if (execution instanceof ObjectNode) {
                return (JsonNode) execution;
            }
            if (execution instanceof String) {
                JsonNode objectNode = MAPPER.readTree((String) execution);
                if (objectNode.isMissingNode()) {
                    throw new RuntimeException("Parsing exception");
                }

                return objectNode;
            }
            return MAPPER.readValue(MAPPER.writeValueAsString(execution), JsonNode.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(ChatFunctionCall call) {
        ChatFunction function = FUNCTIONS.get(call.getName());
        Object obj;
        try {
            JsonNode arguments = call.getArguments();
            obj = MAPPER.readValue(arguments instanceof TextNode ? arguments.asText() : arguments.toPrettyString(), function.getParametersClass());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return (T) function.getExecutor().apply(obj);
    }

    public List<ChatFunction> getFunctions() {
        return new ArrayList<>(FUNCTIONS.values());
    }

    public void setFunctions(List<ChatFunction> functions) {
        this.FUNCTIONS.clear();
        functions.forEach(f -> this.FUNCTIONS.put(f.getName(), f));
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.MAPPER = objectMapper;
    }
}
