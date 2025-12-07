package com.mercadona.mbordoya.web.main.driven.bucket.adapters;

import com.google.common.io.ByteSource;
import com.google.common.net.MediaType;
import com.mercadona.framework.cna.lib.bucket.service.BucketService;
import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleBucketPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleBucketAdapter implements ExampleBucketPort {

  private final String bucketA;
  private final String bucketB;
  private final BucketService bucketService;

  public ExampleBucketAdapter(@Value("${fwkcna.buckets[0].id}") final String bucketA,
                              @Value("${fwkcna.buckets[1].id}") final String bucketB,
                              final BucketService bucketService) {
    this.bucketA = bucketA;
    this.bucketB = bucketB;
    this.bucketService = bucketService;
  }

  @Override
  public void saveCsvInBucketA(final String fileName, final byte[] bytes) {
    this.bucketService.upload(bucketA, ByteSource.wrap(bytes), fileName, MediaType.CSV_UTF_8, null);
  }

  @Override
  public void saveCsvInBucketB(final String fileName, final byte[] bytes) {
    this.bucketService.upload(bucketB, ByteSource.wrap(bytes), fileName, MediaType.CSV_UTF_8, null);
  }
}
