package com.example.vkapibotrepeater.exceptions;

public class MessageSenderException extends RuntimeException {
    public MessageSenderException(String message) {
        super(message);
    }

    public MessageSenderException(Throwable cause) {
        super(cause);
    }
}
