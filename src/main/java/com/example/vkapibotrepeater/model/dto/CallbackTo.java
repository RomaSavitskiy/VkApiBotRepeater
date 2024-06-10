package com.example.vkapibotrepeater.model.dto;

import com.example.vkapibotrepeater.model.enums.CallbackType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for handling callback messages.
 * <br>
 * This class is used to encapsulate the data received in callback requests.
 * It uses Lombok annotations to generate boilerplate code like constructors, getters, and builders.
 * It also uses Jackson annotations for JSON property mapping and ignoring unknown properties.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackTo {
    private CallbackType type;

    private Map<String, LinkedHashMap<String, Object>> object;

    @JsonProperty(value = "group_id")
    private Long groupId;

    private String secret;

    @JsonProperty(value = "event_id")
    private String eventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallbackTo that = (CallbackTo) o;

        if (type != that.type) return false;
        if (!Objects.equals(object, that.object)) return false;
        if (!Objects.equals(groupId, that.groupId)) return false;
        if (!Objects.equals(secret, that.secret)) return false;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }
}
