package org.zloyleva;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {

  class FakeRandom extends SecureRandom {
    private int counter = -1;
    @Override
    public int nextInt(int length){
      counter++;
//      return counter >= length - 1 ? length : counter;
      return Math.min(counter,length - 1);
    }
  }

  @Test
  public void generateTest(){
    PasswordGenerator generator = new PasswordGenerator(
        "abc",
        "ABC",
        "012",
        "!",
        new FakeRandom()
    );
    assertEquals("abcABC012!", generator.generate(10, true, true, true));
  }
}
