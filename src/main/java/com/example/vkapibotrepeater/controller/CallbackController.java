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

/**
 * REST controller for handling callback requests.
 * <br>
 * This controller receives callback data and processes it using {@link CallbackService}.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/callbacks")
@RequiredArgsConstructor
public class CallbackController {
    private final CallbackService callbackService;

    /**
     * Handles incoming callback requests.
     * <br>
     * This method receives a {@link CallbackTo} object as a request body,
     * processes it using {@link CallbackService#handleCallback(CallbackTo)},
     * and returns a response with the result.
     *
     * @param callbackTo the callback data
     * @return a {@link ResponseEntity} containing the result of the callback processing
     */
    @PostMapping
    public ResponseEntity<String> handleCallback(@RequestBody CallbackTo callbackTo) {
        return new ResponseEntity<>(callbackService.handleCallback(callbackTo), HttpStatus.OK);
    }
}
