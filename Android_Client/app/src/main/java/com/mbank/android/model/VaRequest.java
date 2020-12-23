package com.mbank.android.model;

public class VaRequest {

    private String virtualAccount;

    private String type;

    private Double jumlah;

    public VaRequest(String virtualAccount, String type) {
        this.virtualAccount = virtualAccount;
        this.type = type;
    }

    public VaRequest(String virtualAccount, String type, Double jumlah) {
        this.virtualAccount = virtualAccount;
        this.type = type;
        this.jumlah = jumlah;
    }

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        jumlah = jumlah;
    }
}
