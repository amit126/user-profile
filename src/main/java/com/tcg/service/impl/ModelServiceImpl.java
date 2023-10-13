package com.tcg.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcg.dto.AppDefinitionsDTO;
import com.tcg.dto.ModelsDTO;
import com.tcg.dto.ResponseDTO;
import com.tcg.dto.SuccessResponseDTO;
import com.tcg.config.Config;
import com.tcg.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelServiceImpl implements ModelService {

    private final Config config;

    private final ModelMapper modelMapper;

    @Value("${alfresco.server.url}")
    private String alfrescoServerUrl;

    @Override
    public ResponseDTO getAllModels() {
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/models";
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        List<ModelsDTO> modelsDTOList = null;
        try {
            // Make the HTTP request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Extract the 'data' field as a list of AppDTO
            JsonNode dataNode = jsonNode.get("data");
            modelsDTOList = objectMapper.convertValue(dataNode, new TypeReference<List<ModelsDTO>>() {});
        } catch (JsonProcessingException e) {
            log.error("getAllModels() error while parse the data : " + e);
        }

        return new SuccessResponseDTO<>(modelsDTOList);
    }

    @Override
    public ResponseDTO getModelsForAppDefinition() {
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/models-for-app-definition";
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        List<ModelsDTO> modelsDTOList = null;
        try {
            // Make the HTTP request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Extract the 'data' field as a list of AppDTO
            JsonNode dataNode = jsonNode.get("data");
            modelsDTOList = objectMapper.convertValue(dataNode, new TypeReference<List<ModelsDTO>>() {});
        } catch (JsonProcessingException e) {
            log.error("getAllModels() error while parse the data : " + e);
        }

        return new SuccessResponseDTO<>(modelsDTOList);
    }

    @Override
    public ResponseDTO getModelsById(int modelId) {
        log.info("getRuntimeAppDefinitionsById() starts ...");

        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/models/"+ modelId;

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

        return new SuccessResponseDTO<>(modelMapper.map(map, ModelsDTO.class));
    }
}
