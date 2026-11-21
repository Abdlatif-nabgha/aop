package com.bank.metier;


public interface IMetier {
    void addAccount(Account account);
    void verser(Long code, double sold);
    void retirer(Long code, double sold);
    Account getAccount(Long code);
}
