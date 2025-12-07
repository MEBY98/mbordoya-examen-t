package com.mercadona.mbordoya.web.main.driving.controllers.error;

import com.mercadona.framework.cna.commons.rest.api.model.ErrorResource;
import com.mercadona.framework.cna.commons.rest.api.model.ErrorResourceResponse;
import com.mercadona.mbordoya.web.main.application.exceptions.ApplicationException;
import com.mercadona.mbordoya.web.main.application.exceptions.ExampleException;
import com.mercadona.mbordoya.web.main.application.exceptions.MessageService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Collections.singletonList;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExampleExceptionController {

  private final MessageService messageService;

  @ExceptionHandler({ExampleException.class})
  public ResponseEntity<ErrorResourceResponse> handleExampleException(final ExampleException ex) {
    return ResponseEntity
        .status(ex.getHttpStatus())
        .body(buildErrorResponse(ex));
  }

  private ErrorResourceResponse buildErrorResponse(final ApplicationException ex) {
    return ErrorResourceResponse.builder()
        .error(buildError(ex))
        .build();
  }

  private ErrorResource buildError(final ApplicationException ex) {
    return ErrorResource.builder()
        .code(ex.getErrorCode())
        .description(this.messageService.getMessage(ex.getMessage()))
        .details(singletonList(this.messageService.getMessage(ex.getErrorDetailCode())))
        .build();
  }
}
