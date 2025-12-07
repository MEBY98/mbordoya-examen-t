package com.mercadona.mbordoya.web.main.application.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationException extends RuntimeException {

  private final int httpStatus;
  private final String errorCode;
  private final String errorDetailCode;
  private final Object[] args;

  public ApplicationException(final int httpStatus, final String errorCode, final String errorDetailCode,
                              final Object ...args) {
    super(errorCode);
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.errorDetailCode = errorDetailCode;
    this.args = args;
  }
}
