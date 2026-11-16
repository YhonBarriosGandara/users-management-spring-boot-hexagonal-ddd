package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidFincaDataException;
import java.util.Objects;
import java.util.UUID;

public record FincaId(String value) {

  public FincaId {
    final String normalizedValue = Objects.requireNonNull(value, "FincaId cannot be null").trim();
    if (normalizedValue.isEmpty()) {
      throw InvalidFincaDataException.becauseFieldIsEmpty("id");
    }
    value = normalizedValue;
  }

  public static FincaId generate() {
    return new FincaId(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return value;
  }
}
