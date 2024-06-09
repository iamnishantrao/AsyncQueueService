package org.example.producer.messaging;

import lombok.NonNull;
import org.example.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    public static final String LISTENER_METHOD = "receiveMessage";

    @NonNull private final ProducerService producerService;

    @Autowired
    public MessageReceiver(@NonNull final ProducerService producerService) {
        this.producerService = producerService;
    }

    public void receiveMessage(@NonNull final String message) {
        producerService.consumeResponse(message);
    }
}
