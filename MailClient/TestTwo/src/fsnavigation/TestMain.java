package fsnavigation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TestMain {

  /**
   * @param args
   */
  public static void main(String[] args) {

    List<String> g = new ArrayList<String>();

    g.add("aa");
    g.add("bb");
    g.add("cc");
    g.add("cc");
    ListIterator<String> h = g.listIterator();
    int zahl = 0;
    for (String i : g) {
      zahl++;

      System.out.println(g.get(0));
    }

  }
}
