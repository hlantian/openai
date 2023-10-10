package com.zxiaoyao.openai.api.finetuning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Hyperparameters for a fine-tuning job
 * https://platform.openai.com/docs/api-reference/fine-tuning/object#hyperparameters
 * @Author hlantian
 * @Date Created in 13:43 2023/10/8
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hyperparameters {
    /**
     * The number of epochs to train the model for.
     * An epoch refers to one full cycle through the training dataset.
     * "Auto" decides the optimal number of epochs based on the size of the dataset.
     * If setting the number manually, we support any number between 1 and 50 epochs.
     * 训练模型的历元数。
     * 一个历元指训练数据集的一个完整周期。
     * "Auto "会根据数据集的大小决定最佳的epoch次数。
     * 如果手动设置，我们支持 1 到 50 个 Epoch 之间的任何数量。
     */
    @JsonProperty("n_epochs")
    private Integer nEpochs;
}
