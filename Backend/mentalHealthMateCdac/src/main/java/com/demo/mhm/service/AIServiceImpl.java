package com.demo.mhm.service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "your-openai-api-key";

    @Override
    public String getAnswer(String question) {
        // TODO Auto-generated method stub
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(OPENAI_API_URL);

            // Prepare request body
            String json = String.format(
                "{\"model\": \"gpt-4\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                question
            );

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + API_KEY);

            // Execute API call
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                if (response.getCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response to get the AI's reply
                    return extractAnswer(responseBody);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sorry, I couldn't process your request.";
    }

    @Override
    public String extractAnswer(String responseAnswer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extractAnswer'");
    }
    
}
