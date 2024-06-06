package com.gcsistemas.vendasadmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsSigeve {

  public UtilsSigeve() {

  }

  public static Date str2date(String data) {

    try {

      if (data == null || data.length() == 0)
        return null;

      if (data.trim().length() == 8) {

        int ano = Integer.parseInt(data.substring(6));
        String sec;

        if (ano <= 49)
          sec = "20";
        else
          sec = "19";

        data = data.substring(0, 6) + sec + data.substring(6);

      }

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

      return format.parse(data);

    } catch (ParseException e) {

      e.printStackTrace();
      return null;

    }

  }

  public static String date2str(Date data) {

    if (data == null)
      return "";

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    return format.format(data);

  }

  public static Date getSimpleDate(Date d) { // retira os mm:ss

    if (d == null)
      return null;

    String date = date2str(d);
    return UtilsSigeve.str2date(date);

  }

}
