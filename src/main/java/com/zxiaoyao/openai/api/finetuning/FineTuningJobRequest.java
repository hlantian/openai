package com.zxiaoyao.openai.api.finetuning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Description Request to create a fine tuning job
 * 请求创建微调工作
 * https://platform.openai.com/docs/api-reference/fine-tuning/create
 * @Author hlantian
 * @Date Created in 9:43 2023/10/9
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FineTuningJobRequest {
    /**
     * The ID of an uploaded file that contains training data.
     * 包含训练数据的上传文件的 ID。
     */
    @NonNull
    @JsonProperty("training_file")
    String trainingFile;

    /**
     * The ID of an uploaded file that contains validation data.
     * Optional.
     * 包含验证数据的上传文件的 ID。
     * 可选.
     */
    @JsonProperty("validation_file")
    String validationFile;

    /**
     * The name of the model to fine-tune.
     * 要微调的model名称。
     */
    @NonNull
    String model;

    /**
     * The hyperparameters used for the fine-tuning job.
     * 用于微调工作的超参数。
     */
    Hyperparameters hyperparameters;

    /**
     * A string of up to 40 characters that will be added to your fine-tuned model name.
     * 将添加到微调后的模型名称中的字符串，最多 40 个字符.
     */
    String suffix;
}
