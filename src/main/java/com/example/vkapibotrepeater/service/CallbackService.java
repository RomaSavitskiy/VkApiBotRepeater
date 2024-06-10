package com.example.vkapibotrepeater.service;

import com.example.vkapibotrepeater.model.dto.CallbackTo;

/**
 * Interface for handling VK API callbacks.
 * <br>
 * This interface defines a method for handling VK API callbacks. Implementations of this interface
 * are responsible for processing various types of callbacks received from the VK API.
 *
 * @author Roman Savitski
 * @since 1.0
 */
public interface CallbackService {

    /**
     * Handles the VK API callback by processing the provided {@link CallbackTo} object.
     *
     * @param callbackTo the callback object containing the information about the callback
     * @return a response string to be sent back to the VK API
     */
    String handleCallback(CallbackTo callbackTo);
}
