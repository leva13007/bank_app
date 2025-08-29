package org.zloyleva;

import java.util.List;
import java.util.Scanner;

public class BankCLI {
  Scanner scanner = new Scanner(System.in);
  BankAccount account = null;
  Bank bank = new Bank(new PasswordGenerator());

  public void run() {
    while (true){
      if (account == null) {
        showWelcomeMenu();
        String choice = scanner.nextLine();
        handleGuestChoice(choice);
      } else {
        showAccountMenu();
        String choice = scanner.nextLine();
        handleAccountChoice(choice);
      }
    }
  }

  public void showWelcomeMenu() {
    System.out.println("Welcome to the Bank system!");
    System.out.println("To operate choose the command below:");
    System.out.println("1. Register");
    System.out.println("2. Login");
    System.out.println("3. Exit");
  }

  public void handleGuestChoice(String choice){
    switch (choice){
      case "1": {
        System.out.println("Enter user name:");
        String username = scanner.nextLine();
//            System.out.println("Enter password:");
//            String password = scanner.nextLine();
        boolean status = bank.register(username);
        System.out.println(status ? "✅ Registration success" : "❌ Registration failed");
      }
      break;
      case "2": {
        System.out.println("Enter user name:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        account = bank.login(username, password);
        System.out.println(account != null ? "\n✅ Welcome " + account.getUsername() : "❌ Invalid credentials");

      }
      break;
      case "3":
        System.out.println("Goodbye!");
        account = null;
        break;
      default:
        System.out.println("\uD83D\uDD34 Invalid command");
    }
  }

  public void showAccountMenu(){
    System.out.println("\nChoose the operation:");
    System.out.println("1. Show balance");
    System.out.println("2. Deposit");
    System.out.println("3. Withdraw");
    System.out.println("4. Exit");
    System.out.println("--------");
    System.out.println("5. Show transactions");
  }

  public void handleAccountChoice(String choice){
    switch (choice) {
      case "1":
        System.out.println("Your balance is: " + account.getBalance());
        break;
      case "2": {
        System.out.println("Enter deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        double deposit = account.deposit(amount);
        System.out.println("Your deposit amount now: " + deposit);
        break;
      }
      case "3": {
        System.out.println("Enter withdraw amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        double deposit = account.withdraw(amount);
        System.out.println("Your deposit amount now: " + deposit);
        break;
      }
      case "4":
        System.out.println("Goodbye!");
        account = null;
        break;
      case "5":
        System.out.println("List of transactions for: " + account.getUsername());
        List<Transaction> transactions = account.getTransactions();
        transactions.forEach( (n) -> { System.out.println(n); } ); // () => {}
        break;
      default:
        System.out.println("\uD83D\uDD34 Invalid command");
    }
  }
}
