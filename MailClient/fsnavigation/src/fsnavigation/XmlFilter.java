package fsnavigation;

import java.io.File;
import java.io.FileFilter;

public class XmlFilter implements FileFilter {

  @Override
  public boolean accept(File xmlFile) {
    return xmlFile.getName().toLowerCase().endsWith(".xml");
  }
}
