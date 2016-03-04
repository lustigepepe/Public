package fsnavigation;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public final class ObservableChooseDirectory extends Observable {
  private static final ObservableChooseDirectory INSTANCE = new ObservableChooseDirectory();
  private final Set<String> fileList;

  private ObservableChooseDirectory() {
    fileList = new HashSet<String>();
  }

  public static ObservableChooseDirectory getInstance() {
    return INSTANCE;
  }

  public void chooseDirectory(String path) {
    setChanged();
    notifyObservers(path);
    fileList.add(path);
    System.out.println("goes in Observer");
  }

  public Set<String> getFileList() {
    return fileList;
  }

}
