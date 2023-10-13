package com.tcg.exception;

import com.tcg.constants.ApplicationConstants;
import com.tcg.dto.ErrorResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handle(ServiceException ex) {
        HttpStatus httpStatus = ex.getHttpStatus();
        String errorMessage = messageSource.getMessage(ex.getErrorCode(), ex.getObjects(), null);
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(ex.getErrorCode(), errorMessage);
        String className = ex.getClassName() != null ? ((Class) ex.getClassName()).getSimpleName() : ServiceException.class.getSimpleName();
        log.error(className + " | errorCode : {} | errorMessage : {} | httpStatus : {}", ex.getErrorCode(), errorMessage, httpStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(String.valueOf(ApplicationConstants.HTTP_BAD_REQUEST_CODE), "Bad Request");
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        Integer errorCode = ApplicationConstants.HTTP_BAD_REQUEST_CODE;
        String errorMessage = ApplicationConstants.HTTP_BAD_REQUEST;
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(String.valueOf(errorCode), errorMessage, errors);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

}

