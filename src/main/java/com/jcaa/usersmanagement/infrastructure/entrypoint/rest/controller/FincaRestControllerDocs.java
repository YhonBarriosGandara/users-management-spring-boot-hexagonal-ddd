package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateFincaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateFincaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ApiErrorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.FincaRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Contrato OpenAPI / Swagger para {@link FincaRestController}.
 * Ejercicio N.º 4: Gestión de Fincas - Arquitectura Hexagonal y DDD.
 */
@Tag(name = "Fincas", description = "Gestión integral de predios agropecuarios (Fincas): CRUDL completo.")
public interface FincaRestControllerDocs {

  @Operation(
      summary = "Crear finca",
      description = "Registra una nueva finca en el sistema con sus 12 atributos de negocio e integridad espacial.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Finca creada exitosamente.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FincaRestResponse.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Datos de entrada inválidos.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  FincaRestResponse create(@Valid @RequestBody CreateFincaRestRequest request);

  @Operation(
      summary = "Listar todas las fincas",
      description = "Retorna el listado completo de fincas registradas ordenadas alfabéticamente por nombre.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Lista de fincas obtenida exitosamente.",
        content =
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = FincaRestResponse.class)))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  List<FincaRestResponse> getAll();

  @Operation(
      summary = "Consultar finca por ID",
      description = "Obtiene los detalles completos de una finca específica según su identificador único.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Finca encontrada.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FincaRestResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "Finca no encontrada.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  FincaRestResponse getById(
      @Parameter(description = "Identificador único de la finca", required = true)
      @PathVariable String id);

  @Operation(
      summary = "Actualizar finca",
      description = "Modifica los atributos de una finca existente a partir de su ID.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Finca actualizada exitosamente.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FincaRestResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "Finca no encontrada para actualizar.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  FincaRestResponse update(
      @Parameter(description = "Identificador único de la finca", required = true)
      @PathVariable String id,
      @Valid @RequestBody UpdateFincaRestRequest request);

  @Operation(
      summary = "Eliminar finca",
      description = "Remueve permanentemente un predio del sistema según su ID.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "204",
        description = "Finca eliminada exitosamente."),
    @ApiResponse(
        responseCode = "404",
        description = "Finca no encontrada.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  void delete(
      @Parameter(description = "Identificador único de la finca a eliminar", required = true)
      @PathVariable String id);
}
