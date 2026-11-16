package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import com.jcaa.usersmanagement.domain.valueobject.FincaNombre;
import com.jcaa.usersmanagement.domain.valueobject.FincaPersonal;
import com.jcaa.usersmanagement.domain.valueobject.FincaProduccion;
import com.jcaa.usersmanagement.domain.valueobject.FincaSuperficie;
import com.jcaa.usersmanagement.domain.valueobject.FincaUbicacion;
import lombok.Value;

@Value
public class FincaModel {

  FincaId id;
  FincaNombre nombre;
  FincaSuperficie superficie;
  FincaPersonal personal;
  FincaUbicacion ubicacion;
  FincaProduccion produccion;

  public static FincaModel create(
      final FincaId id,
      final FincaNombre nombre,
      final FincaSuperficie superficie,
      final FincaPersonal personal,
      final FincaUbicacion ubicacion,
      final FincaProduccion produccion) {
    return new FincaModel(id, nombre, superficie, personal, ubicacion, produccion);
  }

  public static FincaModel fromPrimitives(
      final String id,
      final String nombre,
      final double numHectareas,
      final double metrosCuadrados,
      final String propietario,
      final String capataz,
      final String pais,
      final String departamento,
      final String ciudad,
      final boolean produceLeche,
      final boolean produceCereales,
      final boolean produceFrutas,
      final boolean produceVerduras) {
    return new FincaModel(
        new FincaId(id),
        new FincaNombre(nombre),
        FincaSuperficie.of(numHectareas, metrosCuadrados),
        new FincaPersonal(propietario, capataz),
        new FincaUbicacion(pais, departamento, ciudad),
        FincaProduccion.of(produceLeche, produceCereales, produceFrutas, produceVerduras));
  }

  public FincaModel update(
      final FincaNombre newNombre,
      final FincaSuperficie newSuperficie,
      final FincaPersonal newPersonal,
      final FincaUbicacion newUbicacion,
      final FincaProduccion newProduccion) {
    return new FincaModel(this.id, newNombre, newSuperficie, newPersonal, newUbicacion, newProduccion);
  }
}
