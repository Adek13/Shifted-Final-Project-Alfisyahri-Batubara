package com.mbank.android.model;

public class RegisterRequest {

    private String noRekening;

    private String email;

    private String noTelepon;

    private String password;

    public RegisterRequest(String noRekening, String email, String noTelepon, String password) {
        this.noRekening = noRekening;
        this.email = email;
        this.noTelepon = noTelepon;
        this.password = password;
    }

    public RegisterRequest() {
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
