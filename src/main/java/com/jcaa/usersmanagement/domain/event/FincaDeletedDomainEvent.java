package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import java.util.Map;
import lombok.Getter;

@Getter
public final class FincaDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "finca.deleted";

  private final FincaId fincaId;

  public FincaDeletedDomainEvent(final FincaId fincaId) {
    super(EVENT_NAME);
    this.fincaId = fincaId;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of("id", fincaId.value());
  }
}
