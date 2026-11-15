package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetFincaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetFincaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetFincaByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.FincaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.FincaNotFoundException;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFincaByIdService implements GetFincaByIdUseCase {

  private final GetFincaByIdPort getFincaByIdPort;
  private final Validator validator;

  @Override
  public FincaModel execute(final GetFincaByIdQuery query) {
    validateQuery(query);

    final FincaId fincaId = FincaApplicationMapper.fromGetByIdQueryToId(query);
    return getFincaByIdPort
        .getById(fincaId)
        .orElseThrow(() -> FincaNotFoundException.becauseIdWasNotFound(fincaId.value()));
  }

  private void validateQuery(final GetFincaByIdQuery query) {
    final Set<ConstraintViolation<GetFincaByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
