package filterdesign;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import common.filter.FilterOperator;
import common.filter.StringCompareHelper;
import common.mail.model.Message;
import common.mail.model.Recipient;

public class Recipients extends SimpleFilter {

  private final String toMatch;
  private final FilterOperator filterOperator;
  private final Collection<Message> messages;
  private Set<Message> recipients;

  public Recipients(String toMatch, FilterOperator filterOperator, Collection<Message> messages) {
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
    return "Recipients:\n" + s;
  }

  @Override
  public Set<Message> getSet() {
    return filter(messages);
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {

    recipients = new HashSet<Message>();
    try {
      for (Iterator<?> i = messagesToFilter.iterator(); i.hasNext();) {
        Message message = (Message) i.next();

        Recipient oneReci = null;
        List<?> re = message.getRecipients();
        for (Iterator<?> j = re.iterator(); j.hasNext();) {
          oneReci = (Recipient) j.next();
          if (StringCompareHelper.matches(oneReci.toString(), toMatch, filterOperator)) {

            recipients.add(message);
          }
        }

      }
    } catch (Exception e) {
      return null;
    }
    return recipients;
  }
}