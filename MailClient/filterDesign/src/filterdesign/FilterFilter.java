package filterdesign;

import java.util.Set;

import model.Message;

public abstract class FilterFilter extends SimpleFilter {
  private final SimpleFilter one;

  public FilterFilter(SimpleFilter one, SimpleFilter two) {
    this.one = one;
  }

  @Override
  public Set<Message> getSet() {
    return filter(one.getSet());
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Message i : filter(one.getSet())) {
      s.append(i).append('\n');
    }
    return this.getClass().getSimpleName() + ": " + '\n' + s;

  }

  @Override
  public abstract Set<Message> filter(Iterable<Message> messagesToFilter);

}
