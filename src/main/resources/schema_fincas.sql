-- =============================================
-- Script DDL: Tabla Fincas (Ejercicio N.º 4)
-- Arquitectura Hexagonal, DDD y CQRS en Spring Boot
-- =============================================

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS fincas (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    num_hectareas DECIMAL(12, 2) NOT NULL,
    metros_cuadrados DECIMAL(14, 2) NOT NULL,
    propietario VARCHAR(100) NOT NULL,
    capataz VARCHAR(100) NOT NULL,
    pais VARCHAR(60) NOT NULL DEFAULT 'Colombia',
    departamento VARCHAR(60) NOT NULL,
    ciudad VARCHAR(60) NOT NULL,
    produce_leche BOOLEAN NOT NULL DEFAULT FALSE,
    produce_cereales BOOLEAN NOT NULL DEFAULT FALSE,
    produce_frutas BOOLEAN NOT NULL DEFAULT FALSE,
    produce_verduras BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_fincas_ciudad (ciudad),
    INDEX idx_fincas_departamento (departamento)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
