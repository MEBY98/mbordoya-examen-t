package com.mercadona.mbordoya.web.main.driven.repositories.specifications;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.StoreMO;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.text.Normalizer;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class StoreSpecification implements Specification<StoreMO> {

  private final String name;

  @Override
  public Predicate toPredicate(final Root<StoreMO> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
    assert query != null;

    query.distinct(true);
    Predicate predicate = criteriaBuilder.conjunction();

    if (name != null) {
      predicate = criteriaBuilder.and(predicate, buildLikeUnaccentAndLowerCase(root.get("name"), name, criteriaBuilder));
    }

    return predicate;
  }

  private static final String UNACCENTED_REGEX = "\\p{M}";

  private Predicate buildLikeUnaccentAndLowerCase(final Path<String> path, final String value,
                                                  final CriteriaBuilder builder) {
    return builder.like(builder.lower(path), "%" + requireNonNull(value).toLowerCase() + "%");
  }

  private static String removeAccents(final String string) {
    return isValidString(string)
        ? Normalizer.normalize(string, Normalizer.Form.NFKD).replaceAll(UNACCENTED_REGEX, "")
        : null;
  }

  private static boolean isValidString(final String string) {
    return string != null && !string.isBlank();
  }
}
