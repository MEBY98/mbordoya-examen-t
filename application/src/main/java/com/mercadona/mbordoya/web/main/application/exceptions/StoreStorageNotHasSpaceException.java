package com.mercadona.mbordoya.web.main.application.exceptions;

public class StoreStorageNotHasSpaceException extends ApplicationException {

  public StoreStorageNotHasSpaceException(final Object... args) {
    super(400, "STORE_STORAGE_NOT_HAS_SPACE_ERROR_CODE", "STORE_STORAGE_NOT_HAS_SPACE_ERROR_CODE_DETAIL", args);
  }
}
