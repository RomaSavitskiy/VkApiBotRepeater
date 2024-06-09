package com.example.vkapibotrepeater.service;

public interface MessageSenderService<T> {
    void send(T message);
}
