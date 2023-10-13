package com.tcg.dto;

import com.tcg.constants.ApplicationConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@NoArgsConstructor
@Getter
public class ErrorResponseDTO implements ResponseDTO {
    private String code = String.valueOf(ApplicationConstants.HTTP_RESPONSE_ERROR_CODE);
    private Object data;
    private String message;
    private Integer status = ApplicationConstants.ERROR_STATUS_CODE;
    private long timestamp;
    protected String requestId;


    public ErrorResponseDTO(String errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
        this.timestamp = System.currentTimeMillis();
        this.requestId = MDC.get(ApplicationConstants.REQUEST_ID);
    }

    public ErrorResponseDTO(String errorCode, String errorMessage, Object data) {
        this.data = data;
        this.code = errorCode;
        this.message = errorMessage;
        this.timestamp = System.currentTimeMillis();
        this.requestId = MDC.get(ApplicationConstants.REQUEST_ID);
    }

}
