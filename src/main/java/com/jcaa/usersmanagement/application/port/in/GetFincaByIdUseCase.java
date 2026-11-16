package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetFincaByIdQuery;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetFincaByIdUseCase {
  FincaModel execute(@NotNull @Valid GetFincaByIdQuery query);
}
