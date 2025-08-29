package org.zloyleva;

public class MockPasswordGenerator implements PasswordProvider {
  private final String password;
  public MockPasswordGenerator(String password) {
    this.password = password;
  }

  @Override
  public String generate(int length, boolean useUppercase, boolean useDigits, boolean useSymbols) {
    return this.password;
  }
}