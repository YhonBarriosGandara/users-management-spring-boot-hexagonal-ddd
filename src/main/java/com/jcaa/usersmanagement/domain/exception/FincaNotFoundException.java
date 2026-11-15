package com.jcaa.usersmanagement.domain.exception;

public final class FincaNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The finca with id '%s' was not found.";

  private FincaNotFoundException(final String message) {
    super(message);
  }

  public static FincaNotFoundException becauseIdWasNotFound(final String fincaId) {
    return new FincaNotFoundException(String.format(MESSAGE_BY_ID, fincaId));
  }
}
