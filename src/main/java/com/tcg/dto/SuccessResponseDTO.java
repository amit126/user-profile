package com.tcg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tcg.constants.ApplicationConstants;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

@Getter
@Setter
public class SuccessResponseDTO<T> implements ResponseDTO {

    protected Integer code = ApplicationConstants.HTTP_RESPONSE_SUCCESS_CODE;
    protected T data;
    protected String message = null;
    protected Integer status = ApplicationConstants.SUCCESS_STATUS_CODE;
    protected long timestamp;
    protected String requestId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected PageableDTO pageableDTO;

    public SuccessResponseDTO() {
        this(null, ApplicationConstants.SUCCESS);
    }

    public SuccessResponseDTO(T data) {
        this(data, ApplicationConstants.SUCCESS);
    }

    public SuccessResponseDTO(String message) {
        this(null, message);
    }

    public SuccessResponseDTO(T data2, String successMessage) {
        this.data = data2;
        this.message = successMessage;
        this.timestamp = System.currentTimeMillis();
        this.requestId = MDC.get(ApplicationConstants.REQUEST_ID);
    }

    public SuccessResponseDTO(T responseData, String successMessage, Integer status) {
        this(responseData, successMessage);
        this.status = status;
    }

    public SuccessResponseDTO(Integer status) {
        this.status = status;
    }

    public SuccessResponseDTO(T responseData, PageableDTO pageableDTO) {
        this(responseData);
        this.pageableDTO = pageableDTO;
    }

}
