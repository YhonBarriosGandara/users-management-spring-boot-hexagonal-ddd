package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidFincaDataException;
import java.util.Objects;

public record FincaUbicacion(String pais, String departamento, String ciudad) {

  public FincaUbicacion {
    final String normPais = Objects.requireNonNullElse(pais, "Colombia").trim();
    final String normDep = Objects.requireNonNull(departamento, "Departamento cannot be null").trim();
    final String normCiu = Objects.requireNonNull(ciudad, "Ciudad cannot be null").trim();

    if (normDep.isEmpty()) {
      throw InvalidFincaDataException.becauseFieldIsEmpty("departamento");
    }
    if (normCiu.isEmpty()) {
      throw InvalidFincaDataException.becauseFieldIsEmpty("ciudad");
    }

    pais = normPais.isEmpty() ? "Colombia" : normPais;
    departamento = normDep;
    ciudad = normCiu;
  }
}
