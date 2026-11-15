package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetFincaByIdQuery;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateFincaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateFincaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.FincaRestResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FincaRestMapper {

  public CreateFincaCommand toCreateCommand(final CreateFincaRestRequest request) {
    return new CreateFincaCommand(
        request.id(),
        request.nombre(),
        request.numHectareas(),
        request.metrosCuadrados(),
        request.propietario(),
        request.capataz(),
        request.pais(),
        request.departamento(),
        request.ciudad(),
        request.produceLeche(),
        request.produceCereales(),
        request.produceFrutas(),
        request.produceVerduras());
  }

  public UpdateFincaCommand toUpdateCommand(final String id, final UpdateFincaRestRequest request) {
    return new UpdateFincaCommand(
        id,
        request.nombre(),
        request.numHectareas(),
        request.metrosCuadrados(),
        request.propietario(),
        request.capataz(),
        request.pais(),
        request.departamento(),
        request.ciudad(),
        request.produceLeche(),
        request.produceCereales(),
        request.produceFrutas(),
        request.produceVerduras());
  }

  public GetFincaByIdQuery toGetByIdQuery(final String id) {
    return new GetFincaByIdQuery(id);
  }

  public DeleteFincaCommand toDeleteCommand(final String id) {
    return new DeleteFincaCommand(id);
  }

  public FincaRestResponse toResponse(final FincaModel finca) {
    return new FincaRestResponse(
        finca.getId().value(),
        finca.getNombre().value(),
        finca.getSuperficie().numHectareas(),
        finca.getSuperficie().metrosCuadrados(),
        finca.getPersonal().propietario(),
        finca.getPersonal().capataz(),
        finca.getUbicacion().pais(),
        finca.getUbicacion().departamento(),
        finca.getUbicacion().ciudad(),
        finca.getProduccion().produceLeche(),
        finca.getProduccion().produceCereales(),
        finca.getProduccion().produceFrutas(),
        finca.getProduccion().produceVerduras());
  }

  public List<FincaRestResponse> toResponseList(final List<FincaModel> fincas) {
    final List<FincaRestResponse> responses = new ArrayList<>();
    for (final FincaModel finca : fincas) {
      responses.add(toResponse(finca));
    }
    return responses;
  }
}
