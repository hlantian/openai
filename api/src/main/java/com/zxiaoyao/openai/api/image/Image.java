package com.zxiaoyao.openai.api.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description An object containing either a URL or a base 64 encoded image.
 * 包含 URL 或基 64 编码图像的对象
 * https://beta.openai.com/docs/api-reference/images
 * @Author hlantian
 * @Date Created in 11:20 2023/10/8
 * @Version 1.0
 */
@Data
public class Image {
    /**
     * The URL where the image can be accessed.
     * 可以访问图片的 URL。
     */
    private String url;


    /**
     * Base64 encoded image string.
     * Base64 编码的图像字符串
     */
    @JsonProperty("b64_json")
    private String b64Json;
}
