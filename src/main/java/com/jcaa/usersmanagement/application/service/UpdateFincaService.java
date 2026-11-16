package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateFincaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetFincaByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateFincaPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateFincaCommand;
import com.jcaa.usersmanagement.application.service.mapper.FincaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.FincaNotFoundException;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateFincaService implements UpdateFincaUseCase {

  private final GetFincaByIdPort getFincaByIdPort;
  private final UpdateFincaPort updateFincaPort;
  private final Validator validator;

  @Override
  public FincaModel execute(final UpdateFincaCommand command) {
    validateCommand(command);

    final FincaId fincaId = new FincaId(command.id());
    getFincaByIdPort
        .getById(fincaId)
        .orElseThrow(() -> FincaNotFoundException.becauseIdWasNotFound(fincaId.value()));

    final FincaModel fincaToUpdate = FincaApplicationMapper.fromUpdateCommandToModel(command);
    final FincaModel updatedFinca = updateFincaPort.update(fincaToUpdate);

    log.info("Finca updated successfully with id '{}'", updatedFinca.getId().value());
    return updatedFinca;
  }

  private void validateCommand(final UpdateFincaCommand command) {
    final Set<ConstraintViolation<UpdateFincaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
