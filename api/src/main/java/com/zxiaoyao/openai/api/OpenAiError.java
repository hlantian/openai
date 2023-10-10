package com.zxiaoyao.openai.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Represents the error body when an OpenAI request fails
 * 代表 OpenAI 请求失败时的错误正文
 * @Author hlantian
 * @Date Created in 16:46 2023/10/8
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiError {


    private OpenAiErrorDetails error;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OpenAiErrorDetails {

        /**
         * Human-readable error message
         * 人类可读的错误信息
         */
        private String message;

        /**
         * OpenAI error type, for example "invalid_request_error"
         * OpenAI 错误类型，例如 "invalid_request_error"
         * https://platform.openai.com/docs/guides/error-codes/python-library-error-types
         */
        private String type;

        private String param;

        /**
         * OpenAI error code, for example "invalid_api_key"
         * OpenAI 错误代码，例如 "invalid_api_key"
         */
        private String code;
    }
}
