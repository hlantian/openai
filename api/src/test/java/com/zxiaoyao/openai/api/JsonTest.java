package com.zxiaoyao.openai.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxiaoyao.openai.api.audio.TranscriptionResult;
import com.zxiaoyao.openai.api.audio.TranslationResult;
import com.zxiaoyao.openai.api.completion.chat.ChatCompletionRequest;
import com.zxiaoyao.openai.api.completion.chat.ChatCompletionResult;
import com.zxiaoyao.openai.api.edit.EditRequest;
import com.zxiaoyao.openai.api.edit.EditResult;
import com.zxiaoyao.openai.api.embedding.EmbeddingRequest;
import com.zxiaoyao.openai.api.embedding.EmbeddingResult;
import com.zxiaoyao.openai.api.file.File;
import com.zxiaoyao.openai.api.image.ImageResult;
import com.zxiaoyao.openai.api.model.Model;
import com.zxiaoyao.openai.api.moderation.ModerationRequest;
import com.zxiaoyao.openai.api.moderation.ModerationResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 16:52 2023/10/8
 * @Version 1.0
 */
public class JsonTest {

    @ParameterizedTest
    @ValueSource(classes = {
            ChatCompletionRequest.class,
            ChatCompletionResult.class,
            DeleteResult.class,
            EditRequest.class,
            EditResult.class,
            EmbeddingRequest.class,
            EmbeddingResult.class,
            File.class,
            ImageResult.class,
            TranscriptionResult.class,
            TranslationResult.class,
            Model.class,
            ModerationRequest.class,
            ModerationResult.class
    })
    void objectMatchesJson(Class<?> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String path = "src/test/resources/fixtures/" + clazz.getSimpleName() + ".json";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String json = new String(bytes);

        String actual = mapper.writeValueAsString(mapper.readValue(json, clazz));
        System.out.println(actual);
        // Convert to JsonNodes to avoid any json formatting differences
        assertEquals(mapper.readTree(json), mapper.readTree(actual));
    }
}
