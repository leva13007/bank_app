package org.zloyleva;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

  @Test
  void testRegisterSuccess() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    Bank bank = new Bank(new MockPasswordGenerator("1234"), ps);
    boolean status = bank.register("user");
    assertTrue(status);
  }

  @Test
  void testRegisterFail() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    Bank bank = new Bank(new MockPasswordGenerator("1234"), ps);
    bank.register("user");
    boolean status = bank.register("user");
    assertFalse(status);
  }

  @Test
  void testLoginSuccess() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    Bank bank = new Bank(new MockPasswordGenerator("1234"), ps);
    bank.register("user");
    BankAccount account = bank.login("user", "1234");
    assertNotNull(account);
  }

  @Test
  void testLoginFail() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    Bank bank = new Bank(new MockPasswordGenerator("1234"), ps);
    bank.register("user");
    BankAccount account = bank.login("user", "12345");
    assertNull(account);
  }
}