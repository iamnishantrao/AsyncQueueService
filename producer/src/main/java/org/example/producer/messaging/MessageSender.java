package org.example.producer.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commons.models.RequestModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfig rabbitMqConfig;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, RabbitMqConfig rabbitMqConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfig = rabbitMqConfig;
    }

    public void sendMessage(RequestModel requestModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String serialized = objectMapper.writeValueAsString(requestModel);
            rabbitTemplate.convertAndSend(rabbitMqConfig.getExchange(), rabbitMqConfig.getRequestRoutingKey(), serialized);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
