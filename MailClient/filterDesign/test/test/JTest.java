package test;

import static org.junit.Assert.*;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import common.filter.FilterOperator;
import common.mail.model.Importance;
import common.mail.model.Message;
import common.mail.testdata.RandomTestDataProvider;
import filterdesign.Intersection;
import filterdesign.MyImportance;
import filterdesign.Read;
import filterdesign.Recipients;
import filterdesign.Sender;
import filterdesign.SimpleFilter;
import filterdesign.Subject;
import filterdesign.Text;
import filterdesign.Union;

public class JTest {

  private static int MAILCOUNT = 50;

  @Before
  public void sendertest() throws Exception {

    SimpleFilter sender = new Sender("a", FilterOperator.CONTAINS, getMessage());

    // System.out.println(sender);

  }

  @Test
  public void simpleTest() {
    SimpleFilter read = new Read(true, getMessage());
    assertNull(read);
    // System.out.println(read);
    //

  }

  @Test
  public void recipientsTest() {
    SimpleFilter recipients = new Recipients("a", FilterOperator.CONTAINS, getMessage());
    assertNull("check the important methode in the object/ do you somthing? ", recipients.filter(getMessage()));
    // System.out.println(recipients);
    //
  }

  @Test
  public void intersectionTest() {
    SimpleFilter subject = new Subject("a", FilterOperator.CONTAINS, getMessage());
    // System.out.println(subject);
    //
    SimpleFilter text = new Text("a", FilterOperator.CONTAINS, getMessage());
    // System.out.println(text);
    //
    //
    SimpleFilter union = new Union(text, subject);
    // System.out.println(union);
    SimpleFilter intersection = new Intersection(union, subject);
    assertSame(union.getSet(), intersection.getSet());
    // < ---- - funny!
    // intersection.getSet());
    // System.out.println(intersection);
  }

  @Test
  public void rest() {
    SimpleFilter importance = new MyImportance(Importance.LOW, getMessage());
    // System.out.println(importance);

  }

  public static Collection<Message> getMessage() {
    return new RandomTestDataProvider(MAILCOUNT).getMessages();
  }
}
