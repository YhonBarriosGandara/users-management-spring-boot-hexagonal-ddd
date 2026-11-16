package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record FincaPersistenceDto(
    String id,
    String nombre,
    double numHectareas,
    double metrosCuadrados,
    String propietario,
    String capataz,
    String pais,
    String departamento,
    String ciudad,
    boolean produceLeche,
    boolean produceCereales,
    boolean produceFrutas,
    boolean produceVerduras,
    String createdAt,
    String updatedAt) {}
