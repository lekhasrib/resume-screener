package com.resume_screener.ai;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@Component
public class GroqAPIClient{
        @Value("${GROQ_API_KEY}")
        private String apiKey;
        private final RestTemplate restTemplate = new RestTemplate();

        public String callGroq(String prompt) {
                String url = "https://api.groq.com/openai/v1/chat/completions";

                Map<String, Object> body = new HashMap<>();
                body.put("model", "openai/gpt-oss-20b");

                List<Map<String, String>> messages = new ArrayList<>();
                messages.add(Map.of("role", "user", "content", prompt));
                body.put("messages", messages);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(apiKey);

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                
                return response.getBody();
                
                }
}
