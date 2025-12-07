package com.mercadona.mbordoya.web.main.driven.repositories.specifications;

import com.mercadona.mbordoya.web.main.driven.repositories.models.ExampleMO;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.text.Normalizer;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class ExampleSpecification implements Specification<ExampleMO> {

  private final String name;
  private final Long id_1;
  private final Long id_2;

  @Override
  public Predicate toPredicate(final Root<ExampleMO> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
    assert query != null;

    query.distinct(true);
    Predicate predicate = criteriaBuilder.conjunction();

    if (name != null) {
      predicate = criteriaBuilder.and(predicate, buildLikeUnaccentAndLowerCase(root.get("name"), name, criteriaBuilder));
    }
    if (id_1 != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id_1"), id_1));
    }
    if (id_2 != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id_2"), id_2));
    }

    return predicate;
  }

  private static final String UNACCENT_POSTGRESQL_FUNCTION = "unaccent";
  private static final String UNACCENTED_REGEX = "\\p{M}";

  private Predicate buildLikeUnaccentAndLowerCase(final Path<String> path, final String value,
                                                    final CriteriaBuilder builder) {
    return builder.like(builder.function(UNACCENT_POSTGRESQL_FUNCTION, String.class
        , builder.lower(path)), "%" + requireNonNull(removeAccents(value)).toLowerCase() + "%");
  }

  private static String removeAccents(final String string) {
    return isValidString(string)
        ? Normalizer.normalize(string, Normalizer.Form.NFKD).replaceAll(UNACCENTED_REGEX, "")
        : null;
  }

  private static boolean isValidString(final String string) {
    return string != null && !string.isBlank();
  }

  private  <T> Path<T> getObjectPath(final Path<?> root, final String field) {
    final var path = field.split("\\.");

    Path<T> property = null;
    for (String s : path) {
      if (property == null) {
        property = root.get(s);
      } else {
        property = property.get(s);
      }
    }
    return property;
  }

}
