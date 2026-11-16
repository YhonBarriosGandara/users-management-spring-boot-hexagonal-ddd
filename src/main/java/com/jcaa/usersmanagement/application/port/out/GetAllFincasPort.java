package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.FincaModel;
import java.util.List;

public interface GetAllFincasPort {
  List<FincaModel> getAll();
}
