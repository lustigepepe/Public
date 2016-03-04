package maillist;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import common.rcp.selection.SelectionHelper;
import common.table.MessageValues;
import filterdesign.Sender;
import filterdesign.SimpleFilter;
import de.ralfebert.rcputils.properties.PropertyValueFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;

public class TabelleView extends ViewPart implements Observer {
  public TabelleView() {
  }

  private static final int IMPORTANE_WIDTH = 20;
  private static final int RECEIVED_WIDTH = 25;
  private static final int READ_WIDTH = 15;
  private static final int MAIL_WIDTH = 60;
  private static final int SUBJECT_WIDTH = 50;
  // private static final int MAILCOUNT = 50;
  private static final int LABEL_WIDTH = 55;
  private static final int LABEL_HEIGHT = 15;
  private static final int TEXT_WIDTH = 76;
  private static final int TEXT_HEIGHT = 21;

  private TableViewer tableViewer;
  private Text text;
  private TableViewerBuilder t;

  @Override
  public void createPartControl(Composite parent) {

    getSite().getPage().addSelectionListener(listener);

    final IWorkbench workbench = PlatformUI.getWorkbench();

    ICommandService commandService = (ICommandService) workbench.getService(ICommandService.class);
    commandService.addExecutionListener(new LasyExecutionListener());

    parent.setLayout(new GridLayout(1, false));
    final SearchFilter filter = new SearchFilter();
    Composite searchComposite = new Composite(parent, SWT.NONE);
    searchComposite.setLayout(new GridLayout(2, false));
    searchComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

    Label lblNewLabel = new Label(searchComposite, SWT.NONE);
    lblNewLabel.setBounds(0, 0, LABEL_WIDTH, LABEL_HEIGHT);
    lblNewLabel.setText("Search: ");

    text = new Text(searchComposite, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    text.setBounds(0, 0, TEXT_WIDTH, TEXT_HEIGHT);
    text.addKeyListener(new KeyListener() {

      @Override
      public void keyReleased(KeyEvent e) {
        if (!text.getText().isEmpty()) {
          filter.setSearchText(text.getText());
          tableViewer.refresh();

        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        return;
      }
    });

    // Hey man is tree stuff only
    Composite tableComposite = new Composite(parent, SWT.NONE);
    tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

    t = new TableViewerBuilder(tableComposite);
    t.createColumn("Importance").bindToValue(MessageValues.IMPORTANCE).setPercentWidth(IMPORTANE_WIDTH).build();

    t.createColumn("Received").bindToValue(MessageValues.RECEIVED)
        .format(Formatter.forDate(SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM)))
        .setPercentWidth(RECEIVED_WIDTH).useAsDefaultSortColumn().build();

    t.createColumn("Read").bindToValue(MessageValues.READ).setPercentWidth(READ_WIDTH).build();

    t.createColumn("Sender").bindToValue(MessageValues.SENDER).setPercentWidth(MAIL_WIDTH)
        .format(new PropertyValueFormatter("email")).build();

    t.createColumn("Recipients").bindToValue(MessageValues.RECIPIENT).setPercentWidth(MAIL_WIDTH)
        .format(new RecipientPropertyFormatter("email")).build();

    t.createColumn("Subject").bindToValue(MessageValues.SUBJECT).setPercentWidth(SUBJECT_WIDTH).build();
    // t.setInput(createDummyMessages());
    tableViewer = t.getTableViewer();
    tableViewer.addFilter(filter);

    // Give the side for iSelectionListener
    getSite().setSelectionProvider(tableViewer);

  }

  // changed in task 6!
  // private Collection<Message> createDummyMessages() {
  // return new RandomTestDataProvider(MAILCOUNT).getMessages();
  // }

  @Override
  public void setFocus() {
    tableViewer.getTable().setFocus();
  }

  // Edit the side with a searchfiled
  private final ISelectionListener listener = new ISelectionListener() {
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection sel) {
      MySystemItem folder = SelectionHelper.handleStructuredSelection(sel, MySystemItem.class);
      if (folder == null) {
        return;
      }
      XmlObserver.getInstance().chooseXML(folder.getMessages());
      t.setInput(folder.getMessages());
    }
  };

  @Override
  public void dispose() {
    getSite().getPage().removeSelectionListener(listener);
  }

  private class LasyExecutionListener implements IExecutionListener {

    @Override
    public void notHandled(String commandId, NotHandledException exception) {
      System.out.println("============ EasyExecutionListener.notHandled ============");
      System.out.println("Command '" + commandId + "' not handeled: " + exception.getMessage());
      System.out.println("========================================================");
    }

    @Override
    public void postExecuteFailure(String commandId, ExecutionException exception) {
      System.out.println("======== EasyExecutionListener.postExecuteFailure ========");
      System.out.println("Command '" + commandId + "' execution failed: " + exception.getMessage());
      System.out.println("========================================================");
    }

    @Override
    public void postExecuteSuccess(String commandId, Object returnValue) {
      System.out.println("============ EasyExecutionListener.Success ============");
      if (returnValue instanceof SimpleFilter) {
        t.setInput(((SimpleFilter) returnValue).getSet());
      }
      // System.out.println(returnValue.getClass().toString());
      if (returnValue instanceof Sender) {
        System.out.println("============ MyDirectory.Success ============");

        t.setInput((Collection<?>) returnValue);

      }
      if (returnValue instanceof ProgressProvider) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Return is done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // t.setInput(((SimpleFilter) returnValue).getSet());
      }
    }

    @Override
    public void preExecute(String commandId, ExecutionEvent event) {
      System.out.println("============ EasyExecutionListener.preExecute ============");
      System.out.println("Command '" + commandId + "' pre-execution. Event:" + event);
      System.out.println("========================================================");
    }

  }

  @Override
  public void update(Observable o, Object arg) {

    t.setInput((Collection<?>) arg);
  }
}
