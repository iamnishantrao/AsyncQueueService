package org.example.producer.config;

import lombok.NonNull;
import org.example.producer.messaging.MessageReceiver;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.example.producer.messaging.MessageReceiver.LISTENER_METHOD;

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
        simpleMessageListenerContainer.setQueueNames(rabbitMqConfigReader.getResponseQueue());
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        simpleMessageListenerContainer.setConcurrency(rabbitMqConfigReader.getResponseQueueConsumerConcurrency());
        simpleMessageListenerContainer.setBatchSize(rabbitMqConfigReader.getResponseQueueConsumerBatchSize());
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(@NonNull final MessageReceiver messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, LISTENER_METHOD);
    }
}
