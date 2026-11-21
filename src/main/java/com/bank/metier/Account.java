package com.bank.metier;


public class Account {

    private final Long code;
    private double solde;

    public Account(Long code, double solde) {
        this.code = code;
        this.solde = solde;
    }

    public void setSolde(double sold) {
        this.solde = sold;
    }

    public Long getCode() {
        return code;
    }
    public double getSolde() {
        return solde;
    }

    @Override
    public String toString() {
        return "[code: " + code + ", sold: " + solde + "]";
    }
}
