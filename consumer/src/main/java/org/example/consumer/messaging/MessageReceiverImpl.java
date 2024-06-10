package org.example.consumer.messaging;

import lombok.NonNull;
import org.example.commons.messaging.MessageReceiver;
import org.example.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverImpl implements MessageReceiver {

    public static final String LISTENER_METHOD = "receiveMessage";

    @NonNull private final ConsumerService consumerService;

    @Autowired
    public MessageReceiverImpl(@NonNull final ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Override
    public void receiveMessage(@NonNull final String message) {
        consumerService.consumeMessage(message);
    }
}
