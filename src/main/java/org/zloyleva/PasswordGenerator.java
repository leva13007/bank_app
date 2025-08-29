package org.zloyleva;

import java.security.SecureRandom;

public class PasswordGenerator implements PasswordProvider {
  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String UPPERCASE = LOWERCASE.toUpperCase();
  private static final String DIGITS = "0123456789";
  private static final String SYMBOLS = "!@#$%^&*()-_+={}[]<>?";

  private static final SecureRandom random = new SecureRandom();

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
}
