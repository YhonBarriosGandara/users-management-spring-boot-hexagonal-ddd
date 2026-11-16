package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateFincaUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveFincaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateFincaCommand;
import com.jcaa.usersmanagement.application.service.mapper.FincaApplicationMapper;
import com.jcaa.usersmanagement.domain.model.FincaModel;
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
public class CreateFincaService implements CreateFincaUseCase {

  private final SaveFincaPort saveFincaPort;
  private final Validator validator;

  @Override
  public FincaModel execute(final CreateFincaCommand command) {
    validateCommand(command);

    final FincaModel fincaToSave = FincaApplicationMapper.fromCreateCommandToModel(command);
    final FincaModel savedFinca = saveFincaPort.save(fincaToSave);

    log.info("Finca created successfully with id '{}'", savedFinca.getId().value());
    return savedFinca;
  }

  private void validateCommand(final CreateFincaCommand command) {
    final Set<ConstraintViolation<CreateFincaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
