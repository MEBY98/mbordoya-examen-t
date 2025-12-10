package com.mercadona.mbordoya.web.main.driving.controllers.api;

import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.ExampleCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/examples")
@Tag(name = "Example API", description = "API for Example operations")
public interface ExampleApi {

  @Operation(summary = "Get example by ID", security = @SecurityRequirement(name = "adfs"))
  @GetMapping("/{id}")
  ResponseEntity<ExampleDomain> getExample(@PathVariable("id") Long id);

  @Operation(summary = "Create example", security = @SecurityRequirement(name = "adfs"))
  @PostMapping
  ResponseEntity<Long> createExample(@RequestBody ExampleCreateRequest exampleRequest);

  @Operation(summary = "Upload example children from CSV", security = @SecurityRequirement(name = "adfs"))
  @PostMapping("{id}/children-csv")
  ResponseEntity<ExampleDomain> uploadCsvWithOrders(@PathVariable(name = "id", value = "id") Long id,
                                                    @RequestParam MultipartFile file) throws IOException;
}
