package com.jcaa.usersmanagement.domain.valueobject;

public record FincaProduccion(
    boolean produceLeche,
    boolean produceCereales,
    boolean produceFrutas,
    boolean produceVerduras) {

  public static FincaProduccion of(
      final boolean produceLeche,
      final boolean produceCereales,
      final boolean produceFrutas,
      final boolean produceVerduras) {
    return new FincaProduccion(produceLeche, produceCereales, produceFrutas, produceVerduras);
  }
}
