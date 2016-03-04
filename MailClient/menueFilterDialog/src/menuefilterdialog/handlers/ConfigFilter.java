package menuefilterdialog.handlers;

import java.util.ArrayList;

import java.util.List;

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
import filterdesigne.Intersection;
import filterdesigne.Read;
import filterdesigne.Recipients;
import filterdesigne.Sender;
import filterdesigne.SimpleFilter;
import filterdesigne.Subject;
import filterdesigne.Text;
import filterdesigne.Union;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ConfigFilter extends AbstractHandler {

  private final List<SimpleFilter> filterList = new ArrayList<SimpleFilter>();
  private SimpleFilter filter;
  private SimpleFilter filterfilter;

  /**
   * The constructor.
   */
  public ConfigFilter() {
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
      String a = filterfilter.toString();
      System.out.println(a);

      switch (filterType.value()) {
      case "Sender":
        filter = new Sender((String) filterValue, filterOperator);
        // System.out.println("Grouping: " + "union");
        break;

      case "Recipients":
        filter = new Recipients((String) filterValue, filterOperator);
        // System.out.println("Grouping: " + "Intersection");
        break;

      case "Subject":
        filter = new Subject((String) filterValue, filterOperator);
        System.out.println("Grouping: " + "Intersection");
        break;

      case "Contents of EMail": // Text!!!
        filter = new Text((String) filterValue, filterOperator);
        // System.out.println("Grouping: " + "Intersection");
        break;

      case "Read":
        filter = new Read((boolean) filterValue);
        // System.out.println("Grouping: " + "Intersection");
        break;

      case "Importance":
        // Importance importance = (Importance) filterValue;
        // filter = new MyImportance(importance);
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

    return filterfilter;
  }
}
