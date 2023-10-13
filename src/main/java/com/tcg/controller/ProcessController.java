package com.tcg.controller;

import com.tcg.dto.ResponseDTO;
import com.tcg.service.ModelService;
import com.tcg.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/enterprise/process-definitions")
    public ResponseDTO getProcessDefinitions() {
        log.info("getProcessDefinitions starts...");

        return processService.getProcessDefinitions();
    }
}
