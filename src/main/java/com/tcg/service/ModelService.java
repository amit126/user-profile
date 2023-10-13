package com.tcg.service;

import com.tcg.dto.ResponseDTO;

public interface ModelService {
    ResponseDTO getAllModels();

    ResponseDTO getModelsForAppDefinition();

    ResponseDTO getModelsById(int modelId);
}
