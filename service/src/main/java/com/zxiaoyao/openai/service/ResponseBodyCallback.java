package com.zxiaoyao.openai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxiaoyao.openai.api.OpenAiError;
import com.zxiaoyao.openai.api.OpenAiHttpException;
import io.reactivex.FlowableEmitter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @Description Callback to parse Server Sent Events (SSE) from raw InputStream and emit the events with io.reactivex.FlowableEmitter to allow streaming of SSE.
 * 回调，用于解析原始 InputStream 中的服务器发送事件（SSE），并使用 io.reactivex.FlowableEmitter 发射事件，以实现 SSE 的流式传输。
 * @Author hlantian
 * @Date Created in 10:10 2023/10/9
 * @Version 1.0
 */
public class ResponseBodyCallback implements Callback<ResponseBody> {
    private static final ObjectMapper mapper = OpenAiService.defaultObjectMapper();

    private FlowableEmitter<SSE> emitter;
    private boolean emitDone;

    public ResponseBodyCallback(FlowableEmitter<SSE> emitter, boolean emitDone) {
        this.emitter = emitter;
        this.emitDone = emitDone;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        BufferedReader reader = null;

        try {
            if (!response.isSuccessful()) {
                HttpException e = new HttpException(response);
                ResponseBody errorBody = response.errorBody();

                if (errorBody == null) {
                    throw e;
                } else {
                    OpenAiError error = mapper.readValue(
                            errorBody.string(),
                            OpenAiError.class
                    );
                    throw new OpenAiHttpException(error, e, e.code());
                }
            }

            InputStream in = response.body().byteStream();
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            SSE sse = null;

            while (!emitter.isCancelled() && (line = reader.readLine()) != null) {
                if (line.startsWith("data:")) {
                    String data = line.substring(5).trim();
                    sse = new SSE(data);
                } else if (line.equals("") && sse != null) {
                    if (sse.isDone()) {
                        if (emitDone) {
                            emitter.onNext(sse);
                        }
                        break;
                    }

                    emitter.onNext(sse);
                    sse = null;
                } else {
                    throw new SSEFormatException("Invalid sse format! " + line);
                }
            }

            emitter.onComplete();

        } catch (Throwable t) {
            onFailure(call, t);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        emitter.onError(t);
    }
}
