package com.mbank.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaldoResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("payload")
    @Expose
    private Double payload;

    @SerializedName("code")
    @Expose
    private int code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPayload() {
        return payload;
    }

    public void setPayload(Double payload) {
        this.payload = payload;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
