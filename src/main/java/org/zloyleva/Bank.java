package org.zloyleva;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private PasswordProvider passwordGenerator;
    PrintStream out;
    
    public Bank(PasswordProvider passwordGenerator, PrintStream out) {
      this.passwordGenerator = passwordGenerator;
      this.out = out;
    }

    public boolean register(String username){
        if (accounts.containsKey(username)) {
            out.println("Account with this username is exist!");
            return false;
        }

        // generate a new password and provide it to the user!
        String password = this.passwordGenerator.generate(12, true,true, true);
        out.println("User password is: " + password);
        accounts.put(username, new BankAccount(username, password, out));
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
