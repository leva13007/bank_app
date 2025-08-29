package org.zloyleva;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BankApp {

  public static void main(String[] args) {

    BankCLI cli = new BankCLI(System.in, System.out);
    cli.run();
  }
}
