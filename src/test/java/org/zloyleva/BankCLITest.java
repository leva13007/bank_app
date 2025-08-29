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

    BankCLI cli = new BankCLI(in, printStream);
    cli.run();

    String output = out.toString();
    assertTrue(output.contains("Welcome to the Bank system!"));
    assertTrue(output.contains("Goodbye!"));
  }

  // testLoginFlow
  // testDepositFlow
  // testTransactionsFlow
  // testWithDrawFlow
  // testWithDrawFlowFail->s
}
