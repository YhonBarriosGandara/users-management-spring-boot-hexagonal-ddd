package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidFincaDataException;

public record FincaSuperficie(double numHectareas, double metrosCuadrados) {

  private static final double METROS_CUADRADOS_POR_HECTAREA = 10000.0;

  public FincaSuperficie {
    if (numHectareas <= 0) {
      throw InvalidFincaDataException.becauseValueMustBePositive("numHectareas");
    }
    // Si metrosCuadrados no fue suministrado o es 0, se calcula automáticamente
    if (metrosCuadrados <= 0) {
      metrosCuadrados = numHectareas * METROS_CUADRADOS_POR_HECTAREA;
    }
  }

  public static FincaSuperficie fromHectareas(final double numHectareas) {
    return new FincaSuperficie(numHectareas, numHectareas * METROS_CUADRADOS_POR_HECTAREA);
  }

  public static FincaSuperficie of(final double numHectareas, final double metrosCuadrados) {
    return new FincaSuperficie(numHectareas, metrosCuadrados);
  }
}
