package com.zxiaoyao.openai.client;

import com.zxiaoyao.openai.api.DeleteResult;
import com.zxiaoyao.openai.api.OpenAiResponse;
import com.zxiaoyao.openai.api.audio.TranscriptionResult;
import com.zxiaoyao.openai.api.audio.TranslationResult;
import com.zxiaoyao.openai.api.completion.CompletionRequest;
import com.zxiaoyao.openai.api.completion.CompletionResult;
import com.zxiaoyao.openai.api.completion.chat.ChatCompletionRequest;
import com.zxiaoyao.openai.api.completion.chat.ChatCompletionResult;
import com.zxiaoyao.openai.api.edit.EditRequest;
import com.zxiaoyao.openai.api.edit.EditResult;
import com.zxiaoyao.openai.api.embedding.EmbeddingRequest;
import com.zxiaoyao.openai.api.embedding.EmbeddingResult;
import com.zxiaoyao.openai.api.file.File;
import com.zxiaoyao.openai.api.finetuning.FineTuningEvent;
import com.zxiaoyao.openai.api.finetuning.FineTuningJob;
import com.zxiaoyao.openai.api.finetuning.FineTuningJobRequest;
import com.zxiaoyao.openai.api.image.CreateImageRequest;
import com.zxiaoyao.openai.api.image.ImageResult;
import com.zxiaoyao.openai.api.model.Model;
import com.zxiaoyao.openai.api.moderation.ModerationRequest;
import com.zxiaoyao.openai.api.moderation.ModerationResult;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @Description 接口
 * @Author hlantian
 * @Date Created in 9:32 2023/10/9
 * @Version 1.0
 */
public interface OpenAiApi {

    @GET("v1/models")
    Single<OpenAiResponse<Model>> listModels();

    @GET("/v1/models/{model_id}")
    Single<Model> getModel(@Path("model_id") String modelId);

    @POST("/v1/completions")
    Single<CompletionResult> createCompletion(@Body CompletionRequest request);

    @Streaming
    @POST("/v1/completions")
    Call<ResponseBody> createCompletionStream(@Body CompletionRequest request);

    @POST("/v1/chat/completions")
    Single<ChatCompletionResult> createChatCompletion(@Body ChatCompletionRequest request);

    @Streaming
    @POST("/v1/chat/completions")
    Call<ResponseBody> createChatCompletionStream(@Body ChatCompletionRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/completions")
    Single<CompletionResult> createCompletion(@Path("engine_id") String engineId, @Body CompletionRequest request);

    @POST("/v1/edits")
    Single<EditResult> createEdit(@Body EditRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/edits")
    Single<EditResult> createEdit(@Path("engine_id") String engineId, @Body EditRequest request);

    @POST("/v1/embeddings")
    Single<EmbeddingResult> createEmbeddings(@Body EmbeddingRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/embeddings")
    Single<EmbeddingResult> createEmbeddings(@Path("engine_id") String engineId, @Body EmbeddingRequest request);

    @GET("/v1/files")
    Single<OpenAiResponse<File>> listFiles();

    @Multipart
    @POST("/v1/files")
    Single<File> uploadFile(@Part("purpose") RequestBody purpose, @Part MultipartBody.Part file);

    @DELETE("/v1/files/{file_id}")
    Single<DeleteResult> deleteFile(@Path("file_id") String fileId);

    @GET("/v1/files/{file_id}")
    Single<File> retrieveFile(@Path("file_id") String fileId);

    @POST("/v1/fine_tuning/jobs")
    Single<FineTuningJob> createFineTuningJob(@Body FineTuningJobRequest request);

    @GET("/v1/fine_tuning/jobs")
    Single<OpenAiResponse<FineTuningJob>> listFineTuningJobs();

    @GET("/v1/fine_tuning/jobs/{fine_tuning_job_id}")
    Single<FineTuningJob> retrieveFineTuningJob(@Path("fine_tuning_job_id") String fineTuningJobId);

    @POST("/v1/fine_tuning/jobs/{fine_tuning_job_id}/cancel")
    Single<FineTuningJob> cancelFineTuningJob(@Path("fine_tuning_job_id") String fineTuningJobId);

    @GET("/v1/fine_tuning/jobs/{fine_tuning_job_id}/events")
    Single<OpenAiResponse<FineTuningEvent>> listFineTuningJobEvents(@Path("fine_tuning_job_id") String fineTuningJobId);


    @POST("/v1/completions")
    Single<CompletionResult> createFineTuneCompletion(@Body CompletionRequest request);



    @DELETE("/v1/models/{fine_tune_id}")
    Single<DeleteResult> deleteFineTune(@Path("fine_tune_id") String fineTuneId);

    @POST("/v1/images/generations")
    Single<ImageResult> createImage(@Body CreateImageRequest request);

    @POST("/v1/images/edits")
    Single<ImageResult> createImageEdit(@Body RequestBody requestBody);

    @POST("/v1/images/variations")
    Single<ImageResult> createImageVariation(@Body RequestBody requestBody);

    @POST("/v1/audio/transcriptions")
    Single<TranscriptionResult> createTranscription(@Body RequestBody requestBody);

    @POST("/v1/audio/translations")
    Single<TranslationResult> createTranslation(@Body RequestBody requestBody);

    @POST("/v1/moderations")
    Single<ModerationResult> createModeration(@Body ModerationRequest request);

}
