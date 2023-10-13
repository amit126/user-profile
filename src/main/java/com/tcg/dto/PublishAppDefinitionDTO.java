package com.tcg.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublishAppDefinitionDTO {

    private String comment;
    private boolean force;
}
