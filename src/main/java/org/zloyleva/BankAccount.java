package org.zloyleva;

public class BankAccount {
    private final String username;
    private final String password; // todo change password feature!

    private double balance = 0;

    public BankAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public double deposit(double value){
        // todo validate value should be more than 0
        this.balance += value;
        return this.balance;
    }

    public double withdraw(double value){
        // todo value shouldn't be more than balance
        this.balance -= value;
        return this.balance;
    }
}
