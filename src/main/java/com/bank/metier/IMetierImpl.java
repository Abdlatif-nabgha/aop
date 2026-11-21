package com.bank.metier;


import java.util.HashMap;
import java.util.Map;

public class IMetierImpl implements IMetier {

    private final Map<Long, Account> accounts = new HashMap<>();

    @Override
    public void addAccount(Account account) {
        accounts.put(account.getCode(), account);
    }

    @Override
    public void verser(Long code, double sold) {
        Account account = accounts.get(code);
        account.setSolde(account.getSolde() + sold);
    }

    @Override
    public void retirer(Long code, double sold) {
        Account account = accounts.get(code);
        account.setSolde(account.getSolde() - sold);
    }

    @Override
    public Account getAccount(Long code) {
        return accounts.get(code);
    }
}
