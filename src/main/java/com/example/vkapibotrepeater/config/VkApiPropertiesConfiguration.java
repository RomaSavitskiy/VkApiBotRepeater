package com.example.vkapibotrepeater.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@PropertySource(value = "classpath:vk.properties")
@ConfigurationProperties(prefix = "vk.api")
public class VkApiPropertiesConfiguration {

    private String accessToken;

    private Double v;

    private String secret;

    private String confirmation;
}
