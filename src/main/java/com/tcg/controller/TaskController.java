package com.tcg.controller;

import com.tcg.dto.TaskDTO;
import com.tcg.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/enterprise/task-forms/{taskId}")
    public ResponseEntity completeTask(@PathVariable int taskId, @RequestBody TaskDTO taskDTO) {
        log.info("completeTask starts...");

        return taskService.completeTask(taskId, taskDTO);
    }
}
