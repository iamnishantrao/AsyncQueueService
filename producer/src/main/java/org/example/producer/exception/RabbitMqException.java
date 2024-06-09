package org.example.producer.exception;

import lombok.NonNull;

public class RabbitMqException extends RuntimeException {
    public RabbitMqException(@NonNull final String message) {
        super(message);
    }

    public RabbitMqException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
