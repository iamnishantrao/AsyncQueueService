package org.example.producer.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.RequestModel;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.requestdto.RequestDtoValidatorImpl;
import org.example.producer.messaging.MessageSender;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProducerService {

    @NonNull private final MessageSender messageSender;
    @NonNull private final RequestDtoValidatorImpl requestDtoValidatorImpl;
    @NonNull private final ModelMapper modelMapper;

    @Autowired
    public ProducerService(@NonNull final MessageSender messageSender,
                           @NonNull final RequestDtoValidatorImpl requestDtoValidatorImpl,
                           @NonNull final ModelMapper modelMapper) {
        this.messageSender = messageSender;
        this.requestDtoValidatorImpl = requestDtoValidatorImpl;
        this.modelMapper = modelMapper;
    }

    public void produceRequest(@NonNull final RequestDto requestDto,
                               @NonNull final String requestId) {
        final RequestModel requestModel = modelMapper.map(requestDto, RequestModel.class);
        log.debug("Converted request: {}", requestModel);
        requestModel.setRequestId(requestId);
        messageSender.sendMessage(requestModel);
    }

    public void consumeResponse(@NonNull final String response) {
        log.info("Message consumed successfully: {}", response);
    }

    public List<ValidationError> validateRequest(@NonNull final RequestDto requestDto) {
        return requestDtoValidatorImpl.validate(requestDto);
    }

    public String getRequestId() {
        return UUID.randomUUID().toString();
    }
}
