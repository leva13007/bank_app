package org.zloyleva;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class BankCLI {
  Scanner scanner;
  PrintStream out;
  BankAccount account = null;
  Bank bank = new Bank(new PasswordGenerator());

  public BankCLI(InputStream in, PrintStream out) {
    this.scanner = new Scanner(in);
    this.out = out;
  }

  public void run() {
    boolean status = false;
    while (!status){
      if (account == null) {
        showWelcomeMenu();
        String choice = scanner.nextLine();
        status = handleGuestChoice(choice);
      } else {
        showAccountMenu();
        String choice = scanner.nextLine();
        status = handleAccountChoice(choice);
      }
    }
  }

  public void showWelcomeMenu() {
    out.println("Welcome to the Bank system!");
    out.println("To operate choose the command below:");
    out.println("1. Register");
    out.println("2. Login");
    out.println("3. Exit");
  }

  public boolean handleGuestChoice(String choice){
    switch (choice){
      case "1": {
        out.println("Enter user name:");
        String username = scanner.nextLine();
//            System.out.println("Enter password:");
//            String password = scanner.nextLine();
        boolean status = bank.register(username);
        out.println(status ? "✅ Registration success" : "❌ Registration failed");
      }
      return false;
      case "2": {
        out.println("Enter user name:");
        String username = scanner.nextLine();
        out.println("Enter password:");
        String password = scanner.nextLine();
        account = bank.login(username, password);
        out.println(account != null ? "\n✅ Welcome " + account.getUsername() : "❌ Invalid credentials");

      }
      return false;
      case "3":
        out.println("Goodbye!");
        account = null;
        return true;
      default:
        out.println("\uD83D\uDD34 Invalid command");
        return true;
    }
  }

  public void showAccountMenu(){
    out.println("\nChoose the operation:");
    out.println("1. Show balance");
    out.println("2. Deposit");
    out.println("3. Withdraw");
    out.println("4. Exit");
    out.println("--------");
    out.println("5. Show transactions");
  }

  public boolean handleAccountChoice(String choice){
    switch (choice) {
      case "1":
        out.println("Your balance is: " + account.getBalance());
        return false;
      case "2": {
        out.println("Enter deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        double deposit = account.deposit(amount);
        out.println("Your deposit amount now: " + deposit);
        return false;
      }
      case "3": {
        out.println("Enter withdraw amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        double deposit = account.withdraw(amount);
        out.println("Your deposit amount now: " + deposit);
        return false;
      }
      case "4":
        out.println("Goodbye!");
        account = null;
        return true;
      case "5":
        out.println("List of transactions for: " + account.getUsername());
        List<Transaction> transactions = account.getTransactions();
        transactions.forEach( (n) -> { out.println(n); } ); // () => {}
        return false;
      default:
        out.println("\uD83D\uDD34 Invalid command");
        return true;
    }
  }
}
