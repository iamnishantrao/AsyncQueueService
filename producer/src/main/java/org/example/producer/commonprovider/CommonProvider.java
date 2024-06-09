package org.example.producer.commonprovider;

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
}
