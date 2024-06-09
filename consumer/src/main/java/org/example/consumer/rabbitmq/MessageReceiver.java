package org.example.consumer.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commons.model.RequestModel;
import org.example.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final ConsumerService consumerService;

    @Autowired
    public MessageReceiver(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    public void receiveMessage(String message) {
        System.out.println(message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel model = objectMapper.readValue(message, RequestModel.class);
            System.out.println(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        consumerService.produceResponse("Message Consumed!");
    }
}
