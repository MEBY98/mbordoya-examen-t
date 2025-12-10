package com.mercadona.mbordoya.web.main.application.exceptions;

public class StoreNotFoundException extends ApplicationException{

  public StoreNotFoundException(final Object... args) {
    super(404, "STORE_NOT_FOUND_ERROR_CODE", "STORE_NOT_FOUND_ERROR_CODE_DETAIL", args);
  }
}
