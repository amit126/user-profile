package com.tcg.controller;

import com.tcg.dto.PublishAppDefinitionDTO;
import com.tcg.dto.ResponseDTO;
import com.tcg.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AppController {

    private final AppService appService;

    @GetMapping("/enterprise/runtime-app-definitions")
    public ResponseDTO getRuntimeAppDefinitions() {
        log.info("getRuntimeAppDefinitions starts...");

        return appService.getRuntimeAppDefinitions();
    }

    @GetMapping("/enterprise/runtime-app-definitions/{appDefinitionId}")
    public ResponseDTO getRuntimeAppDefinitionsById(@PathVariable int appDefinitionId) {
        log.info("getRuntimeAppDefinitionsById starts...");

        return appService.getRuntimeAppDefinitionsById(appDefinitionId);
    }

    @PostMapping("/enterprise/app-definitions/{modelId}/publish")
    public ResponseEntity publishRuntimeAppDefinitions(@PathVariable int modelId, @RequestBody PublishAppDefinitionDTO publishAppDefinitionDTO) {
        log.info("publishRuntimeAppDefinitions starts...");

        return appService.publishRuntimeAppDefinitions(modelId, publishAppDefinitionDTO);
    }

    @GetMapping("/enterprise/app-definitions/{modelId}")
    public ResponseEntity getAppDefinitionsByModelId(@PathVariable int modelId) {
        log.info("getAppDefinitionsByModelId starts...");

        return appService.getAppDefinitionsByModelId(modelId);
    }
}
