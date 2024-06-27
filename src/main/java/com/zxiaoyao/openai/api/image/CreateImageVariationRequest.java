package com.zxiaoyao.openai.api.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description A request for OpenAi to create a variation of an image  All fields are optional
 * 请求 OpenAi 创建图像的变体 所有字段均为可选字段
 * https://beta.openai.com/docs/api-reference/images/create-variation
 * @Author hlantian
 * @Date Created in 11:25 2023/10/8
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateImageVariationRequest {

    /**
     * The number of images to generate. Must be between 1 and 10. Defaults to 1.
     * 要生成的图像数量。必须介于 1 和 10 之间。默认为 1。
     */
    private Integer n;

    /**
     * The size of the generated images. Must be one of "256x256", "512x512", or "1024x1024". Defaults to "1024x1024".
     * 生成图像的大小。必须是 "256x256"、"512x512 "或 "1024x1024 "之一。默认为 "1024x1024"
     */
    private String size;

    /**
     * The format in which the generated images are returned. Must be one of url or b64_json. Defaults to url.
     * 返回生成图像的格式。必须是 url 或 b64_json 之一。默认为 url。
     */
    @JsonProperty("response_format")
    private String responseFormat;

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     * 代表最终用户的唯一标识符，这将有助于 OpenAI 监控和检测滥用行为。
     */
    private String user;
}
