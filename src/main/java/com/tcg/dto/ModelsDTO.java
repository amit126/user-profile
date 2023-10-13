package com.tcg.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelsDTO {

    private long id;
    private String name;
    private String description;
    private long createdBy;
    private String createdByFullName;
    private long lastUpdatedBy;
    private String lastUpdatedByFullName;
    private String lastUpdated;
    private boolean latestVersion;
    private int version;
    private String comment;
    private int stencilSet;
    private String referenceId;
    private int modelType;
    private String favorite;
    private String permission;
    private long tenantId;
}
