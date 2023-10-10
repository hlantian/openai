package com.zxiaoyao.openai.service;

import com.zxiaoyao.openai.api.completion.chat.ChatFunctionCall;
import com.zxiaoyao.openai.api.completion.chat.ChatMessage;

/**
 * @Description Class that accumulates chat messages and provides utility methods for handling message chunks and function calls within a chat stream. This class is immutable.
 * 该类用于累积聊天信息，并提供实用方法来处理聊天流中的信息块和函数调用。该类不可变。
 * @Author hlantian
 * @Date Created in 10:29 2023/10/9
 * @Version 1.0
 */
public class ChatMessageAccumulator {

    private final ChatMessage messageChunk;
    private final ChatMessage accumulatedMessage;

    /**
     * Constructor that initializes the message chunk and accumulated message.
     * 构造函数，用于初始化信息块和累积信息。
     *
     * @param messageChunk       The message chunk. 信息块
     * @param accumulatedMessage The accumulated message. 累积的信息
     */
    public ChatMessageAccumulator(ChatMessage messageChunk, ChatMessage accumulatedMessage) {
        this.messageChunk = messageChunk;
        this.accumulatedMessage = accumulatedMessage;
    }

    /**
     * Checks if the accumulated message contains a function call. 检查累计报文是否包含函数调用。
     *
     * @return true if the accumulated message contains a function call, false otherwise. 如果累积信息包含函数调用，则为 true，否则为 false。
     */
    public boolean isFunctionCall() {
        return getAccumulatedMessage().getFunctionCall() != null && getAccumulatedMessage().getFunctionCall().getName() != null;
    }

    /**
     * Checks if the accumulated message contains a chat message. 检查累积的信息是否包含聊天信息。
     *
     * @return true if the accumulated message contains a chat message, false otherwise. 如果累积的信息包含聊天信息，则为 true，否则为 false。
     */
    public boolean isChatMessage() {
        return !isFunctionCall();
    }

    /**
     * Retrieves the message chunk. 读取信息块。
     *
     * @return the message chunk. 信息块。
     */
    public ChatMessage getMessageChunk() {
        return messageChunk;
    }

    /**
     * Retrieves the accumulated message. 读取累积的信息。
     *
     * @return the accumulated message. 累积的信息。
     */
    public ChatMessage getAccumulatedMessage() {
        return accumulatedMessage;
    }

    /**
     * Retrieves the function call from the message chunk.
     * This is equivalent to getMessageChunk().getFunctionCall().
     * 从信息块中读取函数调用。
     * 这等同于 getMessageChunk().getFunctionCall()。
     *
     * @return the function call from the message chunk. 消息块中的函数调用。
     */
    public ChatFunctionCall getChatFunctionCallChunk() {
        return getMessageChunk().getFunctionCall();
    }

    /**
     * Retrieves the function call from the accumulated message.
     * This is equivalent to getAccumulatedMessage().getFunctionCall().
     * 从累积信息中读取函数调用。
     *       这等同于 getAccumulatedMessage().getFunctionCall()。
     *
     * @return the function call from the accumulated message.从累积的消息调用函数。
     */
    public ChatFunctionCall getAccumulatedChatFunctionCall() {
        return getAccumulatedMessage().getFunctionCall();
    }
}
