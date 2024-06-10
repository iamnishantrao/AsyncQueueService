package org.example.producer.messaging;

import lombok.NonNull;
import org.example.commons.messaging.MessageReceiver;
import org.example.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverImpl implements MessageReceiver {

    public static final String LISTENER_METHOD = "receiveMessage";

    @NonNull private final ProducerService producerService;

    @Autowired
    public MessageReceiverImpl(@NonNull final ProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public void receiveMessage(@NonNull final String message) {
        producerService.consumeResponse(message);
    }
}
