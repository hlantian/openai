package com.zxiaoyao.openai.api.completion.chat;

/**
 * @Description see {@link ChatMessage} documentation.
 * @Author hlantian
 * @Date Created in 14:46 2023/10/8
 * @Version 1.0
 */
public enum ChatMessageRole {
    SYSTEM("system"),

    USER("user"),

    ASSISTANT("assistant"),

    FUNCTION("function");

    private final String value;

    ChatMessageRole(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
