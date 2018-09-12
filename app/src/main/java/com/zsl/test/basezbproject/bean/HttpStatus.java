package com.zsl.test.basezbproject.bean;

import com.google.gson.annotations.SerializedName;
import com.zsl.test.basezbproject.Constants;

public class HttpStatus {
    @SerializedName("code")
    private String mCode;
    @SerializedName("message")
    private String mMessage;

    public String getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getStatus() {
        return mStatus;
    }

    @SerializedName("status")
private String mStatus;

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return mCode != Constants.WEB_RESP_CODE_SUCCESS;
    }
    public boolean isStatus(){
        return !mStatus.equals(Constants.WEB_RESP_STATUS);

    }
}
