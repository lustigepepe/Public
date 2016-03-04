package imapnavigation;

import common.mail.model.Account;
import static common.mail.model.builder.Builders.*;
import common.mail.testdata.RandomTestDataProvider;

public class DummyAccount {
  public DummyAccount() {
  }

  public static Account getDummyAccount() {
    // @formatter:off    
    return newAccountBuilder()
      .host("de.somewhere.com")
      .username("alice")
      .password("secret")
      .name("Alice-IMAP")
      .folder(
        newFolderBuilder()
          .fullName("INBOX")
          .builtMessages(new RandomTestDataProvider(20).getMessages())
          .folder(
            newFolderBuilder()
              .fullName("Customers")
              .builtMessages(new RandomTestDataProvider(30).getMessages())              
          )
      )
      .folder(
        newFolderBuilder()
          .fullName("Sent")
          .builtMessages(new RandomTestDataProvider(5).getMessages())
      )
    .build();
    // @formatter:on
  }

}
