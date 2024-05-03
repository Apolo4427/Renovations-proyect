package com.renovations.jrl.apirestrenovations.error.dto;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private HttpStatus status;
    private String messageStatus;

    public ErrorMessage(){

    }

    public ErrorMessage(HttpStatus status, String messageStatus) {
        this.status = status;
        this.messageStatus = messageStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }
    
}
