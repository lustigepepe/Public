package fsnavigation;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.JAXB;

import org.eclipse.swt.graphics.Image;
import common.mail.model.Message;
import maillist.MySystemItem;

public class MyDirectory extends MySystemItem {
  protected static Image folderIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icon/folder.png")
      .createImage();
  private final File file;
  private Image folderIc;

  public MyDirectory(File file) {
    this.file = file;

  }

  @Override
  public Image getImage() {
    if (file.isDirectory()) {
      folderIc = folderIcon;
    }
    return folderIc;
  }

  @Override
  public boolean hasChildren() {

    return file.listFiles() != null;
  }

  @Override
  public File getFile() {

    return file;
  }

  @Override
  public MySystemItem[] getChildren() {
    File[] child = file.listFiles();
    if (child == null) {
      return new MySystemItem[0];
    }

    MySystemItem[] children = new MySystemItem[child.length];

    for (int i = 0; i < file.listFiles().length; i++) {
      File f = file.listFiles()[i];
      if (f.isDirectory()) {
        MyDirectory md = new MyDirectory(f);
        children[i] = md;
      }

      if (f.isFile()) {
        MySystemItem mf = new MyFile(f);
        children[i] = mf;
      }
    }

    return children;
  }

  // // for XML in my MyDirectory
  //
  // public Message parsen() {
  // Message message = JAXB.unmarshal(file, Message.class);
  // return message;
  // }
  //
  // @Override
  // public boolean accept(File xmlFile) {
  // return xmlFile.getName().toLowerCase().endsWith(".xml");
  // }
  //
  // public Message ifXml() {
  // Message message = null;
  //
  // if (file.isFile() && ((FileFilter) file).accept(file)) {
  // message = parsen();
  //
  // }
  // return message;
  // }

  @Override
  public String getText() {
    return file.getName();
  }

  // Task 6

  @Override
  public Collection<Message> getMessages() {

    final Collection<Message> coll = new ArrayList<>();

    FileFilter filter = new XmlFilter();
    MySystemItem[] loop = getChildren();
    for (MySystemItem i : loop) {
      File onlyFile = i.getFile();
      if (!filter.accept(onlyFile)) {
        continue;
      }

      try {
        Message message = JAXB.unmarshal(onlyFile, Message.class);
        if (message == null || message.getId() == null) {
          continue;

        }
        coll.add(message);

      } catch (RuntimeException e) {
        continue;
      }
    }

    return coll;
  }

}
