package com.tcg.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppDefinitionsDTO {

    private Long id;
    private String defaultAppId;
    private String name;
    private String description;
    private Long modelId;
    private String theme;
    private String icon;
    private String deploymentId;
    private Long tenantId;
}
