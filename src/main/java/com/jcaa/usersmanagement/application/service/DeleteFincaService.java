package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteFincaUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteFincaPort;
import com.jcaa.usersmanagement.application.port.out.GetFincaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteFincaCommand;
import com.jcaa.usersmanagement.application.service.mapper.FincaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.FincaNotFoundException;
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
public class DeleteFincaService implements DeleteFincaUseCase {

  private final GetFincaByIdPort getFincaByIdPort;
  private final DeleteFincaPort deleteFincaPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteFincaCommand command) {
    validateCommand(command);

    final FincaId fincaId = FincaApplicationMapper.fromDeleteCommandToId(command);
    getFincaByIdPort
        .getById(fincaId)
        .orElseThrow(() -> FincaNotFoundException.becauseIdWasNotFound(fincaId.value()));

    deleteFincaPort.delete(fincaId);
    log.info("Finca deleted successfully with id '{}'", fincaId.value());
  }

  private void validateCommand(final DeleteFincaCommand command) {
    final Set<ConstraintViolation<DeleteFincaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
