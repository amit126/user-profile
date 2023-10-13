package com.tcg.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private HttpStatus httpStatus = HttpStatus.OK;
    private Exception ex ;

    private String message;

    private Object className;

    private Object[] objects;

    public ServiceException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus) {
        super();
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, Exception ex) {
        super();
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.ex = ex;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, String message, Object className) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
        this.className = className;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, Object[] objects, Object className) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.objects = objects;
        this.className = className;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, Object[] objects) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.objects = objects;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus, Object className) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.className = className;
    }
}
