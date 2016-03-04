package imapnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import common.mail.model.Folder;
import common.mail.model.Message;
import maillist.MySystemItem;

public class MyFolder extends MySystemItem {

  protected static Image folderIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icon/folder.png")
      .createImage();

  // private String name;
  private Collection<Message> messages;

  private final Folder f;

  public MyFolder(Folder f) {
    this.f = f;

  }

  @Override
  public Image getImage() {

    return folderIcon;
  }

  @Override
  public boolean hasChildren() {

    return false;
  }

  @Override
  public String getText() {
    return f.getFullName();
  }

  // Task 6

  @Override
  public Collection<Message> getMessages() {
    messages = new ArrayList<Message>();
    if (f.getMessages() != null) {
      for (Message j : f.getMessages()) {
        messages.add(j);
      }
    }
    return messages;
  }

  @Override
  public Object[] getChildren() {
    List<Folder> child = f.getFolders();
    if (child == null) {
      return new MySystemItem[0];
    }

    List<MySystemItem> children = new ArrayList<MySystemItem>();
    for (Folder f : child) {
      MyFolder mF = new MyFolder(f);
      children.add(mF);

    }
    MySystemItem[] family = new MySystemItem[children.size()];
    for (int i = 0; i < children.size(); i++) {
      family[i] = children.get(i);

    }
    return family;
  }

  @Override
  public File getFile() {
    return null;
  }

}