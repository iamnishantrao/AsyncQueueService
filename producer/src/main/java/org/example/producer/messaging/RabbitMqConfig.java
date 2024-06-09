package org.example.producer.messaging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbit.conf")
@Getter @Setter
public class RabbitMqConfig {

    private String requestQueue;
    private String responseQueue;
    private String exchange;
    private String requestRoutingKey;
    private String responseRoutingKey;

    @Bean
    public Queue createRequestQueue() {
        return new Queue(requestQueue, false);
    }

    @Bean
    public Queue createResponseQueue() {
        return new Queue(responseQueue, false);
    }

    @Bean
    public TopicExchange createExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding createRequestBinding(Queue createRequestQueue, TopicExchange createExchange) {
        return BindingBuilder.bind(createRequestQueue).to(createExchange).with(requestRoutingKey);
    }

    @Bean
    public Binding createResponseBinding(Queue createResponseQueue, TopicExchange createExchange) {
        return BindingBuilder.bind(createResponseQueue).to(createExchange).with(responseRoutingKey);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(responseQueue);
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(MessageReceiver messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "receiveMessage");
    }
}
