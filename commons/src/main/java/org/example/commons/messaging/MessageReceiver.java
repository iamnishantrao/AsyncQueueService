package org.example.commons.messaging;

import lombok.NonNull;

public interface MessageReceiver {

    void receiveMessage(@NonNull final String message);
}
