package org.example.producer.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.example.commons.model.RequestModel;
import org.example.producer.config.RabbitMqConfigReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @NonNull private final RabbitTemplate rabbitTemplate;
    @NonNull private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public MessageSender(@NonNull RabbitTemplate rabbitTemplate,
                         @NonNull RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    public void sendMessage(RequestModel requestModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String serialized = objectMapper.writeValueAsString(requestModel);
            rabbitTemplate.convertAndSend(rabbitMqConfigReader.getExchange(), rabbitMqConfigReader.getRequestRoutingKey(), serialized);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
