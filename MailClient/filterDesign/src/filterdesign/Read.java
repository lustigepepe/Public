package filterdesign;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import common.mail.model.Message;

public class Read extends SimpleFilter {

  private final boolean boo;
  private final Collection<Message> messages;
  private Set<Message> read;

  public Read(boolean boo, Collection<Message> messages) {
    this.boo = boo;
    this.messages = messages;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Message i : filter(messages)) {
      s.append(i).append('\n');
    }
    return "Read " + s;
  }

  @Override
  public Set<Message> getSet() {
    return filter(messages);
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {

    read = new HashSet<Message>();
    try {
      for (Iterator<?> i = messagesToFilter.iterator(); i.hasNext();) {
        Message message = (Message) i.next();
        message.isRead();
        if (message.isRead() == boo) {

          read.add(message);
        }

      }
    } catch (Exception e) {
      return null;
    }
    return read;
  }
}