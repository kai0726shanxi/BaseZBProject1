package com.zsl.test.basezbproject.http.exception;

/**
 * @author Created by stone
 * @since 2018/7/10
 */

public class ApiException extends RuntimeException {
    private String mErrorCode;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }


}


