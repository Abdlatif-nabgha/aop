package com.bank;


import com.bank.metier.Account;
import com.bank.metier.IMetier;
import com.bank.metier.IMetierImpl;

public class Main {
    public static void main(String[] args) {
        Account account1 = new Account(1L, 1000);
        Account account2 = new Account(2L, 2000);

        IMetier metier = new IMetierImpl();
        metier.addAccount(account1);
        metier.addAccount(account2);
        metier.verser(1L, 200);
        metier.retirer(1L, 10000);
        System.out.println(metier.getAccount(1L));
        System.out.println(metier.getAccount(2L));
    }
}
