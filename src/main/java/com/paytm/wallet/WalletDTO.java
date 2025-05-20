package com.paytm.wallet;

public class WalletDTO {

    private String  email;
    private Double amount;

    public WalletDTO() {
    }

    public WalletDTO(String email, Double amount) {
        this.email = email;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
