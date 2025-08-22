package org.zloyleva;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    public boolean register(String username, String password){
        if (accounts.containsKey(username)) {
            System.out.println("Account with this username is exist!");
            return false;
        }

        accounts.put(username, new BankAccount(username, password));
        return true;
    }

    public BankAccount login(String username, String password){
        BankAccount account = accounts.get(username);
        if (account == null || account.checkPassword(password)){
            return account;
        }
        return null;
    }
}
