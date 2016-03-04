package maillist;

import java.util.Iterator;
import java.util.List;
import common.internal.Activator;
import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.properties.IValueFormatter;
import de.ralfebert.rcputils.properties.PropertyValue;

public class RecipientPropertyFormatter implements IValueFormatter<Object, Object> {

  private final IValue valueHandler;
  private Object objN;

  public RecipientPropertyFormatter(String propertyName) {
    this.valueHandler = new PropertyValue(propertyName);
  }

  @Override
  public Object format(Object obj) {
    try {

      List<?> ob = (List<?>) obj;
      for (Iterator<?> i = ob.iterator(); i.hasNext();) {
        objN = i.next();

      }
      return valueHandler.getValue(objN);
    } catch (Exception e) {
      Activator.logException(e);
      return null;
    }
  }

  @Override
  public Object parse(Object objN) {
    throw new UnsupportedOperationException();
  }

}
