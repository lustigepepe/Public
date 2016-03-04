package filterdesign;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import common.filter.FilterOperator;
import common.filter.StringCompareHelper;
import common.mail.model.Message;

public class Subject extends SimpleFilter {

  private final String toMatch;
  private final FilterOperator filterOperator;
  private final Collection<Message> messages;
  private Set<Message> subject;

  public Subject(String toMatch, FilterOperator filterOperator, Collection<Message> messages) {
    this.toMatch = toMatch;
    this.filterOperator = filterOperator;
    this.messages = messages;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Message i : filter(messages)) {
      s.append(i).append('\n');
    }
    return "Subject:\n" + s;
  }

  @Override
  public Set<Message> getSet() {
    return filter(messages);
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {

    subject = new HashSet<Message>();
    try {
      for (Iterator<?> i = messagesToFilter.iterator(); i.hasNext();) {
        Message message = (Message) i.next();

        String stringMessage = message.getSubject();
        if (StringCompareHelper.matches(stringMessage, toMatch, filterOperator)) {

          subject.add(message);
        }

      }
    } catch (Exception e) {
      return null;
    }
    return subject;
  }
}