package org.example.producer.exception;

import lombok.NonNull;

public class MalformedRequestException extends RuntimeException {

    public MalformedRequestException(@NonNull final String message) {
        super(message);
    }

    public MalformedRequestException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
