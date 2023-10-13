package com.tcg.controller;

import com.tcg.dto.ResponseDTO;
import com.tcg.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/enterprise/models")
    public ResponseDTO getAllModels() {
        log.info("getAllModels starts...");

        return modelService.getAllModels();
    }

    @GetMapping("/enterprise/models-for-app-definition")
    public ResponseDTO getModelsForAppDefinition() {
        log.info("getModelsForAppDefinition starts...");

        return modelService.getModelsForAppDefinition();
    }

    @GetMapping("/enterprise/models/{modelId}")
    public ResponseDTO getModelsById(@PathVariable int modelId) {
        log.info("getModelsById starts...");

        return modelService.getModelsById(modelId);
    }
}
