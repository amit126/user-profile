package com.tcg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableDTO {

    private long numPages;
    @JsonProperty("isLast")
    private boolean isLast;
    private long pageNumber;
}
