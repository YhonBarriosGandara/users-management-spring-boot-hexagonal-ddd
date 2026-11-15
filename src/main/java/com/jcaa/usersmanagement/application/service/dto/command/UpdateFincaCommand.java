package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateFincaCommand(
    @NotBlank(message = "id must not be blank") String id,
    @NotBlank(message = "nombre must not be blank")
        @Size(min = 3, max = 100, message = "nombre must have between 3 and 100 characters")
        String nombre,
    @Positive(message = "numHectareas must be strictly positive") double numHectareas,
    double metrosCuadrados,
    @NotBlank(message = "propietario must not be blank")
        @Size(min = 3, message = "propietario must have at least 3 characters")
        String propietario,
    @NotBlank(message = "capataz must not be blank")
        @Size(min = 3, message = "capataz must have at least 3 characters")
        String capataz,
    String pais,
    @NotBlank(message = "departamento must not be blank") String departamento,
    @NotBlank(message = "ciudad must not be blank") String ciudad,
    boolean produceLeche,
    boolean produceCereales,
    boolean produceFrutas,
    boolean produceVerduras) {}
