package com.mercadona.mbordoya.web.main.application.exceptions;

public class ExampleNotFoundException extends ApplicationException {

  public ExampleNotFoundException(final Long id) {
    super(400, "EXAMPLE_NOT_FOUND_ERROR_CODE", "EXAMPLE_NOT_FOUND_ERROR_CODE_DETAIL", id);
  }
}
