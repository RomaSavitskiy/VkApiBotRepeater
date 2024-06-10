package com.example.vkapibotrepeater.service.impl;

import com.example.vkapibotrepeater.config.VkApiPropertiesConfiguration;
import com.example.vkapibotrepeater.model.dto.CallbackTo;
import com.example.vkapibotrepeater.model.entity.MessageCallback;
import com.example.vkapibotrepeater.model.dto.MessagesSendRequestTo;
import com.example.vkapibotrepeater.repository.MessageRepository;
import com.example.vkapibotrepeater.service.CallbackService;
import com.example.vkapibotrepeater.service.MessageSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Service implementation for handling callbacks from the VK API.
 * <br>
 * This service handles different types of callbacks received from VK API, such as new messages and confirmations.
 * It uses {@code VkApiPropertiesConfiguration} for VK API properties, {@code MessageRepository} for message persistence,
 * and {@code MessageSenderService} for sending responses.
 *
 * @see VkApiPropertiesConfiguration
 * @see MessageRepository
 * @see MessageSenderService
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CallbackServiceImpl implements CallbackService {
    private final VkApiPropertiesConfiguration vkApiPropertiesConfiguration;
    private final MessageRepository messageNewCallbackRepository;
    private final MessageSenderService<MessagesSendRequestTo> messageSenderService;

    /**
     * Handles the incoming callback and performs appropriate actions based on the callback type.
     *
     * @param callbackTo the callback object received from VK API
     * @return a response to the callback
     * @throws InvalidParameterException if the secret provided in the callback does not match the configured secret
     * @throws UnsupportedOperationException if the callback type is not supported
     */
    @Override
    public String handleCallback(CallbackTo callbackTo) {
        validateSecret(callbackTo);
        switch (Objects.requireNonNull(callbackTo.getType())) {
            case confirmation -> {
                return vkApiPropertiesConfiguration.getConfirmation();
            }
            case message_new -> {
                MessageCallback messageCallback = parseMessageCallbackFromCallbackTo(callbackTo);
                saveAndSendResponseForNewMessage(messageCallback);
                return "ok";
            }
            default -> {
                throw new UnsupportedOperationException("Service supports only 'message_new' callback type");
            }
        }
    }

    /**
     * Validates the secret provided in the callback against the configured secret.
     *
     * @param callbackTo the callback object received from VK API
     * @throws InvalidParameterException if the secret provided in the callback does not match the configured secret
     */
    private void validateSecret(CallbackTo callbackTo) {
        if (!vkApiPropertiesConfiguration.getSecret().equals(callbackTo.getSecret())) {
            throw new InvalidParameterException("Invalid secret");
        }
    }

    /**
     * Saves the incoming message callback and sends a response.
     *
     * @param messageCallback the message callback object
     */
    private void saveAndSendResponseForNewMessage(MessageCallback messageCallback) {
        MessageCallback savedMessageCallback = messageNewCallbackRepository.save(messageCallback);
        MessagesSendRequestTo dto = MessagesSendRequestTo.builder()
                    .peerId(savedMessageCallback.getPeerId())
                    .message("Вы сказали: ".concat(savedMessageCallback.getText()))
                    .groupId(savedMessageCallback.getGroupId())
                .build();
        messageSenderService.send(dto);
    }

    /**
     * Parses the incoming callback object to extract message-related information.
     *
     * @param callbackTo the callback object received from VK API
     * @return a {@code MessageCallback} object representing the parsed message callback
     */
    private MessageCallback parseMessageCallbackFromCallbackTo(CallbackTo callbackTo) {
        Map<String, LinkedHashMap<String, Object>> map = callbackTo.getObject();
        Map<String, Object> messageMap = map.get("message");
        return MessageCallback.builder()
                    .id(Long.parseLong(String.valueOf(messageMap.get("id"))))
                    .date(Long.parseLong(String.valueOf(messageMap.get("date"))))
                    .peerId(Long.parseLong(String.valueOf(messageMap.get("peer_id"))))
                    .fromId(Long.parseLong(String.valueOf(messageMap.get("from_id"))))
                    .text(String.valueOf(messageMap.get("text")))
                    .groupId(callbackTo.getGroupId())
                .build();
    }
}

