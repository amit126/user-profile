package com.tcg.service;

import com.tcg.dto.PublishAppDefinitionDTO;
import com.tcg.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AppService {

    ResponseDTO getRuntimeAppDefinitions();

    ResponseDTO getRuntimeAppDefinitionsById(int appDefinitionId);

    ResponseEntity publishRuntimeAppDefinitions(int modelId, PublishAppDefinitionDTO publishAppDefinitionDTO);

    ResponseEntity getAppDefinitionsByModelId(int modelId);
}
