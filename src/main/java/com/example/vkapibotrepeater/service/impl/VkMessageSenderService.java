package com.example.vkapibotrepeater.service.impl;

import com.example.vkapibotrepeater.exceptions.MessageSenderException;
import com.example.vkapibotrepeater.model.dto.MessagesSendRequestTo;
import com.example.vkapibotrepeater.model.dto.MessagesSendResponseTo;
import com.example.vkapibotrepeater.service.MessageSenderService;
import com.example.vkapibotrepeater.service.VkUriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.net.URI;
import java.util.*;

/**
 * Service implementation for sending messages via the VK API.
 * <br>
 * This service is responsible for sending messages via the VK API.
 * It implements the {@link MessageSenderService} interface, providing functionality to split long messages and send them asynchronously.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class VkMessageSenderService implements MessageSenderService<MessagesSendRequestTo> {
    private static final int TEXT_MAX_LENGTH = 2000;
    private final RestTemplate restTemplate;
    private final VkUriCreator vkUriCreator;

    /**
     * Sends a message or a series of messages asynchronously.
     *
     * @param messagesSendRequestTo the request object containing the message(s) to send
     * @throws IllegalArgumentException if the request object is null
     */
    @Override
    public void send(MessagesSendRequestTo messagesSendRequestTo) {
        if (messagesSendRequestTo == null) {
            throw new IllegalArgumentException("messagesSendRequestTo cannot be null");
        }

        List<MessagesSendRequestTo> messageParts = splitMessageIfTooLong(messagesSendRequestTo);
        messageParts.forEach(this::sendInternal);
    }

    /**
     * Splits the message into parts if it exceeds the maximum length allowed by VK API.
     *
     * @param dto the message request DTO
     * @return a list of message request DTOs with message text split into parts
     */
    private List<MessagesSendRequestTo> splitMessageIfTooLong(MessagesSendRequestTo dto) {
        String inputMessage = dto.getMessage();
        if (StringUtils.isEmpty(inputMessage)) {
            return Collections.singletonList(dto);
        }

        int capacity = (inputMessage.length() / TEXT_MAX_LENGTH) + 1;
        List<MessagesSendRequestTo> messageParts = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            int startIndex = i * TEXT_MAX_LENGTH;
            int endIndex = Math.min((i + 1) * TEXT_MAX_LENGTH, inputMessage.length());
            String part = StringUtils.substring(inputMessage, startIndex, endIndex);
            MessagesSendRequestTo partDto = copyWithNewMessage(dto, part);
            messageParts.add(partDto);
        }
        return messageParts;
    }

    /**
     * Creates a new message request DTO with the given message text.
     *
     * @param dto     the original message request DTO
     * @param message the message text for the new DTO
     * @return a new message request DTO with the specified message text
     */
    private static MessagesSendRequestTo copyWithNewMessage(MessagesSendRequestTo dto, String message) {
        return MessagesSendRequestTo.builder()
                    .userId(dto.getUserId())
                    .peerId(dto.getPeerId())
                    .domain(dto.getDomain())
                    .chatId(dto.getChatId())
                    .userIds(dto.getUserIds())
                    .message(message)
                    .lat(dto.getLat())
                    .longField(dto.getLongField())
                    .attachment(dto.getAttachment())
                    .replyTo(dto.getReplyTo())
                    .forwardMessages(dto.getForwardMessages())
                    .stickerId(dto.getStickerId())
                    .groupId(dto.getGroupId())
                    .keyboard(dto.getKeyboard())
                    .payload(dto.getPayload())
                    .dontParseLinks(dto.getDontParseLinks())
                .build();
    }

    /**
     * Sends a message request DTO to the VK API.
     *
     * @param messagesSendRequestTo the message request DTO to send
     * @throws IllegalArgumentException if the request object is null
     * @throws MessageSenderException if an error occurs while sending the message
     */
    private void sendInternal(MessagesSendRequestTo messagesSendRequestTo) {
        if (messagesSendRequestTo == null) {
            throw new IllegalArgumentException("messagesSendRequestTo cannot be null");
        }

        messagesSendRequestTo.setRandomId(System.currentTimeMillis());
        URI uri = vkUriCreator.createUri(messagesSendRequestTo);
        ResponseEntity<MessagesSendResponseTo> responseEntity = restTemplate.postForEntity
                (uri, null, MessagesSendResponseTo.class);
        validateResponse(responseEntity);
    }

    /**
     * Validates the response from the VK API.
     *
     * @param responseEntity the response entity received from the VK API
     * @throws NullPointerException   if the response body is null
     * @throws MessageSenderException if the VK API returns an error
     */
    private void validateResponse(ResponseEntity<MessagesSendResponseTo> responseEntity) {
        MessagesSendResponseTo.MessagesSendErrorResultDto error = Objects.requireNonNull(responseEntity.getBody()).getError();
        if (error != null && error.getErrorCode() != null) {
            throw new MessageSenderException(error.getErrorMsg());
        }
    }
}

