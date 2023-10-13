package com.tcg.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcg.config.Config;
import com.tcg.dto.AppDefinitionsDTO;
import com.tcg.dto.ProcessDefinitionsDTO;
import com.tcg.dto.ResponseDTO;
import com.tcg.dto.SuccessResponseDTO;
import com.tcg.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Value("${alfresco.server.url}")
    private String alfrescoServerUrl;

    private final ModelMapper modelMapper;

    private final Config config;

    @Override
    public ResponseDTO getProcessDefinitions() {
        String url = alfrescoServerUrl + "/activiti-app/api/enterprise/process-definitions";
        HttpHeaders headers = config.getAuthorizationHeaders();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        List<ProcessDefinitionsDTO> processDefinitionsDTOS = null;
        try {
            // Make the HTTP request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Extract the 'data' field as a list of AppDTO
            JsonNode dataNode = jsonNode.get("data");
            processDefinitionsDTOS = objectMapper.convertValue(dataNode, new TypeReference<List<ProcessDefinitionsDTO>>() {});
        } catch (JsonProcessingException e) {
            log.error("getRuntimeAppDefinitions() error while parse the data : " + e);
        }

        return new SuccessResponseDTO<>(processDefinitionsDTOS);
    }
}
