package com.tcg.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcg.config.Config;
import com.tcg.dto.UserProfileDTO;
import com.tcg.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    @Value("${alfresco.server.url}")
    private String alfrescoServerUrl;

    private final Config config;

    @Override
    public ResponseEntity getUserProfile() {
        log.info("getUserProfile() starts ...");
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/profile";

        //Get authorization headers
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    @Override
    public ResponseEntity addUserProfile(UserProfileDTO userProfileDTO) {
        log.info("addUserProfile() starts ...");
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/profile";

        //Get authorization headers
        HttpHeaders headers = config.getAuthorizationHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set content type to application/json

        // Assuming taskDTO is a JSON-serializable object, you can convert it to JSON using an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String taskDTOJson = null;
        try {
            taskDTOJson = objectMapper.writeValueAsString(userProfileDTO);
        } catch (JsonProcessingException e) {
            log.error("Error converting taskDTO to JSON: " + e.getMessage());
            // Handle the exception as needed
        }
        // Create a HttpEntity with the JSON data and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(taskDTOJson, headers);

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;
    }
}
