package org.example.producer.service;

import org.example.commons.models.RequestModel;
import org.example.producer.messaging.MessageSender;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final MessageSender messageSender;

    public ProducerService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public String produceRequest(RequestModel requestModel) {
        messageSender.sendMessage(requestModel);
        return "Message produced successfully: " + requestModel;
    }

    public void consumeResponse(String response) {
        System.out.println("Message consumed successfully: " + response);
    }
}
