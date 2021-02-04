package com.example.weatherinfo.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {

    @SerializedName("cod")
    private String code;

    @SerializedName("message")
    private String message;

    @Expose(deserialize = false, serialize = false)
    private boolean isRecoverable;

    public ApiError(boolean isRecoverable, String message) {
        this.isRecoverable = isRecoverable;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRecoverable() {
        return isRecoverable;
    }

    public void setRecoverable(boolean recoverable) {
        isRecoverable = recoverable;
    }
}