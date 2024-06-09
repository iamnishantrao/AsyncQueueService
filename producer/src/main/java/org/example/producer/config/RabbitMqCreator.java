package org.example.producer.config;

import lombok.NonNull;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqCreator {

    @NonNull private final RabbitMqConfigReader rabbitMqConfigReader;

    @Autowired
    public RabbitMqCreator(@NonNull final RabbitMqConfigReader rabbitMqConfigReader) {
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
    public Binding createRequestBinding(@NonNull final Queue createRequestQueue,
                                        @NonNull final TopicExchange createExchange) {
        return BindingBuilder
                .bind(createRequestQueue)
                .to(createExchange)
                .with(rabbitMqConfigReader.getRequestRoutingKey());
    }

    @Bean
    public Binding createResponseBinding(@NonNull final Queue createResponseQueue,
                                         @NonNull final TopicExchange createExchange) {
        return BindingBuilder
                .bind(createResponseQueue)
                .to(createExchange)
                .with(rabbitMqConfigReader.getResponseRoutingKey());
    }
}
