package com.mercadona.mbordoya.web.main.application.utils;

import java.util.List;

public interface Exportable {
  List<String> getRow();

  default String formatForExcel(String text) {
    if (StringUtils.isValidString(text)) {
      text = text.replace(";", " ");
      return "=\"" + text + "\"";
    } else {
      return "=\"\"";
    }
  }
}
