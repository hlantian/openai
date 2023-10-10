package com.zxiaoyao.openai.example;

import com.zxiaoyao.openai.api.completion.chat.ChatMessage;
import com.zxiaoyao.openai.api.completion.chat.ChatMessageRole;
import com.zxiaoyao.openai.api.utils.TikTokensUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 14:43 2023/10/9
 * @Version 1.0
 */
public class TikTokensExample {
    public static void main(String... args) {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), "Hello OpenAI 1."));
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), "Hello OpenAI 2.   "));

        int tokens_1 = TikTokensUtil.tokens(TikTokensUtil.ModelEnum.GPT_3_5_TURBO.getName(), messages);
        int tokens_2 = TikTokensUtil.tokens(TikTokensUtil.ModelEnum.GPT_3_5_TURBO.getName(), "Hello OpenAI 1.");
        int tokens_3 = TikTokensUtil.tokens(TikTokensUtil.ModelEnum.GPT_3_5_TURBO.getName(), "一二三四五六七八九十一二三四五六七八九十。，张三");
        System.out.println(tokens_1);
        System.out.println(tokens_2);
        System.out.println(tokens_3);
    }
}
