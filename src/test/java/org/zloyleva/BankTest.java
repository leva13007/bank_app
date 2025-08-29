package org.zloyleva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

  @Test
  void testRegisterSuccess() {
    Bank bank = new Bank(new MockPasswordGenerator("1234"));
    boolean status = bank.register("user");
    assertTrue(status);
  }

  @Test
  void testRegisterFail() {
    Bank bank = new Bank(new MockPasswordGenerator("1234"));
    bank.register("user");
    boolean status = bank.register("user");
    assertFalse(status);
  }

  @Test
  void testLoginSuccess() {
    Bank bank = new Bank(new MockPasswordGenerator("1234"));
    bank.register("user");
    BankAccount account = bank.login("user", "1234");
    assertNotNull(account);
  }

  @Test
  void testLoginFail() {
    Bank bank = new Bank(new MockPasswordGenerator("1234"));
    bank.register("user");
    BankAccount account = bank.login("user", "12345");
    assertNull(account);
  }
}