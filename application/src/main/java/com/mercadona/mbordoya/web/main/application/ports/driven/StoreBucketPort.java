package com.mercadona.mbordoya.web.main.application.ports.driven;

import java.net.URL;

public interface StoreBucketPort {

  URL uploadProductCsv(byte[] bytes);
}
