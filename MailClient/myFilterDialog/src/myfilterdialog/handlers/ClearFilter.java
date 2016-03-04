package myfilterdialog.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import common.filter.FilterOperator;
import filterdesign.Sender;
import maillist.XmlObserver;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ClearFilter extends AbstractHandler {
  /**
   * The constructor.
   */
  public ClearFilter() {
  }

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    return new Sender("", FilterOperator.CONTAINS, XmlObserver.getInstance().getXmlList());
  }
}
