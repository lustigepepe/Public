package fsnavigation;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class HistoryDialog extends Dialog implements Observer {

  private static final int HEIGHT = 300;
  private static final int WIDTH = 450;
  private static List liste;

  /**
   * Create the dialog.
   * 
   * @param parentShell
   */
  public HistoryDialog(Shell parentShell) {
    super(parentShell);
    super.setShellStyle(SWT.SHELL_TRIM | SWT.RESIZE);
  }

  /**
   * Create contents of the dialog.
   * 
   * @param parent
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    ObservableChooseDirectory.getInstance().addObserver(this);

    Composite container = (Composite) super.createDialogArea(parent);
    Composite container1 = new Composite(container, SWT.NONE);
    container1.setLayoutData(new GridData(GridData.FILL_BOTH));
    GridLayout layout = new GridLayout(2, false);
    container1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    container1.setLayout(layout);

    directoryList(container1);

    return container;
  }

  private void directoryList(Composite container) {

    GridData directoryfield = new GridData();
    directoryfield.grabExcessHorizontalSpace = true;
    directoryfield.horizontalAlignment = GridData.FILL;

    liste = new List(container, SWT.BORDER);
    liste.setLayoutData(directoryfield);
    for (String path : ObservableChooseDirectory.getInstance().getFileList()) {
      liste.add(path);
    }

    if (liste.getItemCount() == 0) {
      liste.add("No base directories in history!");
    }

    if (liste.getItem(0).equals("No base directories in history!") && liste.getItemCount() > 1) {
      liste.remove(0);
    }

  }

  /**
   * Create contents of the button bar.
   * 
   * @param parent
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected void okPressed() {

    ObservableChooseDirectory.getInstance().chooseDirectory(liste.getItem(liste.getSelectionIndex()));

    super.okPressed();

  }

  @Override
  protected void configureShell(Shell newShell) {
    super.configureShell(newShell);
    newShell.setText("Select Base Directory in History");
  }

  /**
   * Return the initial size of the dialog.
   */
  @Override
  protected Point getInitialSize() {
    return new Point(WIDTH, HEIGHT);
  }

  @Override
  public void update(Observable arg0, Object arg1) {

  }
}
