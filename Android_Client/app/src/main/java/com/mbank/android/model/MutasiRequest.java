package com.mbank.android.model;

public class MutasiRequest {

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public MutasiRequest(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
