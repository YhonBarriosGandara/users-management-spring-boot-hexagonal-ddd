package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidFincaDataException;
import java.util.Objects;

public record FincaPersonal(String propietario, String capataz) {

  private static final int MIN_LENGTH = 3;

  public FincaPersonal {
    final String normProp = Objects.requireNonNull(propietario, "Propietario cannot be null").trim();
    final String normCap = Objects.requireNonNull(capataz, "Capataz cannot be null").trim();

    if (normProp.isEmpty() || normProp.length() < MIN_LENGTH) {
      throw InvalidFincaDataException.becauseLengthIsInvalid("propietario", MIN_LENGTH, 100);
    }
    if (normCap.isEmpty() || normCap.length() < MIN_LENGTH) {
      throw InvalidFincaDataException.becauseLengthIsInvalid("capataz", MIN_LENGTH, 100);
    }

    propietario = normProp;
    capataz = normCap;
  }
}
