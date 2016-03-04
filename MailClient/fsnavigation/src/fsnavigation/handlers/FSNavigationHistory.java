package fsnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import fsnavigation.HistoryDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class FSNavigationHistory extends AbstractHandler {

  /**
   * The constructor.
   */
  public FSNavigationHistory() {
  }

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

    HistoryDialog dialogHistory = new HistoryDialog(window.getShell());
    // viewerFS.setContentProvider(ArrayContentProvider.getInstance());

    // System.out.println(dialogHistory.getOKButton().getText());
    dialogHistory.open();
    // ObservableChooseDirectory.getInstance().chooseDirectory(dialogHistory.getOKButton().getText());

    return null;

  }

}
