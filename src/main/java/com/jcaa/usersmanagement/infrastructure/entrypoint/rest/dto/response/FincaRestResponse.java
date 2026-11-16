package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

public record FincaRestResponse(
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
    boolean produceVerduras) {}
