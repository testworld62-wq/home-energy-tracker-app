package com.codominator.deviceservice.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
