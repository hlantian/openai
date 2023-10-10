package com.zxiaoyao.openai.service;

/**
 * @Description Exception indicating a SSE format error
 * 显示 SSE 格式错误的异常
 * @Author hlantian
 * @Date Created in 10:11 2023/10/9
 * @Version 1.0
 */
public class SSEFormatException extends Throwable {
    public SSEFormatException(String msg) {
        super(msg);
    }
}
