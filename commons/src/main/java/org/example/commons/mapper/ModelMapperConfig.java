package org.example.commons.mapper;

import org.example.commons.dto.HospitalDto;
import org.example.commons.dto.PatientDto;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.HospitalModel;
import org.example.commons.model.PatientModel;
import org.example.commons.model.RequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(PatientDto.class, PatientModel.class);
        modelMapper.createTypeMap(HospitalDto.class, HospitalModel.class);
        modelMapper.createTypeMap(RequestDto.class, RequestModel.class);

        return modelMapper;
    }
}
