package maillist;

import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import common.mail.model.Message;

public final class IMAPObserver extends Observable {
  private static final IMAPObserver INSTANCE = new IMAPObserver();
  private final Set<Message> xmlList;

  private IMAPObserver() {
    xmlList = new HashSet<Message>();

  }

  public static IMAPObserver getInstance() {
    return INSTANCE;
  }

  public int getSize() {
    return xmlList.size();
  }

  public void chooseXML(Collection<Message> xml) {
    setChanged();
    notifyObservers(xml);
    for (Message i : xml) {
      xmlList.add(i);
      System.out.println("somthing in XMLObserver" + i);
    }

  }

  public Set<Message> getXmlList() {
    return xmlList;
  }

}
