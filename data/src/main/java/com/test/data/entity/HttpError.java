package com.test.data.entity;

import com.google.gson.annotations.SerializedName;

public class HttpError extends Exception {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
