package org.example.consumer.config;

import lombok.NonNull;
import org.example.commons.messaging.MessageReceiver;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.example.consumer.messaging.MessageReceiverImpl.LISTENER_METHOD;

@Configuration
public class MessageListener {

    @NonNull private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public MessageListener(@NonNull final RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(@NonNull final ConnectionFactory connectionFactory,
                                                                         @NonNull final MessageListenerAdapter messageListenerAdapter) {
        final SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(rabbitMqConfigReader.getRequestQueue());
        simpleMessageListenerContainer.setDefaultRequeueRejected(false);
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(@NonNull final MessageReceiver messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, LISTENER_METHOD);
    }
}

