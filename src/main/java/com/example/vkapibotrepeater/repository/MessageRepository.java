package com.example.vkapibotrepeater.repository;

import com.example.vkapibotrepeater.model.entity.MessageCallback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing message callbacks in the VK API context.
 * <br>
 * This interface extends the Spring Data CrudRepository interface to provide basic CRUD (Create, Read, Update, Delete) operations for managing message callbacks.
 * It is annotated with {@code @Repository} to indicate that it is a repository component in the Spring framework.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Repository
public interface MessageRepository extends CrudRepository<MessageCallback, Long> {
}
