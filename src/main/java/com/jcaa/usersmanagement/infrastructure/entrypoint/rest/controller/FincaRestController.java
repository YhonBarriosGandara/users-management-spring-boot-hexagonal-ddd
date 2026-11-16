package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateFincaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteFincaUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllFincasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetFincaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateFincaUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetFincaByIdQuery;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateFincaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateFincaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.FincaRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.FincaRestMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fincas")
@RequiredArgsConstructor
public class FincaRestController implements FincaRestControllerDocs {

  private final CreateFincaUseCase createFincaUseCase;
  private final UpdateFincaUseCase updateFincaUseCase;
  private final DeleteFincaUseCase deleteFincaUseCase;
  private final GetFincaByIdUseCase getFincaByIdUseCase;
  private final GetAllFincasUseCase getAllFincasUseCase;

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FincaRestResponse create(@Valid @RequestBody final CreateFincaRestRequest request) {
    final CreateFincaCommand command = FincaRestMapper.toCreateCommand(request);
    final FincaModel finca = createFincaUseCase.execute(command);
    return FincaRestMapper.toResponse(finca);
  }

  @Override
  @GetMapping
  public List<FincaRestResponse> getAll() {
    return FincaRestMapper.toResponseList(getAllFincasUseCase.execute());
  }

  @Override
  @GetMapping("/{id}")
  public FincaRestResponse getById(@PathVariable final String id) {
    final GetFincaByIdQuery query = FincaRestMapper.toGetByIdQuery(id);
    final FincaModel finca = getFincaByIdUseCase.execute(query);
    return FincaRestMapper.toResponse(finca);
  }

  @Override
  @PutMapping("/{id}")
  public FincaRestResponse update(
      @PathVariable final String id,
      @Valid @RequestBody final UpdateFincaRestRequest request) {
    final UpdateFincaCommand command = FincaRestMapper.toUpdateCommand(id, request);
    final FincaModel finca = updateFincaUseCase.execute(command);
    return FincaRestMapper.toResponse(finca);
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final String id) {
    final DeleteFincaCommand command = FincaRestMapper.toDeleteCommand(id);
    deleteFincaUseCase.execute(command);
  }
}
