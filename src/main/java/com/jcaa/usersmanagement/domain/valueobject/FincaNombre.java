package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidFincaDataException;
import java.util.Objects;

public record FincaNombre(String value) {

  private static final int MIN_LENGTH = 3;
  private static final int MAX_LENGTH = 100;

  public FincaNombre {
    final String normalizedValue = Objects.requireNonNull(value, "FincaNombre cannot be null").trim();
    if (normalizedValue.isEmpty()) {
      throw InvalidFincaDataException.becauseFieldIsEmpty("nombre");
    }
    if (normalizedValue.length() < MIN_LENGTH || normalizedValue.length() > MAX_LENGTH) {
      throw InvalidFincaDataException.becauseLengthIsInvalid("nombre", MIN_LENGTH, MAX_LENGTH);
    }
    value = normalizedValue;
  }

  @Override
  public String toString() {
    return value;
  }
}
