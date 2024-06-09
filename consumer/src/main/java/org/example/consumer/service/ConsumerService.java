package org.example.consumer.service;

import org.example.consumer.rabbitmq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final MessageSender messageSender;

    @Autowired
    public ConsumerService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void produceResponse(String message) {
        messageSender.sendMessage(message);
    }
}
