package com.zxiaoyao.openai.api.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Description Given a prompt and an instruction, OpenAi will return an edited version of the prompt
 * 给定提示和指令后，OpenAi 将返回经过编辑的提示版本
 * https://beta.openai.com/docs/api-reference/edits/create
 * @Author hlantian
 * @Date Created in 14:10 2023/10/8
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditRequest {

    /**
     * The name of the model to use.
     * Required if using the new v1/edits endpoint.
     * 要使用的模型名称。
     * 如果使用新的 v1/edits 端点，则为必填项。
     */
    private String model;

    /**
     * The input text to use as a starting point for the edit.
     * 作为编辑起点的输入文本。
     */
    private String input;

    /**
     * The instruction that tells the model how to edit the prompt.
     * For example, "Fix the spelling mistakes"
     * 告诉模型如何编辑提示的指令。
     * 例如，"Fix the spelling mistakes"。
     */
    @NonNull
    private String instruction;

    /**
     * How many edits to generate for the input and instruction.
     * 为输入和指令生成多少编辑。
     */
    private Integer n;

    /**
     * What sampling temperature to use. Higher values means the model will take more risks.
     * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
     * <p>
     * We generally recommend altering this or {@link EditRequest#topP} but not both.
     * 使用什么采样temperature。取样temperature越高，模型承担的风险越大。
     * 对于更有创意的应用，请尝试 0.9，而对于有明确答案的应用，请尝试 0（最大取样）。
     * <p>
     * 我们通常建议修改此值或 {@link EditRequest#topP} 但不要同时修改。
     */
    private Double temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass.So 0.1 means only the tokens comprising the top 10% probability mass are
     * considered.
     * <p>
     * We generally recommend altering this or {@link EditRequest#temperature} but not both.
     * temperature采样的另一种方法是核采样，模型会考虑以下结果
     * 因此，0.1 意味着只考虑概率质量最高的 10% 的标记。
     * <p>
     * 我们通常建议修改此项或{@link EditRequest#temperature}，但不能同时修改。
     */
    @JsonProperty("top_p")
    private Double topP;
}
