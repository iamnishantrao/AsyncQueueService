package org.example.producer.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.example.commons.model.RequestModel;
import org.example.producer.config.RabbitMqConfigReader;
import org.example.producer.exception.MalformedRequestException;
import org.example.producer.exception.RabbitMqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
    @NonNull private final RabbitTemplate rabbitTemplate;
    @NonNull private final RabbitMqConfigReader rabbitMqConfigReader;
    @NonNull private final ObjectMapper objectMapper;

    @Autowired
    public MessageSender(@NonNull final RabbitTemplate rabbitTemplate,
                         @NonNull final RabbitMqConfigReader rabbitMqConfigReader,
                         @NonNull final ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfigReader = rabbitMqConfigReader;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(@NonNull final RequestModel requestModel) throws MalformedRequestException, RabbitMqException {
        try {
            log.info("Start dispatching requestId: {}", requestModel.getRequestId());
            final String serialized = this.objectMapper.writeValueAsString(requestModel);
            rabbitTemplate.convertAndSend(
                    rabbitMqConfigReader.getExchange(),
                    rabbitMqConfigReader.getRequestRoutingKey(),
                    serialized);
            log.info("Successfully dispatched requestId: {}", requestModel.getRequestId());
        } catch (final JsonProcessingException e) {
            throw new MalformedRequestException("Not able to serialize request.", e);
        } catch (final AmqpException e) {
            throw new RabbitMqException("Not able to send message to request queue.", e);
        }
    }
}
