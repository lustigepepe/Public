package imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import common.mail.imapsync.ImapHelper;
import common.mail.model.Account;
import imapnavigation.IMAPJob;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @param <MToolControl>
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class IMAPNavigation<MToolControl> extends AbstractHandler {
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    Account account = ImapHelper.getAccount("gmail");
    if (account == null) {
      account = new Account();
      account.setName("gmail");
      account.setHost("imap.gmail.com");
      account.setUsername("test@googlemail.com");
      account.setPassword("B-BgxkT_anr2bubbyTLM");
      ImapHelper.saveAccount(account);
    }

    IMAPJob job = new IMAPJob(account);
    job.setUser(true);
    job.schedule();
    return null;
  }
};
