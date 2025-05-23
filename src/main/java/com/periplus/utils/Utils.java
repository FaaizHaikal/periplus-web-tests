package com.periplus.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
  public static String getBookId(String url) {
        Pattern pattern = Pattern.compile("/p/(.*?)/");
    Matcher matcher = pattern.matcher(url);

    if (matcher.find(1)) {
      return matcher.group(1);
    }

    throw new RuntimeException("Book ID not found in URL: " + url);
  }
}
