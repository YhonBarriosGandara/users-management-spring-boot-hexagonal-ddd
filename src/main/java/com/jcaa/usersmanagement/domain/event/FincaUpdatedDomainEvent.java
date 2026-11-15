package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.FincaModel;
import java.util.Map;
import lombok.Getter;

@Getter
public final class FincaUpdatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "finca.updated";

  private final FincaModel finca;

  public FincaUpdatedDomainEvent(final FincaModel finca) {
    super(EVENT_NAME);
    this.finca = finca;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id", finca.getId().value(),
        "nombre", finca.getNombre().value(),
        "capataz", finca.getPersonal().capataz(),
        "numHectareas", String.valueOf(finca.getSuperficie().numHectareas()));
  }
}
