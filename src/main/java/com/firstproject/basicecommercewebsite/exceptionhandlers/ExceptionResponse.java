package com.firstproject.basicecommercewebsite.exceptionhandlers;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {

    private int status;
    private String error;
    private String message;
    private String path;
    private Date Timestamp;

    public ExceptionResponse(int status, String error, String path, String message, Date timestamp) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
        Timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
