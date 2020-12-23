package com.mbank.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MutasiResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("payload")
    @Expose
    private List<Mutasi> payload;

    @SerializedName("code")
    @Expose
    private int code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Mutasi> getPayload() {
        return payload;
    }

    public void setPayload(List<Mutasi> payload) {
        this.payload = payload;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
