package com.jcaa.usersmanagement.domain.exception;

public final class InvalidFincaDataException extends DomainException {

  private InvalidFincaDataException(final String message) {
    super(message);
  }

  public static InvalidFincaDataException becauseFieldIsEmpty(final String fieldName) {
    return new InvalidFincaDataException(String.format("The field '%s' cannot be null or empty.", fieldName));
  }

  public static InvalidFincaDataException becauseValueMustBePositive(final String fieldName) {
    return new InvalidFincaDataException(String.format("The field '%s' must be strictly positive (> 0).", fieldName));
  }

  public static InvalidFincaDataException becauseLengthIsInvalid(final String fieldName, final int min, final int max) {
    return new InvalidFincaDataException(
        String.format("The field '%s' must have length between %d and %d characters.", fieldName, min, max));
  }

  public static InvalidFincaDataException becauseGeneric(final String message) {
    return new InvalidFincaDataException(message);
  }
}
