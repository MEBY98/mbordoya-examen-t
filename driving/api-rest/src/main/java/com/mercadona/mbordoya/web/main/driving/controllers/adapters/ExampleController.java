package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driving.ExampleUseCasePort;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import com.mercadona.mbordoya.web.main.driving.controllers.api.ExampleApi;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.ExampleCreateRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.mappers.ExampleControllerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExampleController implements ExampleApi {

  private final ExampleUseCasePort exampleUseCasePort;
  private final ExampleControllerMapper exampleControllerMapper;

  @Override
  public ResponseEntity<ExampleDomain> getExample(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(this.exampleUseCasePort.getExampleById(id));
  }

  @Override
  public ResponseEntity<Long> createExample(@RequestBody final ExampleCreateRequest exampleRequest) {
    final var example = this.exampleControllerMapper.toExample(exampleRequest);
    return ResponseEntity.ok(this.exampleUseCasePort.createExample(example));
  }

  @Override
  public ResponseEntity<ExampleDomain> uploadCsvWithOrders(@PathVariable(name = "id", value = "id") final Long id,
                                                        @RequestParam final MultipartFile file) throws IOException {
    final byte[] bytes = file.getBytes();
    final String csvString = new String(bytes);
    final List<String> rows = Arrays.asList(csvString.split("\n"));

    final var childrenDescriptions = new LinkedList<String>();
    IntStream.range(0, rows.size()).forEach(i -> {
      final String row = rows.get(i);
      if (i == 0) {
        // Header processing if needed
        // Header processing if needed
      } else {
        final String[] columns = row.split(",");
        childrenDescriptions.add(columns[1]);
      }
    });
    this.exampleUseCasePort.createExampleChildrenFromCsv(id, childrenDescriptions);
    this.exampleUseCasePort.saveCsvInBucket("example-" + id + "-"+ LocalDateTime.now() + ".csv", bytes);

    return this.getExample(id);
  }
}
