package org.example.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.dto.RequestDto;
import org.example.commons.messaging.MessageSender;
import org.example.commons.model.RequestModel;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;
import org.example.producer.exception.MalformedRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProducerService {

    @NonNull private final MessageSender messageSender;
    @NonNull private final Validator validator;
    @NonNull private final ModelMapper modelMapper;
    @NonNull private final ObjectMapper objectMapper;

    @Autowired
    public ProducerService(@NonNull final MessageSender messageSender,
                           @NonNull final Validator validator,
                           @NonNull final ModelMapper modelMapper,
                           @NonNull final ObjectMapper objectMapper) {
        this.messageSender = messageSender;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    public void produceRequest(@NonNull final RequestDto requestDto,
                               @NonNull final String requestId) {
        final RequestModel requestModel = modelMapper.map(requestDto, RequestModel.class);
        log.debug("Converted request: {}", requestModel);
        requestModel.setRequestId(requestId);
        try {
            final String serialized = this.objectMapper.writeValueAsString(requestModel);
            log.info("Start dispatching requestId: {}", requestModel.getRequestId());
            messageSender.sendMessage(serialized);
            log.info("Successfully dispatched requestId: {}", requestModel.getRequestId());
        } catch (final JsonProcessingException e) {
            throw new MalformedRequestException("Not able to serialize request.", e);
        }
    }

    public void consumeResponse(@NonNull final String response) {
        log.info("Message consumed successfully: {}", response);
    }

    public List<ValidationError> validateRequest(@NonNull final RequestDto requestDto) {
        return validator.validate(requestDto);
    }

    public String getRequestId() {
        return UUID.randomUUID().toString();
    }
}
