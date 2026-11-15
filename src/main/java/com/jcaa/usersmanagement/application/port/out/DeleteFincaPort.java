package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.FincaId;

public interface DeleteFincaPort {
  void delete(FincaId id);
}
