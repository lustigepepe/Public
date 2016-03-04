package filterdesign;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import common.mail.model.Importance;
import common.mail.model.Message;

public class MyImportance extends SimpleFilter {

  private final Importance importanceOperator;
  private final Collection<Message> messages;
  private Set<Message> importance;

  public MyImportance(Importance importanceOperator, Collection<Message> messages) {
    this.importanceOperator = importanceOperator;
    this.messages = messages;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Message i : filter(messages)) {
      s.append(i).append('\n');
    }
    return "Importance:\n" + s;
  }

  @Override
  public Set<Message> getSet() {
    return filter(messages);
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {

    importance = new HashSet<Message>();
    try {
      for (Iterator<?> i = messagesToFilter.iterator(); i.hasNext();) {
        Message message = (Message) i.next();

        if (importanceOperator == message.getImportance()) {

          importance.add(message);
        }

      }
    } catch (Exception e) {
      return null;
    }
    return importance;
  }
}