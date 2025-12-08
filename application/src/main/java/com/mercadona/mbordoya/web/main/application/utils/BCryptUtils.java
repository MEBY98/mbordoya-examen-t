package com.mercadona.mbordoya.web.main.application.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

  public static String hashPassword(final String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(12));
  }

  public static boolean verifyPassword(final String password, final String hashGuardado) {
    return BCrypt.checkpw(password, hashGuardado);
  }
}
