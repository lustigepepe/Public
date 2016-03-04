package test;

import java.util.ArrayList;
import java.util.List;

public class Test {

  /**
   * @param args
   */
  public static void main(String[] args) {

    List<String> string = new ArrayList<String>();
    string.add("hey");
    Object b = string;
    System.out.println(b);

  }
}
