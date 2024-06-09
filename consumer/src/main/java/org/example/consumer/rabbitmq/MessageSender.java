package org.example.consumer.rabbitmq;

import org.example.consumer.config.RabbitMqConfigReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(rabbitMqConfigReader.getExchange(), rabbitMqConfigReader.getResponseRoutingKey(), message);
        System.out.println("Message sent to response queue!");
    }
}
