package com.example.vkapibotrepeater.service;

import com.example.vkapibotrepeater.model.dto.CallbackTo;

public interface CallbackService {
    String handleCallback(CallbackTo callbackTo);
}
