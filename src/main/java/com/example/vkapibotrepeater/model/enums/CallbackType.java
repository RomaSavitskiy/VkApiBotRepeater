package com.example.vkapibotrepeater.model.enums;

/**
 * Enum representing the type of callback event received from the VK API.
 * <br>
 * This enum defines different types of callback events that can be received from VK API callbacks.
 * These events include new messages, message confirmations, message reads, message replies, and typing states.
 *
 * @author Roman Savitski
 * @since 1.0
 */
public enum CallbackType {
    message_new,
    confirmation,
    message_read,
    message_reply,
    message_typing_state
}
