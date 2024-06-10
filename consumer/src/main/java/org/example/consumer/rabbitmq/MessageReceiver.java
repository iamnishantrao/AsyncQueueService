package org.example.consumer.rabbitmq;

import lombok.NonNull;
import org.example.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    public static final String LISTENER_METHOD = "receiveMessage";

    @NonNull private final ConsumerService consumerService;

    @Autowired
    public MessageReceiver(@NonNull final ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    public void receiveMessage(@NonNull final String message) {
        consumerService.consumeMessage(message);
    }
}
