package org.example.producer.messaging;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.messaging.MessageSender;
import org.example.producer.config.RabbitMqConfigReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSenderImpl implements MessageSender {

    @NonNull private final RabbitTemplate rabbitTemplate;
    @NonNull private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public MessageSenderImpl(@NonNull final RabbitTemplate rabbitTemplate,
                             @NonNull final RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    @Override
    public void sendMessage(@NonNull final String message) {
        rabbitTemplate.convertAndSend(
                rabbitMqConfigReader.getExchange(),
                rabbitMqConfigReader.getRequestRoutingKey(),
                message);
    }
}
