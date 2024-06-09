package com.example.vkapibotrepeater.controller;

import com.example.vkapibotrepeater.model.dto.CallbackTo;
import com.example.vkapibotrepeater.service.CallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/callbacks")
@RequiredArgsConstructor
public class CallbackController {
    private final CallbackService callbackService;

    @PostMapping
    public ResponseEntity<String> handleCallback(@RequestBody CallbackTo callbackTo) {
        return new ResponseEntity<>(callbackService.handleCallback(callbackTo), HttpStatus.OK);
    }
}
