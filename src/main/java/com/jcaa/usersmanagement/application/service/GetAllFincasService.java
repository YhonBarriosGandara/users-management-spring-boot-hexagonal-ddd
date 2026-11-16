package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllFincasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllFincasPort;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllFincasService implements GetAllFincasUseCase {

  private final GetAllFincasPort getAllFincasPort;

  @Override
  public List<FincaModel> execute() {
    return getAllFincasPort.getAll();
  }
}
