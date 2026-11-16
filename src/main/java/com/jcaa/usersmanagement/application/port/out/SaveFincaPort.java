package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.FincaModel;

public interface SaveFincaPort {
  FincaModel save(FincaModel finca);
}
