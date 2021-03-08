package com.epita.movieserver_rest.errorHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public ApiError(HttpStatus badRequest, String error) {
        timestamp = LocalDateTime.now();
        this.message = error;
        this.status = badRequest;
    }

    public ApiError(HttpStatus status) {
        this(HttpStatus.BAD_REQUEST, "error");
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(HttpStatus.BAD_REQUEST, "error");
        this.message = "Unexpected error";
    }


    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    public ApiError(HttpStatus status, String message, Throwable ex, String debugMessage) {
        this(HttpStatus.BAD_REQUEST, ex.getMessage());
        this.debugMessage = debugMessage;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                '}';
    }
}
