package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record FincaEntity(
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
