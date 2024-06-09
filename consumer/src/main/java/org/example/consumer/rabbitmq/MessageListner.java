package org.example.consumer.rabbitmq;

import org.example.consumer.config.RabbitMqConfigReader;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageListner {

    private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public MessageListner(RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(rabbitMqConfigReader.getRequestQueue());
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(MessageReceiver messageReceiver) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageReceiver, "receiveMessage");
        return messageListenerAdapter;
    }
}

