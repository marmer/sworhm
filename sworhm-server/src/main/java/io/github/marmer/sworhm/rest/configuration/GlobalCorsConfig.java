package io.github.marmer.sworhm.rest.configuration;

import lombok.Data;

import java.util.List;

@Data
public class GlobalCorsConfig {
    private List<String> allowedOrigins;
    private List<String> allowedMethods = List.of("GET", "POST", "DELETE", "PUT");
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;
    private Boolean allowCredentials;
    private Long maxAge;
}