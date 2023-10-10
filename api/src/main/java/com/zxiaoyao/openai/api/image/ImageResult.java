package com.zxiaoyao.openai.api.image;

import lombok.Data;

import java.util.List;

/**
 * @Description An object with a list of image results.
 * 包含图像结果列表的对象。
 * https://beta.openai.com/docs/api-reference/images
 * @Author hlantian
 * @Date Created in 11:21 2023/10/8
 * @Version 1.0
 */
@Data
public class ImageResult {
    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位。
     *
     */
    private Long created;

    /**
     * List of image results.
     * 图像结果列表。
     */
    private List<Image> data;
}
