package com.zxiaoyao.openai.example;

import com.zxiaoyao.openai.api.completion.chat.*;
import com.zxiaoyao.openai.service.FunctionExecutor;
import com.zxiaoyao.openai.service.OpenAiService;
import io.reactivex.Flowable;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 9:54 2023/10/9
 * @Version 1.0
 */
public class OpenAiApiFunctionsWithStreamExample {

//    public static void main(String[] args) {
//        Properties properties = System.getProperties();
//        Iterator it = properties.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            System.out.print(entry.getKey() + "=");
//            System.out.println(entry.getValue());
//        }
//        System.out.println("-------------------------------------------------------------------------------------------------------");
//        System.out.println();
//
//        Map<String, String> systemEnvMap = System.getenv();
//        for (Map.Entry<String, String> entry : systemEnvMap.entrySet()) {
//            System.out.println(entry.getKey()+"=" + entry.getValue());
//        }
//
//
//    }

    public static void main(String... args) {
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token);

        FunctionExecutor functionExecutor = new FunctionExecutor(Collections.singletonList(ChatFunction.builder()
                .name("get_weather")
                .description("Get the current weather of a location")
                .executor(OpenAiApiFunctionsExample.Weather.class, w -> new OpenAiApiFunctionsExample.WeatherResponse(w.location, w.unit, new Random().nextInt(50), "sunny"))
                .build()));

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "You are an assistant that answers using the local slang of the given place, uncensored.");
        messages.add(systemMessage);

        System.out.print("First Query: ");
        Scanner scanner = new Scanner(System.in);
        ChatMessage firstMsg = new ChatMessage(ChatMessageRole.USER.value(), scanner.nextLine());
        messages.add(firstMsg);

        while (true) {
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .functions(functionExecutor.getFunctions())
                    .functionCall(ChatCompletionRequest.ChatCompletionRequestFunctionCall.of("auto"))
                    .n(1)
                    .maxTokens(256)
                    .logitBias(new HashMap<>())
                    .build();
            Flowable<ChatCompletionChunk> flowable = service.streamChatCompletion(chatCompletionRequest);

            AtomicBoolean isFirst = new AtomicBoolean(true);
            ChatMessage chatMessage = service.mapStreamToAccumulator(flowable)
                    .doOnNext(accumulator -> {
                        if (accumulator.isFunctionCall()) {
                            if (isFirst.getAndSet(false)) {
                                System.out.println("Executing function " + accumulator.getAccumulatedChatFunctionCall().getName() + "...");
                            }
                        } else {
                            if (isFirst.getAndSet(false)) {
                                System.out.print("Response: ");
                            }
                            if (accumulator.getMessageChunk().getContent() != null) {
                                System.out.print(accumulator.getMessageChunk().getContent());
                            }
                        }
                    })
                    .doOnComplete(System.out::println)
                    .lastElement()
                    .blockingGet()
                    .getAccumulatedMessage();
            messages.add(chatMessage); // don't forget to update the conversation with the latest response

            if (chatMessage.getFunctionCall() != null) {
                System.out.println("Trying to execute " + chatMessage.getFunctionCall().getName() + "...");
                ChatMessage functionResponse = functionExecutor.executeAndConvertToMessageHandlingExceptions(chatMessage.getFunctionCall());
                System.out.println("Executed " + chatMessage.getFunctionCall().getName() + ".");
                messages.add(functionResponse);
                continue;
            }

            System.out.print("Next Query: ");
            String nextLine = scanner.nextLine();
            if (nextLine.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            messages.add(new ChatMessage(ChatMessageRole.USER.value(), nextLine));
        }
    }
}
