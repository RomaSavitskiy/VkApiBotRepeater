package com.example.vkapibotrepeater.repository;

import com.example.vkapibotrepeater.model.entity.MessageCallback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageCallback, Long> {
}
