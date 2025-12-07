package com.mercadona.mbordoya.web.main.application.ports.driven;

public interface ExampleBucketPort {

  void saveCsvInBucketA(String fileName, byte[] bytes);

  void saveCsvInBucketB(String fileName, byte[] bytes);
}
