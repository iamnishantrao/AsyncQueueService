package org.example.commons.messaging;

import lombok.NonNull;

public interface MessageSender {

    void sendMessage(@NonNull final String message);
}
