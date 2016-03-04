package filterdesign;

import java.util.HashSet;
import java.util.Set;

import org.junit.runners.Parameterized.Parameters;

import common.mail.model.Message;

public class Intersection extends FilterFilter {

  private final SimpleFilter two;
  private final Set<Message> set;

  public Intersection(SimpleFilter one, SimpleFilter two) {
    super(one, two);
    this.two = two;
    this.set = new HashSet<Message>();

  }

  @Parameters
  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    for (Message i : messagesToFilter) {
      for (Message j : two.getSet()) {
        if (i.equals(j)) {
          set.add(j);
        }
      }
    }

    return set;
  }

}
