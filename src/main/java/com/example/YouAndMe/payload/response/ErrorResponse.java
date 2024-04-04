package com.example.YouAndMe.payload.response;

public class ErrorResponse {
    private String errors;

    public ErrorResponse(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

}
