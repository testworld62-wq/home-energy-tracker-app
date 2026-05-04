package com.codominator.deviceservice.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class DeviceNotFoundException extends RuntimeException{
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
    public DeviceNotFoundException(String message){
        super(message);
        this.message = message;
        this.status = HttpStatus.NOT_FOUND;
        this.timestamp = LocalDateTime.now();
    }
}
