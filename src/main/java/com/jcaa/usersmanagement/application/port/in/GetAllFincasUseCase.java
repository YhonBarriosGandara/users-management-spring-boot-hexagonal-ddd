package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.FincaModel;
import java.util.List;

public interface GetAllFincasUseCase {
  List<FincaModel> execute();
}
