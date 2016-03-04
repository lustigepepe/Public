package fsnavigation;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;

import javax.xml.bind.JAXB;

import org.eclipse.swt.graphics.Image;

import common.mail.model.Message;
import maillist.MySystemItem;

public class MyFile extends MySystemItem implements FileFilter {
  protected static Image fileIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icon/file.png")
      .createImage();
  private final File file;

  public MyFile(File file) {
    this.file = file;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public String getText() {
    return file.getName();
  }

  @Override
  public Object[] getChildren() {
    return null;
  }

  // for XML in my MyDirectory

  public Message parsen() {
    Message message = JAXB.unmarshal(file, Message.class);
    return message;
  }

  @Override
  public boolean accept(File xmlFile) {
    return xmlFile.getName().toLowerCase().endsWith(".xml");
  }

  public Message ifXml() {
    Message message = null;

    if (file.isFile() && ((FileFilter) file).accept(file)) {
      message = parsen();

    }
    return message;
  }

  private Image fileIc;

  @Override
  public Image getImage() {
    if (file.isFile()) {
      fileIc = fileIcon;
    }
    return fileIc;
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public Collection<Message> getMessages() {
    return null;
  }

}
