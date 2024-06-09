package org.example.consumer.commonprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonProvider {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
