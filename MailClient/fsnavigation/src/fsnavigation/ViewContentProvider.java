package fsnavigation;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import maillist.MySystemItem;

public class ViewContentProvider implements ITreeContentProvider {

  /**
   * This method is called when the user expands a tree node in the View. The
   * parameter of the method is the selected item, and the method is expected to
   * return the direct children of the item.
   * 
   * @param parentElement
   *          the expanded element in the tree, for which the framework requests
   *          the children
   */

  @Override
  public Object[] getChildren(Object parentElement) {
    // For every parentElement, we return an empty array. That means that for
    // every given tree item, we say it has no children. Here you should cast
    // the parentElement to your own class and return its children.

    Object[] item = ((MySystemItem) parentElement).getChildren();
    int a = 0;
    for (int i = 0; i < item.length; i++) {
      if (item[i] instanceof MyDirectory) {
        a++;
      }
    }
    Object[] objectDirectory = new Object[a];

    int count = 0;
    for (Object i : item) {

      if (i instanceof MyDirectory) {
        objectDirectory[count] = i;
        count++;
      }
    }

    return objectDirectory;

    // return ((MySystemItem) parentElement).getChildren();
  }

  /**
   * This method is called when the user expands a tree node in the View. The
   * framework asks is the given element has any children. The parameter of the
   * method is a tree item, and the method is expected to return
   * <code>true></code> if the item has children, or <code>false</code> if it
   * has no children.
   * 
   * @param element
   *          a tree item, for which the framework wants to know if it has
   *          children
   */
  @Override
  public boolean hasChildren(Object element) {
    // For every element, we say to the framework that is has no children. Here
    // you should cast the element to your own class and check if it has
    // children.

    return getChildren(element).length > 0;
    // return ((MySystemItem) element).hasChildren();

  }

  // ==========================================================================
  // In our simple tree, you don't need to change any of the following methods.
  // ==========================================================================

  @Override
  public Object[] getElements(Object parent) {
    return getChildren(parent);
  }

  @Override
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }
}