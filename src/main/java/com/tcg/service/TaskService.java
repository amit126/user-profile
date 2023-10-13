package com.tcg.service;

import com.tcg.dto.TaskDTO;
import org.springframework.http.ResponseEntity;

public interface TaskService {

    ResponseEntity completeTask(int taskId, TaskDTO taskDTO);
}
