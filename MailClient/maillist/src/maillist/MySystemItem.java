package maillist;

import java.io.File;
import java.util.Collection;

import org.eclipse.swt.graphics.Image;

import common.mail.model.Message;

public abstract class MySystemItem {

  public abstract boolean hasChildren();

  public abstract Object[] getChildren();

  public abstract String getText();

  public abstract Image getImage();

  public abstract File getFile();

  public abstract Collection<Message> getMessages();

}