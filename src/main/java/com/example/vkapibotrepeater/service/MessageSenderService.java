package com.example.vkapibotrepeater.service;

/**
 * Interface for sending messages via the VK API.
 * <br>
 * This interface defines a method for sending messages via the VK API. Implementations of this interface
 * are responsible for handling the sending logic and interacting with the VK API to send messages.
 *
 * @param <T> the type of the message object to be sent
 * @author Roman Savitski
 * @since 1.0
 */
public interface MessageSenderService<T> {

    /**
     * Sends a message or a series of messages asynchronously.
     *
     * @param message the message object to be sent
     */
    void send(T message);
}
