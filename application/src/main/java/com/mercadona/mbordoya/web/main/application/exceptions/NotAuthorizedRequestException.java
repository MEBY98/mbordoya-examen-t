package com.mercadona.mbordoya.web.main.application.exceptions;

public class NotAuthorizedRequestException extends ApplicationException {

  public NotAuthorizedRequestException() {
    super(401, "NOT_AUTHORIZED_ERROR_CODE", "NOT_AUTHORIZED_ERROR_CODE_DETAIL");
  }
}
