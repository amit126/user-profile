package com.tcg.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcg.config.Config;
import com.tcg.dto.TaskDTO;
import com.tcg.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Value("${alfresco.server.url}")
    private String alfrescoServerUrl;

    private final ModelMapper modelMapper;

    private final Config config;

    @Override
    public ResponseEntity<String> completeTask(int taskId, TaskDTO taskDTO) {
        log.info("getRuntimeAppDefinitionsById() starts ...");

        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/task-forms/"+ taskId;

        //Get authorization headers
        HttpHeaders headers = config.getAuthorizationHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set content type to application/json

        // Assuming taskDTO is a JSON-serializable object, you can convert it to JSON using an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String taskDTOJson = null;
        try {
            taskDTOJson = objectMapper.writeValueAsString(taskDTO);
        } catch (JsonProcessingException e) {
            log.error("Error converting taskDTO to JSON: " + e.getMessage());
            // Handle the exception as needed
        }
        // Create a HttpEntity with the JSON data and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(taskDTOJson, headers);

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        // Check the HTTP status code and return the appropriate response
        return (response.getStatusCode() == HttpStatus.OK)
                ? ResponseEntity.ok("Success")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}
