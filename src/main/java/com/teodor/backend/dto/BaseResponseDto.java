package com.teodor.backend.dto;

import lombok.Getter;

@Getter
public class BaseResponseDto<T> {
    private Boolean success = false;
    private Boolean error = false;
    private String errorMessage = null;
    private T data;

    public BaseResponseDto() {}

    public void setSuccessResult(T data) {
        this.success = true;
        this.data = data;
        this.error = false;
        this.errorMessage = null;
    }

    public void setSuccessWithoutData() {
        this.success = true;
        this.error = false;
        this.errorMessage = null;
    }

    public void setErrorMessage(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
        this.success = false;
        this.data = null;
    }

    public void setAdditionalErrorData(T data) {
        this.data = data;
    }
}
