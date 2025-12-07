package com.mercadona.mbordoya.web.main.application.exceptions;

public class UserNotExistsException extends ApplicationException {

  public UserNotExistsException() {
    super(403, "USER_NOT_FOUND_ERROR_CODE", "USER_NOT_FOUND_ERROR_CODE");
  }
}
