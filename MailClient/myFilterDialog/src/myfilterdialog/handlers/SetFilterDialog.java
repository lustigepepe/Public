package myfilterdialog.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import common.filter.FilterCombination;
import common.filter.FilterDialog;
import common.filter.FilterGroupType;
import common.filter.FilterOperator;
import common.filter.FilterType;
import common.mail.model.Importance;
import common.mail.model.Message;
import filterdesign.Intersection;
import filterdesign.MyImportance;
import filterdesign.Read;
import filterdesign.Recipients;
import filterdesign.Sender;
import filterdesign.SimpleFilter;
import filterdesign.Subject;
import filterdesign.Text;
import filterdesign.Union;
import maillist.XmlObserver;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SetFilterDialog extends AbstractHandler implements Observer {

  private final List<SimpleFilter> filterList = new ArrayList<SimpleFilter>();
  private SimpleFilter filter;
  private SimpleFilter filterfilter;

  // private final static int MAILCOUNT = 50;

  /**
   * The constructor.
   */
  public SetFilterDialog() {
    XmlObserver.getInstance().addObserver(this);
  }

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

    FilterDialog filterDialog = new FilterDialog(window.getShell());
    filterDialog.open();

    List<FilterCombination> filterCombinations = filterDialog.getFilterCombinations();

    FilterGroupType filterGroupType = filterDialog.getFilterGroupType();
    // System.out.println("Grouping: " + filterGroupType);

    int zahl = 0;
    for (FilterCombination filterCombination : filterCombinations) {

      zahl++;
      FilterType filterType = filterCombination.getFilterType();
      FilterOperator filterOperator = filterCombination.getFilterOperator();
      Object filterValue = filterCombination.getFilterValue();

      System.out.println("filterGroupType: " + filterGroupType + ",\n FilterType: " + filterType + ", FilterOperator: "
          + filterOperator + ", FilterValue " + filterValue);

      switch (filterType.value()) {
      case "Sender":
        filter = new Sender((String) filterValue, filterOperator, getMessage());
        // System.out.println("Grouping: " + "union");
        break;

      case "Recipients":
        filter = new Recipients((String) filterValue, filterOperator, getMessage());
        // System.out.println("Grouping: " + "Intersection");
        break;

      case "Subject":
        filter = new Subject((String) filterValue, filterOperator, getMessage());
        System.out.println("Grouping: " + "Intersection");
        break;

      case "Contents of EMail": // Text!!!
        filter = new Text((String) filterValue, filterOperator, getMessage());
        // System.out.println("Grouping: " + "Intersection");
        break;

      case "Read":
        filter = new Read((boolean) filterValue, getMessage());
        // System.out.println("Grouping: " + "Intersection");
        break;

      case "Importance":
        Importance importance = (Importance) filterValue;
        filter = new MyImportance(importance, getMessage());
        // System.out.println("Grouping: " + "Intersection");
        break;
      }
      filterList.add(filter);
      if (zahl >= 2) {

        switch (filterGroupType.value()) {
        case "one":
          if (zahl == 2) {
            filterfilter = new Union(filterList.get(0), filterList.get(1));
            // System.out.println("Grouping: " + filterList.get(0) +
          }
          if (zahl > 2) {
            filterfilter = new Union(filterfilter, filterList.get(zahl));

          }
          break;
        case "all":
          if (zahl == 2) {
            filterfilter = new Intersection(filterList.get(0), filterList.get(1));
            // System.out.println("Grouping: " + "Intersection");
          }
          if (zahl > 2) {
            filterfilter = new Intersection(filterfilter, filterList.get(zahl));

          }
          break;
        }
      }

    }
    if (zahl < 2) {
      return filter;
    }
    System.out.println("OK bis zum filterfilter");
    return filterfilter;
  }

  private Collection<Message> getMessage() {
    try {

      return XmlObserver.getInstance().getXmlList();
    } catch (Exception e) {
      System.out.println("" + e + ": Keine Emails to filter in the Dialog");
      return null;
    }

  }

  // public static Collection<Message> getMessage() {
  // return new RandomTestDataProvider(MAILCOUNT).getMessages();
  // }

  @Override
  public void update(Observable arg0, Object arg1) {

  }
}