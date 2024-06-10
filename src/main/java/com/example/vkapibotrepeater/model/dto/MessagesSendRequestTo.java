package com.example.vkapibotrepeater.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for "messages.send" request via VK API.
 * <br>
 * This class encapsulates the parameters required for a message send request to the VK API.
 * It uses Lombok annotations to generate boilerplate code like getters, setters, and builders.
 * It also uses Jackson annotations for JSON property mapping and ordering.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Builder
@Getter
@Setter
@JsonPropertyOrder(alphabetic = true)
public class MessagesSendRequestTo implements Serializable {

    @JsonProperty(value = "user_id")
    Long userId;

    @JsonProperty(value = "random_id")
    Long randomId;

    @JsonProperty(value = "peer_id")
    Long peerId;

    String domain;

    @JsonProperty(value = "chat_id")
    Long chatId;

    @JsonProperty(value = "user_ids")
    List<Long> userIds;

    String message;

    Double lat;

    @JsonProperty(value = "long")
    Double longField;

    String attachment;

    @JsonProperty(value = "reply_to")
    Long replyTo;

    @JsonProperty(value = "forward_messages")
    String forwardMessages;

    @JsonProperty(value = "sticker_id")
    Long stickerId;

    @JsonProperty(value = "group_id")
    Long groupId;

    Map<String, String> keyboard;

    Long payload;

    @JsonProperty(value = "dont_parse_links")
    Integer dontParseLinks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesSendRequestTo that = (MessagesSendRequestTo) o;

        if (!Objects.equals(userId, that.userId)) return false;
        if (!Objects.equals(randomId, that.randomId)) return false;
        if (!Objects.equals(peerId, that.peerId)) return false;
        if (!Objects.equals(domain, that.domain)) return false;
        if (!Objects.equals(chatId, that.chatId)) return false;
        if (!Objects.equals(userIds, that.userIds)) return false;
        if (!Objects.equals(message, that.message)) return false;
        if (!Objects.equals(lat, that.lat)) return false;
        if (!Objects.equals(longField, that.longField)) return false;
        if (!Objects.equals(attachment, that.attachment)) return false;
        if (!Objects.equals(replyTo, that.replyTo)) return false;
        if (!Objects.equals(forwardMessages, that.forwardMessages))
            return false;
        if (!Objects.equals(stickerId, that.stickerId)) return false;
        if (!Objects.equals(groupId, that.groupId)) return false;
        if (!Objects.equals(keyboard, that.keyboard)) return false;
        if (!Objects.equals(payload, that.payload)) return false;
        return Objects.equals(dontParseLinks, that.dontParseLinks);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (randomId != null ? randomId.hashCode() : 0);
        result = 31 * result + (peerId != null ? peerId.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (userIds != null ? userIds.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (longField != null ? longField.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + (replyTo != null ? replyTo.hashCode() : 0);
        result = 31 * result + (forwardMessages != null ? forwardMessages.hashCode() : 0);
        result = 31 * result + (stickerId != null ? stickerId.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (keyboard != null ? keyboard.hashCode() : 0);
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        result = 31 * result + (dontParseLinks != null ? dontParseLinks.hashCode() : 0);
        return result;
    }

    /**
     * DTO for handling error responses from VK API.
     * <br>
     * This class encapsulates error code and error message details.
     *
     * @see Serializable
     * @since 1.0
     */
    @Getter
    public static class MessagesSendErrorResultDto {

        /**
         * The error code returned by VK API.
         */
        @JsonProperty(value = "error_code")
        Long errorCode;

        /**
         * The error message returned by VK API.
         */
        @JsonProperty(value = "error_msg")
        String errorMsg;

        public Long getErrorCode() {
            return errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }
}

