package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.FincaModel;
import java.util.Map;
import lombok.Getter;

@Getter
public final class FincaCreatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "finca.created";

  private final FincaModel finca;

  public FincaCreatedDomainEvent(final FincaModel finca) {
    super(EVENT_NAME);
    this.finca = finca;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id", finca.getId().value(),
        "nombre", finca.getNombre().value(),
        "propietario", finca.getPersonal().propietario(),
        "ciudad", finca.getUbicacion().ciudad(),
        "departamento", finca.getUbicacion().departamento());
  }
}
