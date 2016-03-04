package filterdesign;

import java.util.Set;

import common.filter.IFilter;
import common.mail.model.Message;

public abstract class SimpleFilter implements IFilter {

  public abstract Set<Message> getSet();

  @Override
  public abstract String toString();

  @Override
  public abstract Set<Message> filter(Iterable<Message> messagesToFilter);

}
