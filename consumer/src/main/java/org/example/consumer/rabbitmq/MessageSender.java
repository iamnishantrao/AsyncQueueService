package org.example.consumer.rabbitmq;

import lombok.NonNull;
import org.example.consumer.config.RabbitMqConfigReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @NonNull private final RabbitTemplate rabbitTemplate;
    @NonNull private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public MessageSender(@NonNull final RabbitTemplate rabbitTemplate,
                         @NonNull final RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    public void sendMessage(@NonNull final String message) {
        rabbitTemplate.convertAndSend(rabbitMqConfigReader.getExchange(), rabbitMqConfigReader.getResponseRoutingKey(), message);
    }
}
