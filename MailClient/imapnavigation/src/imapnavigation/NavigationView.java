package imapnavigation;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

import common.mail.model.Account;

public class NavigationView extends ViewPart {
  public static final String ID = "fsnavigation.NavigationView";
  private TreeViewer viewer;

  public NavigationView() {

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

    // Give the side for iSelectionListener
    getSite().setSelectionProvider(viewer);

    // Task 9////
    viewer.setInput(new OpenAccount(DummyAccount.getDummyAccount()));

    // Task 10
    Job.getJobManager().addJobChangeListener(new IJobChangeListener() {

      @Override
      public void sleeping(IJobChangeEvent event) {

      }

      @Override
      public void scheduled(IJobChangeEvent event) {

      }

      @Override
      public void running(IJobChangeEvent event) {

      }

      @Override
      public void done(IJobChangeEvent event) {
        if (event.getJob() instanceof IMAPJob) {
          IMAPJob imapJob = (IMAPJob) event.getJob();
          final Account account = imapJob.getAccount();
          Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
              viewer.setInput(new OpenAccount(account));
            }
          });
        }
      }

      @Override
      public void awake(IJobChangeEvent event) {

      }

      @Override
      public void aboutToRun(IJobChangeEvent event) {

      }
    });

  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  /*
   * public static String getPath() { return path; }
   * 
   * public static void setPath(String path) { NavigationView.path = path; }
   */

}