package com.mercadona.mbordoya.web.main.driven.bucket.adapters;

import com.google.common.io.ByteSource;
import com.google.common.net.MediaType;
import com.mercadona.framework.cna.lib.bucket.service.BucketService;
import com.mercadona.mbordoya.web.main.application.ports.driven.StoreBucketPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

@Repository
@Slf4j
public class StoreBucketAdapter implements StoreBucketPort {

  public static final Long SEVEN_DAYS = 604800L;

  private final String storeBucket;
  private final BucketService bucketService;

  public StoreBucketAdapter(@Value("${fwkcna.buckets[2].id}") final String storeBucket,
                            final BucketService bucketService) {
    this.storeBucket = storeBucket;
    this.bucketService = bucketService;
  }

  @Override
  public URL uploadProductCsv(final byte[] bytes) {
    final String fileName = "products-csv-" + LocalDateTime.now() + ".csv";
    this.bucketService.upload(storeBucket, ByteSource.wrap(bytes), fileName, MediaType.CSV_UTF_8, null);
    try {
      return this.bucketService.getSignedUrl(
          storeBucket,
          fileName,
          SEVEN_DAYS).toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
