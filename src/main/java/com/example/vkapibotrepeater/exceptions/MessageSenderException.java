package com.example.vkapibotrepeater.exceptions;

/**
 * Custom exception class for handling errors in message sending operations.
 * <br>
 * This exception is thrown to indicate issues encountered while sending messages.
 * It extends {@link RuntimeException}, so it is an unchecked exception.
 *
 * <p>There are two constructors available:
 * <ul>
 *   <li>{@link #MessageSenderException(String)}: Accepts a custom error message.</li>
 *   <li>{@link #MessageSenderException(Throwable)}: Accepts a throwable cause.</li>
 * </ul>
 *
 * @see RuntimeException
 *
 * @author Roman Savitski
 * @since 1.0
 */
public class MessageSenderException extends RuntimeException {

    /**
     * Constructs a new {@code MessageSenderException} with the specified detail message.
     *
     * @param message the detail message
     */
    public MessageSenderException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code MessageSenderException} with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public MessageSenderException(Throwable cause) {
        super(cause);
    }
}

