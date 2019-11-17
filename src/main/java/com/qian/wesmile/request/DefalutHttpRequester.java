package com.qian.wesmile.request;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuhuaiqian
 */
public class DefalutHttpRequester extends AbstractHttpRequester {
    private static final Logger log = LoggerFactory.getLogger(DefalutHttpRequester.class);
    private static OkHttpClient client = new OkHttpClient();
    private static Request.Builder builder = new Request.Builder();

    @Override
    public String doRequest(String url, String body) {
        builder.url(url);
        if (body != null && !body.isEmpty()) {
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), body);
            builder.post(requestBody);
        }
        log.debug("url {} \r\n body{}", url, body);
        try (Response response = client.newCall(builder.build()).execute()) {

            String result = new String(response.body().bytes());
            log.debug("response {}", result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
