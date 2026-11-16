package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.FincaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.FincaEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FincaPersistenceMapper {

  public FincaPersistenceDto fromModelToDto(final FincaModel finca) {
    return new FincaPersistenceDto(
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
        finca.getProduccion().produceVerduras(),
        null,
        null);
  }

  public FincaEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new FincaEntity(
        resultSet.getString("id"),
        resultSet.getString("nombre"),
        resultSet.getDouble("num_hectareas"),
        resultSet.getDouble("metros_cuadrados"),
        resultSet.getString("propietario"),
        resultSet.getString("capataz"),
        resultSet.getString("pais"),
        resultSet.getString("departamento"),
        resultSet.getString("ciudad"),
        resultSet.getBoolean("produce_leche"),
        resultSet.getBoolean("produce_cereales"),
        resultSet.getBoolean("produce_frutas"),
        resultSet.getBoolean("produce_verduras"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public FincaModel fromEntityToModel(final FincaEntity entity) {
    return FincaModel.fromPrimitives(
        entity.id(),
        entity.nombre(),
        entity.numHectareas(),
        entity.metrosCuadrados(),
        entity.propietario(),
        entity.capataz(),
        entity.pais(),
        entity.departamento(),
        entity.ciudad(),
        entity.produceLeche(),
        entity.produceCereales(),
        entity.produceFrutas(),
        entity.produceVerduras());
  }

  public List<FincaModel> fromEntitiesToModels(final List<FincaEntity> entities) {
    final List<FincaModel> models = new ArrayList<>();
    for (final FincaEntity entity : entities) {
      models.add(fromEntityToModel(entity));
    }
    return models;
  }
}
