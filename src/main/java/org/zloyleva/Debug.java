package org.zloyleva;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Debug {
  public static void main(String[] args) {
    String str = String.join("\n", "str1", "str2");

    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

    InputStream in1 = System.in;

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);

    PrintStream originOut = System.out;

//    System.setOut(printStream);

    Scanner scanner = new Scanner(in);
    System.out.println(scanner.nextLine());
    System.out.println(scanner.nextLine());


    printStream.println("test");
    printStream.println("11111");
    printStream.println("2222");

    String output = out.toString();


//    System.setOut(originOut);
    System.out.println("\n\n::::" + output);
  }
}
