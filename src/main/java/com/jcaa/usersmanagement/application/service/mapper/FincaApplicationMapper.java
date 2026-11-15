package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateFincaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetFincaByIdQuery;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import com.jcaa.usersmanagement.domain.valueobject.FincaNombre;
import com.jcaa.usersmanagement.domain.valueobject.FincaPersonal;
import com.jcaa.usersmanagement.domain.valueobject.FincaProduccion;
import com.jcaa.usersmanagement.domain.valueobject.FincaSuperficie;
import com.jcaa.usersmanagement.domain.valueobject.FincaUbicacion;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FincaApplicationMapper {

  public FincaModel fromCreateCommandToModel(final CreateFincaCommand command) {
    return FincaModel.create(
        new FincaId(command.id()),
        new FincaNombre(command.nombre()),
        FincaSuperficie.fromHectareas(command.numHectareas()),
        new FincaPersonal(command.propietario(), command.capataz()),
        new FincaUbicacion(command.pais(), command.departamento(), command.ciudad()),
        FincaProduccion.of(
            command.produceLeche(),
            command.produceCereales(),
            command.produceFrutas(),
            command.produceVerduras()));
  }

  public FincaModel fromUpdateCommandToModel(final UpdateFincaCommand command) {
    return FincaModel.create(
        new FincaId(command.id()),
        new FincaNombre(command.nombre()),
        FincaSuperficie.fromHectareas(command.numHectareas()),
        new FincaPersonal(command.propietario(), command.capataz()),
        new FincaUbicacion(command.pais(), command.departamento(), command.ciudad()),
        FincaProduccion.of(
            command.produceLeche(),
            command.produceCereales(),
            command.produceFrutas(),
            command.produceVerduras()));
  }

  public FincaId fromGetByIdQueryToId(final GetFincaByIdQuery query) {
    return new FincaId(query.id());
  }

  public FincaId fromDeleteCommandToId(final DeleteFincaCommand command) {
    return new FincaId(command.id());
  }
}
