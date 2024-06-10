package com.example.vkapibotrepeater.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Configuration class for VK API properties.
 * <br>
 * This class is used to load VK API properties from the {@code vk.properties} file.
 * The properties are prefixed with {@code vk.api}.
 * <br><br>
 * Example of {@code vk.properties} file:
 * <pre>
 * vk.api.accessToken=your_access_token
 * vk.api.v=your_api_version
 * vk.api.secret=your_secret_key
 * vk.api.confirmation=your_confirmation_string
 * </pre>
 *
 * @author Roman Savitski
 * @since 1.0
 */
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
