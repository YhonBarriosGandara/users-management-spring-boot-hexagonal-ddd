package com.jcaa.usersmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import com.jcaa.usersmanagement.domain.valueobject.FincaNombre;
import com.jcaa.usersmanagement.domain.valueobject.FincaPersonal;
import com.jcaa.usersmanagement.domain.valueobject.FincaProduccion;
import com.jcaa.usersmanagement.domain.valueobject.FincaSuperficie;
import com.jcaa.usersmanagement.domain.valueobject.FincaUbicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FincaModel")
class FincaModelTest {

  @Test
  @DisplayName("create() debe instanciar FincaModel preservando todos los Value Objects")
  void shouldCreateFincaModelCorrectly() {
    final FincaModel finca =
        FincaModel.create(
            new FincaId("f-001"),
            new FincaNombre("Hacienda San Pedro"),
            FincaSuperficie.fromHectareas(50.0),
            new FincaPersonal("Carlos Gómez", "Manuel Santos"),
            new FincaUbicacion("Colombia", "Antioquia", "Rionegro"),
            FincaProduccion.of(true, false, true, false));

    assertNotNull(finca);
    assertEquals("f-001", finca.getId().value());
    assertEquals("Hacienda San Pedro", finca.getNombre().value());
    assertEquals(50.0, finca.getSuperficie().numHectareas());
    assertEquals(500000.0, finca.getSuperficie().metrosCuadrados());
    assertEquals("Carlos Gómez", finca.getPersonal().propietario());
    assertEquals("Manuel Santos", finca.getPersonal().capataz());
    assertTrue(finca.getProduccion().produceLeche());
    assertFalse(finca.getProduccion().produceCereales());
    assertTrue(finca.getProduccion().produceFrutas());
    assertFalse(finca.getProduccion().produceVerduras());
  }

  @Test
  @DisplayName("fromPrimitives() debe instanciar FincaModel a partir de tipos primitivos")
  void shouldCreateFincaFromPrimitives() {
    final FincaModel finca =
        FincaModel.fromPrimitives(
            "f-002",
            "Finca El Paraíso",
            30.0,
            300000.0,
            "Jorge Isaacs",
            "Aníbal Cardona",
            "Colombia",
            "Valle del Cauca",
            "El Cerrito",
            false,
            true,
            true,
            false);

    assertEquals("f-002", finca.getId().value());
    assertEquals("Finca El Paraíso", finca.getNombre().value());
    assertEquals(30.0, finca.getSuperficie().numHectareas());
    assertEquals(300000.0, finca.getSuperficie().metrosCuadrados());
  }

  @Test
  @DisplayName("update() debe generar un nuevo FincaModel inmutable con los datos modificados")
  void shouldUpdateFincaImmutably() {
    final FincaModel original =
        FincaModel.fromPrimitives(
            "f-003",
            "Finca Antigua",
            20.0,
            200000.0,
            "Propietario 1",
            "Capataz 1",
            "Colombia",
            "Córdoba",
            "Montería",
            false,
            false,
            false,
            false);

    final FincaModel updated =
        original.update(
            new FincaNombre("Finca Renovada"),
            FincaSuperficie.fromHectareas(25.0),
            new FincaPersonal("Propietario 2", "Capataz 2"),
            new FincaUbicacion("Colombia", "Córdoba", "Cereté"),
            FincaProduccion.of(true, true, true, true));

    assertEquals("f-003", updated.getId().value());
    assertEquals("Finca Renovada", updated.getNombre().value());
    assertEquals(25.0, updated.getSuperficie().numHectareas());
    assertEquals(250000.0, updated.getSuperficie().metrosCuadrados());
    assertEquals("Propietario 2", updated.getPersonal().propietario());
  }
}
