package com.zxiaoyao.openai.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.TextNode;
import com.zxiaoyao.openai.api.DeleteResult;
import com.zxiaoyao.openai.api.OpenAiError;
import com.zxiaoyao.openai.api.OpenAiHttpException;
import com.zxiaoyao.openai.api.audio.CreateTranscriptionRequest;
import com.zxiaoyao.openai.api.audio.CreateTranslationRequest;
import com.zxiaoyao.openai.api.audio.TranscriptionResult;
import com.zxiaoyao.openai.api.audio.TranslationResult;
import com.zxiaoyao.openai.api.completion.CompletionChunk;
import com.zxiaoyao.openai.api.completion.CompletionRequest;
import com.zxiaoyao.openai.api.completion.CompletionResult;
import com.zxiaoyao.openai.api.completion.chat.*;
import com.zxiaoyao.openai.api.edit.EditRequest;
import com.zxiaoyao.openai.api.edit.EditResult;
import com.zxiaoyao.openai.api.embedding.EmbeddingRequest;
import com.zxiaoyao.openai.api.embedding.EmbeddingResult;
import com.zxiaoyao.openai.api.file.File;
import com.zxiaoyao.openai.api.finetuning.FineTuningEvent;
import com.zxiaoyao.openai.api.finetuning.FineTuningJob;
import com.zxiaoyao.openai.api.finetuning.FineTuningJobRequest;
import com.zxiaoyao.openai.api.image.CreateImageEditRequest;
import com.zxiaoyao.openai.api.image.CreateImageRequest;
import com.zxiaoyao.openai.api.image.CreateImageVariationRequest;
import com.zxiaoyao.openai.api.image.ImageResult;
import com.zxiaoyao.openai.api.model.Model;
import com.zxiaoyao.openai.api.moderation.ModerationRequest;
import com.zxiaoyao.openai.api.moderation.ModerationResult;
import com.zxiaoyao.openai.client.AuthenticationInterceptor;
import com.zxiaoyao.openai.client.OpenAiApi;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description 服务
 * @Author hlantian
 * @Date Created in 10:02 2023/10/9
 * @Version 1.0
 */
@Slf4j
public class OpenAiService {

    private static final String BASE_URL = "https://ai.lvsutong.cn/";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(60);
    private static final ObjectMapper mapper = defaultObjectMapper();

    private final OpenAiApi api;
    private final ExecutorService executorService;

    /**
     * Creates a new OpenAiService that wraps OpenAiApi
     * 创建一个封装 OpenAiApi 的新 OpenAiService
     *
     * @param token OpenAi token string "sk-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
     */
    public OpenAiService(final String token) {
        this(token, DEFAULT_TIMEOUT);
    }

    /**
     * Creates a new OpenAiService that wraps OpenAiApi
     * 创建一个封装 OpenAiApi 的新 OpenAiService
     *
     * @param token   OpenAi token string "sk-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
     * @param timeout http read timeout, Duration.ZERO means no timeout http 读取超时，Duration.ZERO 表示无超时
     */
    public OpenAiService(final String token, final Duration timeout) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(token, timeout);
        Retrofit retrofit = defaultRetrofit(client, mapper);

