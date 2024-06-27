package com.zxiaoyao.openai.service;

/**
 * @Description Simple Server Sent Event representation
 * 简单的服务器发送事件表示法
 * @Author hlantian
 * @Date Created in 10:09 2023/10/9
 * @Version 1.0
 */
public class SSE {

    private static final String DONE_DATA = "[DONE]";

    private final String data;

    public SSE(String data){
        this.data = data;
    }

    public String getData(){
        return this.data;
    }

    public byte[] toBytes(){
        return String.format("data: %s\n\n", this.data).getBytes();
    }

    public boolean isDone(){
        return DONE_DATA.equalsIgnoreCase(this.data);
    }
}
