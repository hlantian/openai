package com.zxiaoyao.openai.api.finetuning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description Fine-tuning job
 * https://platform.openai.com/docs/api-reference/fine-tuning/object
 * @Author hlantian
 * @Date Created in 13:42 2023/10/8
 * @Version 1.0
 */
@Data
public class FineTuningJob {

    /**
     * The object identifier, which can be referenced in the API endpoints.
     * 对象标识符，可在 API 端点中引用。
     */
    private String id;

    /**
     * The object type, which is always "fine_tuning.job".
     * 对象类型，始终为 "fine_tuning.job"。
     */
    private String object;

    /**
     * The unix timestamp for when the fine-tuning job was created.
     * 创建微调任务的 unix 时间戳。
     */
    @JsonProperty("created_at")
    private Long createdAt;

    /**
     * The unix timestamp for when the fine-tuning job was finished.
     * 微调工作完成时的 unix 时间戳。
     */
    @JsonProperty("finished_at")
    private Long finishedAt;

    /**
     * The base model that is being fine-tuned.
     * 正在微调的基础模型。
     */
    private String model;

    /**
     * The name of the fine-tuned model that is being created.
     * Can be null if no fine-tuned model is created yet.
     * 正在创建的微调模型的名称。如果尚未创建微调模型，可以为空。
     */
    @JsonProperty("fine_tuned_model")
    private String fineTunedModel;

    /**
     * The organization that owns the fine-tuning job.
     * 微调job 的组织ID
     */
    @JsonProperty("organization_id")
    private String organizationId;

    /**
     * The current status of the fine-tuning job.
     * Can be either created, pending, running, succeeded, failed, or cancelled.
     * <p>
     * 微调任务的当前状态。
     * 可以是创建、等待、运行、成功、失败或取消。
     */
    private String status;

    /**
     * The hyperparameters used for the fine-tuning job.
     * See the fine-tuning guide for more details.
     * 用于微调工作的超参数。
     * 更多详情，请参阅微调指南。
     */
    private Hyperparameters hyperparameters;

    /**
     * The file ID used for training.
     * 用于训练的文件 ID。
     */
    @JsonProperty("training_file")
    private String trainingFile;

    /**
     * The file ID used for validation.
     * Can be null if validation is not used.
     * 用于验证的文件 ID。
     * 如果不使用验证，可以为空。
     */
    @JsonProperty("validation_file")
    private String validationFile;

    /**
     * The compiled results files for the fine-tuning job.
     * 微调工作的编译结果文件。
     */
    @JsonProperty("result_files")
    private List<String> resultFiles;

    /**
     * The total number of billable tokens processed by this fine-tuning job.
     * 该微调任务处理的计费令牌总数。
     */
    @JsonProperty("trained_tokens")
    private Integer trainedTokens;
}
