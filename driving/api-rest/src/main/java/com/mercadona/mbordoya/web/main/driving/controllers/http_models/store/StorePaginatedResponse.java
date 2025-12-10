package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store;

import com.mercadona.framework.cna.commons.rest.api.model.Pagination;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StorePaginatedResponse {
  private List<StoreResponse> data;
  private Pagination pagination;
}
