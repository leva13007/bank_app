package org.zloyleva;

import java.security.SecureRandom;

public class PasswordGenerator implements PasswordProvider {
  private final String LOWERCASE;
  private final String UPPERCASE;
  private final String DIGITS;
  private final String SYMBOLS;
  private final SecureRandom random;

  public PasswordGenerator() {
    this(
        "abcdefghijklmnopqrstuvwxyz",
        "abcdefghijklmnopqrstuvwxyz".toUpperCase(),
        "0123456789",
        "!@#$%^&*()-_+={}[]<>?",
        new SecureRandom()
    );
  }

  public PasswordGenerator(String lowercase, String uppercase, String digits, String symbols, SecureRandom random)  {
    LOWERCASE = lowercase;
    UPPERCASE = uppercase;
    DIGITS = digits;
    SYMBOLS = symbols;
    this.random = random;
  }

  @Override
  public String generate(int length, boolean useUppercase, boolean useDigits, boolean useSymbols) {
    StringBuilder characters = new StringBuilder(LOWERCASE);
    if (useUppercase) characters.append(UPPERCASE);
    if (useDigits) characters.append(DIGITS);
    if (useSymbols) characters.append(SYMBOLS);

    StringBuilder password = new StringBuilder(length);
    for (int i=0; i<length; i++){
      int index = random.nextInt(characters.length());
      password.append(characters.charAt(index));
    }
    return password.toString();
  }

  public String generate(int length){
    return this.generate(length, false, false, false);
  }
}
