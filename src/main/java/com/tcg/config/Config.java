package com.tcg.config;

import com.tcg.constants.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Base64;

@Slf4j
@Configuration
public class Config {

    @Value("${alfresco.server.url}")
    private String alfrescoServerUrl;

    @Value("${alfresco.admin.username}")
    private String username;

    @Value("${alfresco.admin.password}")
    private String password;

    @Bean
    public HttpHeaders getAuthorizationHeaders() {
        log.info("getAuthorizationHeaders() starts ...");

        // Set up HTTP headers with the Basic Authentication
        HttpHeaders headers = new HttpHeaders();
        String authHeader = ApplicationConstants.CONSTANTS_BASIC + " " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        headers.set(ApplicationConstants.CONSTANTS_AUTHORIZATION, authHeader);
        return headers;
    }
}
