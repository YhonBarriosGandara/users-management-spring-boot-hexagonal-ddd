package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteFincaPort;
import com.jcaa.usersmanagement.application.port.out.GetAllFincasPort;
import com.jcaa.usersmanagement.application.port.out.GetFincaByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveFincaPort;
import com.jcaa.usersmanagement.application.port.out.UpdateFincaPort;
import com.jcaa.usersmanagement.domain.exception.FincaNotFoundException;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import com.jcaa.usersmanagement.domain.valueobject.FincaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.FincaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.FincaEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.FincaPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FincaRepositoryMySQL
    implements SaveFincaPort,
        UpdateFincaPort,
        GetFincaByIdPort,
        GetAllFincasPort,
        DeleteFincaPort {

  private static final String SQL_INSERT =
      "INSERT INTO fincas "
          + "(id, nombre, num_hectareas, metros_cuadrados, propietario, capataz, pais, departamento, ciudad, "
          + "produce_leche, produce_cereales, produce_frutas, produce_verduras, created_at, updated_at) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE fincas SET nombre = ?, num_hectareas = ?, metros_cuadrados = ?, propietario = ?, capataz = ?, "
          + "pais = ?, departamento = ?, ciudad = ?, produce_leche = ?, produce_cereales = ?, produce_frutas = ?, "
          + "produce_verduras = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, nombre, num_hectareas, metros_cuadrados, propietario, capataz, pais, departamento, ciudad, "
          + "produce_leche, produce_cereales, produce_frutas, produce_verduras, created_at, updated_at "
          + "FROM fincas WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, nombre, num_hectareas, metros_cuadrados, propietario, capataz, pais, departamento, ciudad, "
          + "produce_leche, produce_cereales, produce_frutas, produce_verduras, created_at, updated_at "
          + "FROM fincas ORDER BY nombre ASC";

  private static final String SQL_DELETE = "DELETE FROM fincas WHERE id = ?";

  private final DataSource dataSource;

  @Override
  public FincaModel save(final FincaModel finca) {
    final FincaPersistenceDto dto = FincaPersistenceMapper.fromModelToDto(finca);
    executeSave(dto);
    return findByIdOrFail(finca.getId());
  }

  @Override
  public FincaModel update(final FincaModel finca) {
    final FincaPersistenceDto dto = FincaPersistenceMapper.fromModelToDto(finca);
    executeUpdate(dto);
    return findByIdOrFail(finca.getId());
  }

  @Override
  public Optional<FincaModel> getById(final FincaId id) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, id.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      final FincaEntity entity = FincaPersistenceMapper.fromResultSetToEntity(resultSet);
      return Optional.of(FincaPersistenceMapper.fromEntityToModel(entity));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByIdFailed(id.value(), exception);
    }
  }

  @Override
  public List<FincaModel> getAll() {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      final List<FincaEntity> entities = new ArrayList<>();
      while (resultSet.next()) {
        entities.add(FincaPersistenceMapper.fromResultSetToEntity(resultSet));
      }
      return FincaPersistenceMapper.fromEntitiesToModels(entities);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final FincaId id) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, id.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(id.value(), exception);
    }
  }

  private void executeSave(final FincaPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id());
      statement.setString(2, dto.nombre());
      statement.setDouble(3, dto.numHectareas());
      statement.setDouble(4, dto.metrosCuadrados());
      statement.setString(5, dto.propietario());
      statement.setString(6, dto.capataz());
      statement.setString(7, dto.pais());
      statement.setString(8, dto.departamento());
      statement.setString(9, dto.ciudad());
      statement.setBoolean(10, dto.produceLeche());
      statement.setBoolean(11, dto.produceCereales());
      statement.setBoolean(12, dto.produceFrutas());
      statement.setBoolean(13, dto.produceVerduras());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.id(), exception);
    }
  }

  private void executeUpdate(final FincaPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.nombre());
      statement.setDouble(2, dto.numHectareas());
      statement.setDouble(3, dto.metrosCuadrados());
      statement.setString(4, dto.propietario());
      statement.setString(5, dto.capataz());
      statement.setString(6, dto.pais());
      statement.setString(7, dto.departamento());
      statement.setString(8, dto.ciudad());
      statement.setBoolean(9, dto.produceLeche());
      statement.setBoolean(10, dto.produceCereales());
      statement.setBoolean(11, dto.produceFrutas());
      statement.setBoolean(12, dto.produceVerduras());
      statement.setString(13, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), exception);
    }
  }

  private FincaModel findByIdOrFail(final FincaId fincaId) {
    return getById(fincaId)
        .orElseThrow(() -> FincaNotFoundException.becauseIdWasNotFound(fincaId.value()));
  }
}
