package imapnavigation;

import java.io.File;
import java.util.Collection;
import org.eclipse.swt.graphics.Image;

import common.mail.model.Account;
import common.mail.model.Message;
import maillist.MySystemItem;

public class OpenAccount extends MySystemItem {

  protected static Image accountIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icon/account.png")
      .createImage();
  private final Account account;

  public OpenAccount(Account account) {
    this.account = account;

  }

  @Override
  public Image getImage() {
    return accountIcon;
  }

  public Account getAccount() {
    return account;
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

    MySystemItem[] accOunt = new MySystemItem[1];
    accOunt[0] = new MyAccount(account);
    return accOunt;
  }

  @Override
  public File getFile() {
    return null;
  }
}
