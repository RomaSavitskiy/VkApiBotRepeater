package com.example.vkapibotrepeater.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for application beans.
 * <br>
 * This class defines configuration for creating and managing REST client and JSON object mapper beans.
 * It includes methods for configuring a RestTemplate for HTTP requests and an ObjectMapper for JSON serialization and deserialization.
 *
 * @author Roman Savitski
 * @since 1.0
 */
@Configuration
public class RestClientConfiguration {

    /**
     * Creates a {@link RestTemplate} bean for making RESTful web service calls.
     *
     * @return a configured {@link RestTemplate} instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Creates an {@link ObjectMapper} bean for JSON serialization and deserialization.
     * The ObjectMapper is configured to:
     *  - Accept single values as arrays during deserialization.
     *  - Exclude null values from JSON serialization.
     *
     * @return a configured {@link ObjectMapper} instance
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }
}
