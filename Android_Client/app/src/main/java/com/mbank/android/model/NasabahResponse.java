package com.mbank.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mbank.android.model.Nasabah;

public class NasabahResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Nasabah data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Nasabah getData() {
        return data;
    }

    public void setData(Nasabah data) {
        this.data = data;
    }
}
