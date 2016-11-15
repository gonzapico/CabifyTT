package xyz.gonzapico.cabifytt.getEstimation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gfernandez on 14/11/16.
 */

public class Utils {

  public static String currentDateFormatted() {
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String currentDateFormatted = "";
    Date d = null;
    try {
      d = dataFormat.parse("2018-08-09 11:15");//catch exception
      //d = new Date();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      currentDateFormatted = dataFormat.format(d);
    } catch (Exception e) {
      currentDateFormatted = dataFormat.format(new Date());
    }
    return currentDateFormatted;
  }

  public final static int PLACE_AUTOCOMPLETE_REQUEST_CODE_START = 1;
  public final static int PLACE_AUTOCOMPLETE_REQUEST_CODE_END = 2;
}
