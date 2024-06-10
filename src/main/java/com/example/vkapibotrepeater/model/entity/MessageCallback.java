package com.example.vkapibotrepeater.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Entity representing a message callback from the VK API.
 * <br>
 * This class encapsulates the details of a message received from VK API callbacks.
 * It uses Lombok annotations to generate boilerplate code like constructors and getters.
 * It also uses JPA annotations for ORM (Object-Relational Mapping).
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageCallback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long date;

    @Column(nullable = false)
    private Long peerId;

    @Column(nullable = false)
    private Long fromId;

    @Column(columnDefinition="TEXT", nullable = false)
    private String text;

    @Column
    private Long groupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageCallback that = (MessageCallback) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(date, that.date)) return false;
        if (!Objects.equals(peerId, that.peerId)) return false;
        if (!Objects.equals(fromId, that.fromId)) return false;
        if (!Objects.equals(text, that.text)) return false;
        return Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (peerId != null ? peerId.hashCode() : 0);
        result = 31 * result + (fromId != null ? fromId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }
}
