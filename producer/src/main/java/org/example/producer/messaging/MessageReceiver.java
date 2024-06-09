package org.example.producer.messaging;

import org.example.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final ProducerService producerService;

    @Autowired
    public MessageReceiver(ProducerService producerService) {
        this.producerService = producerService;
    }

    public void receiveMessage(String message) {
        producerService.consumeResponse(message);
    }
}
