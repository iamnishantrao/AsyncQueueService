package org.example.producer.commonprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commons.mapper.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonProvider {

    @Bean
    public ModelMapper modelMapper() {
       return ModelMapperConfig.modelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