        this.api = retrofit.create(OpenAiApi.class);
        this.executorService = client.dispatcher().executorService();
    }

    /**
     * Creates a new OpenAiService that wraps OpenAiApi.
     * Use this if you need more customization, but use OpenAiService(api, executorService) if you use streaming and
     * want to shut down instantly
     * 创建一个封装 OpenAiApi 的新 OpenAiService。
     * 如果您需要更多自定义功能，请使用该服务；如果您使用流式传输，并且想立即关闭
     *
     * @param api OpenAiApi instance to use for all methods 用于所有方法的 OpenAiApi 实例
     */
    public OpenAiService(final OpenAiApi api) {
        this.api = api;
        this.executorService = null;
    }

    /**
     * Creates a new OpenAiService that wraps OpenAiApi.
     * The ExecutorService must be the one you get from the client you created the api with
     * otherwise shutdownExecutor() won't work.
     * <p>
     * Use this if you need more customization.
     * 创建一个封装 OpenAiApi 的新 OpenAiService。
     * ExecutorService 必须是通过创建 api 的客户端获取的。
     * 否则 shutdownExecutor() 将不起作用。
     * <p>
     * 如果需要更多自定义功能，请使用此功能。
     *
     * @param api             OpenAiApi instance to use for all methods 用于所有方法的 OpenAiApi 实例
     * @param executorService the ExecutorService from client.dispatcher().executorService()
     */
    public OpenAiService(final OpenAiApi api, final ExecutorService executorService) {
        this.api = api;
        this.executorService = executorService;
    }

    public List<Model> listModels() {
        return execute(api.listModels()).data;
    }

    public Model getModel(String modelId) {
        return execute(api.getModel(modelId));
    }

    public CompletionResult createCompletion(CompletionRequest request) {
        return execute(api.createCompletion(request));
    }

    public Flowable<CompletionChunk> streamCompletion(CompletionRequest request) {
        request.setStream(true);

        return stream(api.createCompletionStream(request), CompletionChunk.class);
    }

    public ChatCompletionResult createChatCompletion(ChatCompletionRequest request) {
        return execute(api.createChatCompletion(request));
    }

    public Flowable<ChatCompletionChunk> streamChatCompletion(ChatCompletionRequest request) {
        request.setStream(true);

        return stream(api.createChatCompletionStream(request), ChatCompletionChunk.class);
    }

    public EditResult createEdit(EditRequest request) {
        return execute(api.createEdit(request));
    }

    public EmbeddingResult createEmbeddings(EmbeddingRequest request) {
        return execute(api.createEmbeddings(request));
    }

    public List<File> listFiles() {
        return execute(api.listFiles()).data;
    }

    public File uploadFile(String purpose, String filepath) {
        java.io.File file = new java.io.File(filepath);
        RequestBody purposeBody = RequestBody.create(okhttp3.MultipartBody.FORM, purpose);
        RequestBody fileBody = RequestBody.create(MediaType.parse("text"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", filepath, fileBody);

        return execute(api.uploadFile(purposeBody, body));
    }

    public DeleteResult deleteFile(String fileId) {
        return execute(api.deleteFile(fileId));
    }

    public File retrieveFile(String fileId) {
        return execute(api.retrieveFile(fileId));
    }

    public FineTuningJob createFineTuningJob(FineTuningJobRequest request) {
        return execute(api.createFineTuningJob(request));
    }

    public List<FineTuningJob> listFineTuningJobs() {
        return execute(api.listFineTuningJobs()).data;
    }

    public FineTuningJob retrieveFineTuningJob(String fineTuningJobId) {
        return execute(api.retrieveFineTuningJob(fineTuningJobId));
    }

    public FineTuningJob cancelFineTuningJob(String fineTuningJobId) {
        return execute(api.cancelFineTuningJob(fineTuningJobId));
    }

    public List<FineTuningEvent> listFineTuningJobEvents(String fineTuningJobId) {
        return execute(api.listFineTuningJobEvents(fineTuningJobId)).data;
    }


    public CompletionResult createFineTuneCompletion(CompletionRequest request) {
        return execute(api.createFineTuneCompletion(request));
    }


    public DeleteResult deleteFineTune(String fineTuneId) {
        return execute(api.deleteFineTune(fineTuneId));
    }

    public ImageResult createImage(CreateImageRequest request) {
        return execute(api.createImage(request));
    }

    public ImageResult createImageEdit(CreateImageEditRequest request, String imagePath, String maskPath) {
        java.io.File image = new java.io.File(imagePath);
        java.io.File mask = null;
        if (maskPath != null) {
            mask = new java.io.File(maskPath);
        }
        return createImageEdit(request, image, mask);
    }

    public ImageResult createImageEdit(CreateImageEditRequest request, java.io.File image, java.io.File mask) {
        RequestBody imageBody = RequestBody.create(MediaType.parse("image"), image);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.get("multipart/form-data"))
                .addFormDataPart("prompt", request.getPrompt())
                .addFormDataPart("size", request.getSize())
                .addFormDataPart("response_format", request.getResponseFormat())
                .addFormDataPart("image", "image", imageBody);

        if (request.getN() != null) {
            builder.addFormDataPart("n", request.getN().toString());
        }

        if (mask != null) {
            RequestBody maskBody = RequestBody.create(MediaType.parse("image"), mask);
            builder.addFormDataPart("mask", "mask", maskBody);
        }

        return execute(api.createImageEdit(builder.build()));
    }

    public ImageResult createImageVariation(CreateImageVariationRequest request, String imagePath) {
        java.io.File image = new java.io.File(imagePath);
        return createImageVariation(request, image);
    }

    public ImageResult createImageVariation(CreateImageVariationRequest request, java.io.File image) {
        RequestBody imageBody = RequestBody.create(MediaType.parse("image"), image);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.get("multipart/form-data"))
                .addFormDataPart("size", request.getSize())
                .addFormDataPart("response_format", request.getResponseFormat())
                .addFormDataPart("image", "image", imageBody);

        if (request.getN() != null) {
            builder.addFormDataPart("n", request.getN().toString());
        }

        return execute(api.createImageVariation(builder.build()));
    }

    public TranscriptionResult createTranscription(CreateTranscriptionRequest request, String audioPath) {
        java.io.File audio = new java.io.File(audioPath);
        return createTranscription(request, audio);
    }

    public TranscriptionResult createTranscription(CreateTranscriptionRequest request, java.io.File audio) {
        RequestBody audioBody = RequestBody.create(MediaType.parse("audio"), audio);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.get("multipart/form-data"))
                .addFormDataPart("model", request.getModel())
                .addFormDataPart("file", audio.getName(), audioBody);

        if (request.getPrompt() != null) {
            builder.addFormDataPart("prompt", request.getPrompt());
        }
        if (request.getResponseFormat() != null) {
            builder.addFormDataPart("response_format", request.getResponseFormat());
        }
        if (request.getTemperature() != null) {
            builder.addFormDataPart("temperature", request.getTemperature().toString());
        }
        if (request.getLanguage() != null) {
            builder.addFormDataPart("language", request.getLanguage());
        }

        return execute(api.createTranscription(builder.build()));
    }

    public TranslationResult createTranslation(CreateTranslationRequest request, String audioPath) {
        java.io.File audio = new java.io.File(audioPath);
        return createTranslation(request, audio);
    }

    public TranslationResult createTranslation(CreateTranslationRequest request, java.io.File audio) {
        RequestBody audioBody = RequestBody.create(MediaType.parse("audio"), audio);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.get("multipart/form-data"))
                .addFormDataPart("model", request.getModel())
                .addFormDataPart("file", audio.getName(), audioBody);

        if (request.getPrompt() != null) {
            builder.addFormDataPart("prompt", request.getPrompt());
        }
        if (request.getResponseFormat() != null) {
            builder.addFormDataPart("response_format", request.getResponseFormat());
        }
        if (request.getTemperature() != null) {
            builder.addFormDataPart("temperature", request.getTemperature().toString());
        }

        return execute(api.createTranslation(builder.build()));
    }

    public ModerationResult createModeration(ModerationRequest request) {
        return execute(api.createModeration(request));
    }

    /**
     * Calls the Open AI api, returns the response, and parses error messages if the request fails
     * 调用 Open AI api，返回响应，并在请求失败时解析错误信息
     */
    public static <T> T execute(Single<T> apiCall) {
        try {
            return apiCall.blockingGet();
        } catch (HttpException e) {
            try {
                if (e.response() == null || e.response().errorBody() == null) {
                    throw e;
                }
                String errorBody = e.response().errorBody().string();

                OpenAiError error = mapper.readValue(errorBody, OpenAiError.class);
                throw new OpenAiHttpException(error, e, e.code());
            } catch (IOException ex) {
                // couldn't parse OpenAI error
                throw e;
            }
        }
    }

    /**
     * Calls the Open AI api and returns a Flowable of SSE for streaming
     * omitting the last message.
     * 调用 Open AI api 并返回一个用于流式传输的 SSE Flowable。省略最后一条信息。
     *
     * @param apiCall The api call api调用
     */
    public static Flowable<SSE> stream(Call<ResponseBody> apiCall) {
        return stream(apiCall, false);
    }

    /**
     * Calls the Open AI api and returns a Flowable of SSE for streaming.
     * 调用 Open AI api 并返回用于流式传输的 SSE Flowable。
     *
     * @param apiCall  The api call api调用
     * @param emitDone If true the last message ([DONE]) is emitted 如果为 "true"，则发出最后一条信息（[DONE]）。
     */
    public static Flowable<SSE> stream(Call<ResponseBody> apiCall, boolean emitDone) {
        return Flowable.create(emitter -> apiCall.enqueue(new ResponseBodyCallback(emitter, emitDone)), BackpressureStrategy.BUFFER);
    }

    /**
     * Calls the Open AI api and returns a Flowable of type1· T for streaming
     * omitting the last message.
     * 调用 Open AI api，并返回一个流类型为 T 的 Flowable。省略最后一条信息。
     *
     * @param apiCall The api call  api调用
     * @param cl      Class of type T to return 返回 T 类型的类
     */
    public static <T> Flowable<T> stream(Call<ResponseBody> apiCall, Class<T> cl) {
        return stream(apiCall).map(sse -> {
            String data = sse.getData();
            log.info("Flowable=====: "+ data);
            return mapper.readValue(data, cl);
        });
    }

    /**
     * Shuts down the OkHttp ExecutorService.
     * The default behaviour of OkHttp's ExecutorService (ConnectionPool)
     * is to shut down after an idle timeout of 60s.
     * Call this method to shut down the ExecutorService immediately.
     * 关闭 OkHttp 执行器服务。
     * OkHttp 的执行器服务（ConnectionPool）的默认行为是在空闲超时 60s 后关闭。
     * 调用该方法可立即关闭 ExecutorService。
     */
    public void shutdownExecutor() {
        Objects.requireNonNull(this.executorService, "executorService must be set in order to shut down");
        this.executorService.shutdown();
    }

    public static OpenAiApi buildApi(String token, Duration timeout) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(token, timeout);
        Retrofit retrofit = defaultRetrofit(client, mapper);

        return retrofit.create(OpenAiApi.class);
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.addMixIn(ChatFunction.class, ChatFunctionMixIn.class);
        mapper.addMixIn(ChatCompletionRequest.class, ChatCompletionRequestMixIn.class);
        mapper.addMixIn(ChatFunctionCall.class, ChatFunctionCallMixIn.class);
        return mapper;
    }

    public static OkHttpClient defaultClient(String token, Duration timeout) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(token))
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
                .build();
    }

    public static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Flowable<ChatMessageAccumulator> mapStreamToAccumulator(Flowable<ChatCompletionChunk> flowable) {
        ChatFunctionCall functionCall = new ChatFunctionCall(null, null);
        ChatMessage accumulatedMessage = new ChatMessage(ChatMessageRole.ASSISTANT.value(), null);

        return flowable.map(chunk -> {
            ChatMessage messageChunk = chunk.getChoices().get(0).getMessage();
            messageChunk.setFinishReason(chunk.getChoices().get(0).getFinishReason());
            if (messageChunk.getFunctionCall() != null) {
                if (messageChunk.getFunctionCall().getName() != null) {
                    String namePart = messageChunk.getFunctionCall().getName();
                    functionCall.setName((functionCall.getName() == null ? "" : functionCall.getName()) + namePart);
                }
                if (messageChunk.getFunctionCall().getArguments() != null) {
                    String argumentsPart = messageChunk.getFunctionCall().getArguments() == null ? "" : messageChunk.getFunctionCall().getArguments().asText();
                    functionCall.setArguments(new TextNode((functionCall.getArguments() == null ? "" : functionCall.getArguments().asText()) + argumentsPart));
                }
                accumulatedMessage.setFunctionCall(functionCall);
            } else {
                accumulatedMessage.setContent((accumulatedMessage.getContent() == null ? "" : accumulatedMessage.getContent()) + (messageChunk.getContent() == null ? "" : messageChunk.getContent()));
            }

            if (chunk.getChoices().get(0).getFinishReason() != null) { // last
                if (functionCall.getArguments() != null) {
                    functionCall.setArguments(mapper.readTree(functionCall.getArguments().asText()));
                    accumulatedMessage.setFunctionCall(functionCall);
                }
            }

            return new ChatMessageAccumulator(messageChunk, accumulatedMessage);
        });
    }

}
