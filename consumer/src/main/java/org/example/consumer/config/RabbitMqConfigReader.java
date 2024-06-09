package org.example.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rabbit.conf")
public class RabbitMqConfigReader {

    private String requestQueue;
    private String responseQueue;
    private String exchange;
    private String requestRoutingKey;
    private String responseRoutingKey;
}
