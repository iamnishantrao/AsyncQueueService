package org.example.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqCreator {

    private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public RabbitMqCreator(RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    @Bean
    public Queue createRequestQueue() {
        return new Queue(rabbitMqConfigReader.getRequestQueue(), false);
    }

    @Bean
    public Queue createResponseQueue() {
        return new Queue(rabbitMqConfigReader.getResponseQueue(), false);
    }

    @Bean
    public TopicExchange createExchange() {
        return new TopicExchange(rabbitMqConfigReader.getExchange());
    }

    @Bean
    public Binding createRequestBinding(Queue createRequestQueue, TopicExchange createExchange) {
        return BindingBuilder
                .bind(createRequestQueue)
                .to(createExchange)
                .with(rabbitMqConfigReader.getRequestRoutingKey());
    }

    @Bean
    public Binding createResponseBinding(Queue createResponseQueue, TopicExchange createExchange) {
        return BindingBuilder
                .bind(createResponseQueue)
                .to(createExchange)
                .with(rabbitMqConfigReader.getResponseRoutingKey());
    }
}
