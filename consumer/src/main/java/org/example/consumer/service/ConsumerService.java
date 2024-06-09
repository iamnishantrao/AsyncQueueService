package org.example.consumer.service;

import lombok.NonNull;
import org.example.consumer.rabbitmq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @NonNull private final MessageSender messageSender;

    @Autowired
    public ConsumerService(@NonNull final MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void produceResponse(@NonNull final String message) {
        messageSender.sendMessage(message);
    }
}
