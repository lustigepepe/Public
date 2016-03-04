package fsnavigation;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import common.mail.model.Message;
import common.rcp.selection.SelectionHelper;

import maillist.MySystemItem;

public class SelectionChangeListener implements ISelectionChangedListener {
  private int stringCount = 0;
  private final List<String> path = new ArrayList<String>();

  @Override
  public void selectionChanged(SelectionChangedEvent event) {
    MyDirectory folder = SelectionHelper.handleStructuredSelectionEvent(event, MyDirectory.class);

    if (folder == null) {
      return;
    }

    FileFilter filter = new XmlFilter();
    for (MySystemItem i : folder.getChildren()) {
      stringCount += 1;
      File onlyFile = i.getFile();
      if (!filter.accept(onlyFile)) {
        continue;

      }

      try {
        Message message = JAXB.unmarshal(onlyFile, Message.class);
        if (message == null || message.getId() == null) {
          continue;

        }
        path.add(message.toShortString());

      } catch (RuntimeException e) {
        continue;
      }

    }
    System.out.println("  Selected directory: " + folder.getFile());
    System.out.println("  Number of messages: " + stringCount);
    for (String mail : path) {
      System.out.println(mail);
    }
  }
}