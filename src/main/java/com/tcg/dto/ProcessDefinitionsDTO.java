package com.tcg.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessDefinitionsDTO {

    private String id;
    private String name;
    private String description;
    private String key;
    private String category;
    private int version;
    private String deploymentId;
    private String tenantId;
    private boolean hasStartForm;
    private List<String> metaDataValues;
}
