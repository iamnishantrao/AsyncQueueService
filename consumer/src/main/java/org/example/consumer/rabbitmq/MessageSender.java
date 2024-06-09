package org.example.consumer.rabbitmq;

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

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(rabbitMqConfig.getExchange(), rabbitMqConfig.getResponseRoutingKey(), message);
        System.out.println("Message sent to response queue!");
    }
}
