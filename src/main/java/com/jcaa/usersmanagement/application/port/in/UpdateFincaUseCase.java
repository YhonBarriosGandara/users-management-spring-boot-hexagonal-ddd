package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateFincaCommand;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateFincaUseCase {
  FincaModel execute(@NotNull @Valid UpdateFincaCommand command);
}
