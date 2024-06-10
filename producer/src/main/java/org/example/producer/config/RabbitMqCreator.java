package org.example.producer.config;

import lombok.NonNull;
import org.springframework.amqp.core.*;
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
        return QueueBuilder
                .durable(rabbitMqConfigReader.getRequestQueue())
                .withArgument("x-dead-letter-exchange", rabbitMqConfigReader.getDlqExchange())
                .build();
    }

    @Bean Queue createRequestQueueDLQ() {
        return QueueBuilder
                .durable(rabbitMqConfigReader.getRequestQueueDlq())
                .build();
    }

    @Bean
    public Queue createResponseQueue() {
        return QueueBuilder
                .durable(rabbitMqConfigReader.getResponseQueue())
                .build();
    }

    @Bean
    public TopicExchange createExchange() {
        return new TopicExchange(rabbitMqConfigReader.getExchange());
    }

    @Bean
    public FanoutExchange createDlqFanoutExchange() {
        return new FanoutExchange(rabbitMqConfigReader.getDlqExchange());
    }

    @Bean
    public Binding createDeadLetterBinding(@NonNull final Queue createRequestQueueDLQ,
                                           @NonNull final FanoutExchange createDlqFanoutExchange) {
        return BindingBuilder
                .bind(createRequestQueueDLQ)
                .to(createDlqFanoutExchange);
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
