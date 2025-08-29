package org.zloyleva;

public interface PasswordProvider {
  public String generate(int length, boolean useUppercase, boolean useDigits, boolean useSymbols);
}
