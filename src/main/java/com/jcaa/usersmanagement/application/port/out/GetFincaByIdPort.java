package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import java.util.Optional;

public interface GetFincaByIdPort {
  Optional<FincaModel> getById(FincaId id);
}
