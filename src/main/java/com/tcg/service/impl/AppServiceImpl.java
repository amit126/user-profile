package com.tcg.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcg.dto.AppDefinitionsDTO;
import com.tcg.dto.PublishAppDefinitionDTO;
import com.tcg.dto.ResponseDTO;
import com.tcg.dto.SuccessResponseDTO;
import com.tcg.service.AppService;
import com.tcg.config.Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppServiceImpl implements AppService {

    @Value("${alfresco.server.url}")
    private String alfrescoServerUrl;

    private final ModelMapper modelMapper;

    private final Config config;

    @Override
    public ResponseDTO getRuntimeAppDefinitions() {
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/runtime-app-definitions";
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        List<AppDefinitionsDTO> appDefinitionsDTOList = null;
        try {
            // Make the HTTP request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Extract the 'data' field as a list of AppDTO
            JsonNode dataNode = jsonNode.get("data");
            appDefinitionsDTOList = objectMapper.convertValue(dataNode, new TypeReference<List<AppDefinitionsDTO>>() {});
        } catch (JsonProcessingException e) {
            log.error("getRuntimeAppDefinitions() error while parse the data : " + e);
        }

        return new SuccessResponseDTO<>(appDefinitionsDTOList);
    }

    @Override
    public ResponseDTO getRuntimeAppDefinitionsById(int appDefinitionId) {
        log.info("getRuntimeAppDefinitionsById() starts ...");

        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/runtime-app-definitions/"+ appDefinitionId;

        //Get authorization headers
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> map = null;
        try {
            // Make the HTTP request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
            map = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            log.error("getRuntimeAppDefinitionsById() error while parse the data : " + e);
        }

        //Return empty success response dto.
        if (CollectionUtils.isEmpty(map)) {
            return new SuccessResponseDTO<>("No Data Found");
        }

        return new SuccessResponseDTO<>(modelMapper.map(map, AppDefinitionsDTO.class));
    }

    @Override
    public ResponseEntity publishRuntimeAppDefinitions(int modelId, PublishAppDefinitionDTO publishAppDefinitionDTO) {
        log.info("getRuntimeAppDefinitionsById() starts ...");
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/app-definitions/" + modelId + "/publish";

        //Get authorization headers
        HttpHeaders headers = config.getAuthorizationHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set content type to application/json

        // Assuming taskDTO is a JSON-serializable object, you can convert it to JSON using an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String taskDTOJson = null;
        try {
            taskDTOJson = objectMapper.writeValueAsString(publishAppDefinitionDTO);
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

    @Override
    public ResponseEntity getAppDefinitionsByModelId(int modelId) {
        log.info("getRuntimeAppDefinitionsById() starts ...");

        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/app-definitions/"+ modelId;

        //Get authorization headers
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }
}
