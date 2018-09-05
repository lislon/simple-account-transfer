package com.lislon.sat.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Represents details of error in case of error in API operation")
public class ApiError {
    private String message;
    private int code;

    public ApiError() {
    }

    public ApiError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Schema(description = "End-user friendly message describing error")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Schema(description = "Error code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
