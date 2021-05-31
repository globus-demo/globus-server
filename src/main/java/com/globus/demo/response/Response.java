package com.globus.demo.response;

public class Response {
    private boolean status;
    private String text;

    private Object objectToResponse;

    public Response(boolean status, Object objectToResponse) {
        this.status = status;
        this.objectToResponse = objectToResponse;
    }

    public Response(boolean status, String text) {
        this.status = status;
        this.text = text;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setObjectToResponse(Object objectToResponse) {
        this.objectToResponse = objectToResponse;
    }

    public boolean isStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public Object getObjectToResponse() {
        return objectToResponse;
    }
}
