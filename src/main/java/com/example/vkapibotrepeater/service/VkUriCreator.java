package com.example.vkapibotrepeater.service;

import com.example.vkapibotrepeater.config.VkApiPropertiesConfiguration;
import com.example.vkapibotrepeater.exceptions.MessageSenderException;
import com.example.vkapibotrepeater.model.dto.MessagesSendRequestTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class VkUriCreator {
    private final VkApiPropertiesConfiguration vkApiProperties;
    private final ObjectMapper objectMapper;

    public URI createUri(MessagesSendRequestTo dto) {
        try {
            MultiValueMap<String, String> map = objectMapper.convertValue(dto, LinkedMultiValueMap.class);
            return UriComponentsBuilder.fromHttpUrl("https://api.vk.com/method/messages.send")
                    .queryParam("access_token", vkApiProperties.getAccessToken())
                    .queryParam("v", vkApiProperties.getV())
                    .queryParams(map)
                    .build()
                    .toUri();
        } catch (ClassCastException e) {
            throw new MessageSenderException(e);
        }
    }
}
