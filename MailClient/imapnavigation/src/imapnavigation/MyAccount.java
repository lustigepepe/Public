package imapnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import common.mail.model.Account;
import common.mail.model.Folder;
import common.mail.model.Message;
import maillist.MySystemItem;

public class MyAccount extends MySystemItem {
  protected static Image accountIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icon/account.png")
      .createImage();
  private final Account account;

  public MyAccount(Account account) {
    this.account = account;

  }

  public Account getAccount() {
    return account;
  }

  @Override
  public Image getImage() {
    return accountIcon;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public String getText() {
    return account.getName();
  }

  @Override
  public Collection<Message> getMessages() {
    return null;
  }

  @Override
  public MySystemItem[] getChildren() {

    List<Folder> child = account.getFolders();
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
