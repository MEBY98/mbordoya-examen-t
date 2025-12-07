package com.mercadona.mbordoya.web.main.application.exceptions;

public class InvalidPasswordException extends ApplicationException {

  public InvalidPasswordException() {
    super(403, "INVALID_PASSWORD_ERROR_CODE", "INVALID_PASSWORD_ERROR_CODE_DETAIL");
  }
}
