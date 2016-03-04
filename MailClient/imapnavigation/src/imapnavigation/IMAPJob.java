package imapnavigation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import common.mail.imapsync.ImapHelper;
import common.mail.imapsync.SynchronizationException;
import common.mail.model.Account;

public class IMAPJob extends Job {
  private final Account account;

  public IMAPJob(Account account) {
    super(account.getName());
    this.account = account;
  }

  @Override
  protected IStatus run(IProgressMonitor monitor) {
    try {
      ImapHelper.syncAllFoldersToAccount(account, monitor);
    } catch (SynchronizationException e) {
      throw new RuntimeException("Exception not handled in code", e);
    }
    return Status.OK_STATUS;
  }

  public Account getAccount() {
    return account;
  }
}