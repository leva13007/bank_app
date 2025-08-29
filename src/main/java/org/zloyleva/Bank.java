package org.zloyleva;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private PasswordProvider passwordGenerator;
    public Bank(PasswordProvider passwordGenerator) {
      this.passwordGenerator = passwordGenerator;
    }

    public boolean register(String username){
        if (accounts.containsKey(username)) {
            System.out.println("Account with this username is exist!");
            return false;
        }

        // generate a new password and provide it to the user!
        String password = this.passwordGenerator.generate(12, true,true, true);
        System.out.println("User password is: " + password);
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
