package org.example.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.model.RequestModel;
import org.example.consumer.rabbitmq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @NonNull private final MessageSender messageSender;
    @NonNull private final ObjectMapper objectMapper;

    @Autowired
    public ConsumerService(@NonNull final MessageSender messageSender,
                           @NonNull final ObjectMapper objectMapper) {
        this.messageSender = messageSender;
        this.objectMapper = objectMapper;
    }

    public void consumeMessage(@NonNull final String message) {
        try {
            log.debug("Consuming message: {}", message);
            final RequestModel model = objectMapper.readValue(message, RequestModel.class);
            log.info("Processing requestId: {}", model.getRequestId());
            // Some processing

            // Start generating response after processing is complete
            log.info("Sending response for requestId: {}", model.getRequestId());
            produceResponse("Message processed successfully.");
            log.info("Processed RequestId: {}.", model.getRequestId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void produceResponse(@NonNull final String message) {
        messageSender.sendMessage(message);
    }
}
