package maillist;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import common.internal.Activator;
import common.mail.model.Message;
import common.mail.model.Recipient;

public class SearchFilter extends ViewerFilter {

  private String searchString;
  private String recip;

  public void setSearchText(String s) {
    this.searchString = ".*" + s + ".*";
  }

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    if (searchString == null || searchString.length() == 0) {

      return true;
    }

    try {
      Object objN = null;

      List<?> ob = ((Message) element).getRecipients();
      for (Iterator<?> i = ob.iterator(); i.hasNext();) {
        objN = i.next();
        recip = ((Recipient) objN).getEmail();
      }

    } catch (Exception e) {
      Activator.logException(e);
    }

    if (((Message) element).getSender().getEmail().matches(searchString)) {
      return true;
    }
    if (((Message) element).getSubject().matches(searchString)) {
      return true;
    }
    if (((Message) element).getText().matches(searchString)) {
      return true;
    }
    if (((Message) element).getSent().toString().matches(searchString)) {
      return true;
    }
    if (recip.matches(searchString)) {
      return true;
    }
    return false;
  }
}