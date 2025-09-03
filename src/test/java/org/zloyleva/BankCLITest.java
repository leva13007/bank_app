package org.zloyleva;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BankCLITest {

  @Test
  public void testRegistrationFlow(){
    String str = String.join("\n", "1", "oleh", "3");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new PasswordGenerator());
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("Welcome to the Bank system!"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void guestCanDoRegistrationAndLoginAfter(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "2", "oleh", password, "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("✅ Welcome oleh"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void guestCanDoRegistrationAndLoginAndHasZeroBalance(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "2", "oleh", password, "1", "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("✅ Welcome oleh"));
    assertTrue(output.contains("Your balance is: 0.0"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void userCanDoDeposit(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "2", "oleh", password, "2", "15000", "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("✅ Welcome oleh"));
    assertTrue(output.contains("Your deposit amount now: 15000.0"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void userCanDoWithDrawFlow(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "2", "oleh", password, "2", "15000", "3", "2560", "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("✅ Welcome oleh"));
    assertTrue(output.contains("Your deposit amount now: 15000.0"));
    assertTrue(output.contains("Your deposit amount now: 12440.0"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void userHasRightTransactionRecords(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "2", "oleh", password, "2", "15000", "3", "2560", "5", "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("List of transactions for: oleh"));
    assertTrue(output.contains(":\tDEPOSIT\t|\t0.0\t|\t0.0"));
    assertTrue(output.contains(":\tDEPOSIT\t|\t15000.0\t|\t15000.0"));
    assertTrue(output.contains(":\tWITHDRAW\t|\t2560.0\t|\t12440.0"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void userCannotDoWithDrawMoreMoneyThanTheyHave(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "2", "oleh", password, "2", "15000", "3", "16000", "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("✅ Welcome oleh"));
    assertTrue(output.contains("Your deposit amount now: 15000.0"));
    assertTrue(output.contains("You cannot get more money that is on your account!"));
    assertTrue(output.contains("Goodbye!"));
  }

  @Test
  public void gotAnInvalidCommand(){
    String password = "password";
    String str = String.join("\n", "1", "oleh", "20", "4");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    BankCLI cli = new BankCLI(in, printStream, new MockPasswordGenerator(password));
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("\uD83D\uDD34 Invalid command"));
  }
}
