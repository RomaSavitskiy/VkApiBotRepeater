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

@Service
@RequiredArgsConstructor
public class CallbackServiceImpl implements CallbackService {
    private final VkApiPropertiesConfiguration vkApiPropertiesConfiguration;
    private final MessageRepository messageNewCallbackRepository;
    private final MessageSenderService<MessagesSendRequestTo> messageSenderService;

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

    private void validateSecret(CallbackTo callbackTo) {
        if (!vkApiPropertiesConfiguration.getSecret().equals(callbackTo.getSecret())) {
            throw new InvalidParameterException("Invalid secret");
        }
    }

    private void saveAndSendResponseForNewMessage(MessageCallback messageCallback) {
        MessageCallback savedMessageCallback = messageNewCallbackRepository.save(messageCallback);
        MessagesSendRequestTo dto = MessagesSendRequestTo.builder()
                    .peerId(savedMessageCallback.getPeerId())
                    .message("Вы сказали: ".concat(savedMessageCallback.getText()))
                    .groupId(savedMessageCallback.getGroupId())
                .build();
        messageSenderService.send(dto);
    }

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

