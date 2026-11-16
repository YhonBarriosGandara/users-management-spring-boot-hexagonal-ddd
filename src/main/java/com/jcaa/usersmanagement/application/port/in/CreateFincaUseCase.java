package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateFincaCommand;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateFincaUseCase {
  FincaModel execute(@NotNull @Valid CreateFincaCommand command);
}
