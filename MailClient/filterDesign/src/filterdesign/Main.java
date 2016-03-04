package filterdesign;

import java.util.Collection;

import common.filter.FilterOperator;
import common.mail.model.Importance;
import common.mail.model.Message;
import common.mail.testdata.RandomTestDataProvider;

public class Main {

  private static final int MAILCOUNT = 50;

  public static void main(String[] args) {

    SimpleFilter sender = new Sender("a", FilterOperator.CONTAINS, getMessage());
    System.out.println(sender);

    // SimpleFilter read = new Read(true,getMessage());
    // // System.out.println(read);
    //
    // SimpleFilter recipients = new Recipients("a",
    // FilterOperator.CONTAINS,getMessage());
    // System.out.println(recipients);
    //
    SimpleFilter subject = new Subject("a", FilterOperator.CONTAINS, getMessage());
    // System.out.println(subject);
    //
    SimpleFilter text = new Text("a", FilterOperator.CONTAINS, getMessage());
    // System.out.println(text);
    //
    SimpleFilter importance = new MyImportance(Importance.LOW, getMessage());
    // System.out.println(importance);
    //
    SimpleFilter union = new Union(text, subject);
    System.out.println(union);

    SimpleFilter intersection = new Intersection(union, subject);
    // System.out.println(intersection);

  }

  public static Collection<Message> getMessage() {
    return new RandomTestDataProvider(MAILCOUNT).getMessages();
  }

}
