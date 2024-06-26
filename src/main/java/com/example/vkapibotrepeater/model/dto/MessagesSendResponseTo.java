package com.example.vkapibotrepeater.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for "messages.send" response via VK API.
 * <br>
 * This class encapsulates the response details from a message send request, including peer ID, message ID, and any errors that occurred.
 * It uses Lombok annotations to generate boilerplate code like constructors and getters.
 * It also uses Jackson annotations for JSON property mapping.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessagesSendResponseTo {

    @JsonProperty(value = "peer_id")
    Long peerId;

    @JsonProperty(value = "message_id")
    Long messageId;

    MessagesSendErrorResultDto error;

    @Getter
    public static class MessagesSendErrorResultDto {
        @JsonProperty(value = "error_code")
        Long errorCode;
        @JsonProperty(value = "error_msg")
        String errorMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesSendResponseTo that = (MessagesSendResponseTo) o;

        if (!Objects.equals(peerId, that.peerId)) return false;
        if (!Objects.equals(messageId, that.messageId)) return false;
        return Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        int result = peerId != null ? peerId.hashCode() : 0;
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }
}
