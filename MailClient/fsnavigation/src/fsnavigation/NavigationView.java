package fsnavigation;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class NavigationView extends ViewPart implements Observer {
  public static final String ID = "fsnavigation.NavigationView";
  private TreeViewer viewer;

  public NavigationView() {

    ObservableChooseDirectory.getInstance().addObserver(this);

  }

  /**
   * This is a callback that will allow us to create the viewer and initialize
   * it.
   */
  @Override
  public void createPartControl(Composite parent) {

    // a TreeViewer is a Jface widget, which shows trees
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    // We set the ContentProvider for the TreeViewer. This class prepares data
    // to be shown by the TreeViewer.
    viewer.setContentProvider(new ViewContentProvider());
    // We set the LabelProvider. This class decides how elements in the tree are
    // shown by returning a text and an optional icon.
    viewer.setLabelProvider(new ViewLabelProvider());

    // Here we set the root of the tree. The framework will ask for more data
    // when the user expands tree items.
    viewer.setInput(createModel());

    // Give the new selected directory for task 3 - 5
    viewer.addSelectionChangedListener(new SelectionChangeListener());

    // Give the side for iSelectionListener
    getSite().setSelectionProvider(viewer);

  }

  /**
   * We will set up a model to initialize tree hierarchy.
   */
  private Object createModel() {
    // Our root item is simply a dummy Object. Here you need to provide your own
    // root class.
    return new MyDirectory(new File(System.getProperty("user.home")));

  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public void update(Observable o, Object arg) {
    viewer.setInput(new MyDirectory(new File((String) arg)));

  }

  /*
   * public static String getPath() { return path; }
   * 
   * public static void setPath(String path) { NavigationView.path = path; }
   */

}