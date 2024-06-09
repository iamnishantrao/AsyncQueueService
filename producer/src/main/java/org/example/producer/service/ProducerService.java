package org.example.producer.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.RequestModel;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.requestdto.RequestDtoValidatorImpl;
import org.example.producer.messaging.MessageSender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProducerService {

    private final MessageSender messageSender;
    private final RequestDtoValidatorImpl requestDtoValidatorImpl;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProducerService(@NonNull final MessageSender messageSender,
                           @NonNull final RequestDtoValidatorImpl requestDtoValidatorImpl) {
        this.messageSender = messageSender;
        this.requestDtoValidatorImpl = requestDtoValidatorImpl;
    }

    public String produceRequest(RequestDto requestDto) {
        final RequestModel requestModel = modelMapper.map(requestDto, RequestModel.class);
        messageSender.sendMessage(requestModel);
        return "Message produced successfully: " + requestModel;
    }

    public void consumeResponse(String response) {
        System.out.println("Message consumed successfully: " + response);
    }

    public List<ValidationError> validateRequest(@NonNull final RequestDto requestDto) {
        return requestDtoValidatorImpl.validate(requestDto);
    }
}
