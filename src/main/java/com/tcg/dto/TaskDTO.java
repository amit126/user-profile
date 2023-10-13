package com.tcg.dto;

import lombok.*;

import java.util.Date;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Map<String, String> values;
    private String outcome;
}
