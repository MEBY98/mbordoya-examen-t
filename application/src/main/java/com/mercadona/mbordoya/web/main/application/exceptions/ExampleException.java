package com.mercadona.mbordoya.web.main.application.exceptions;



public class ExampleException extends ApplicationException {

  public ExampleException() {
    super(500, "EXAMPLE_ERROR_CODE", "EXAMPLE_ERROR_CODE_DETAIL");
  }
}
