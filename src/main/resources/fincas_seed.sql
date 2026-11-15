-- =============================================
-- Banco de Datos Inicial: 7 Fincas Colombianas
-- Ejercicio N.º 4 - Gestión de Fincas
-- =============================================

USE crud_usuarios;

INSERT INTO fincas (
    id, nombre, num_hectareas, metros_cuadrados, propietario, capataz,
    pais, departamento, ciudad, produce_leche, produce_cereales, produce_frutas, produce_verduras
) VALUES 
(
    '10000000-0000-0000-0000-000000000001',
    'Hacienda San Pedro',
    120.50,
    1205000.00,
    'Guillermo Valencia',
    'Manuel Santos',
    'Colombia',
    'Antioquia',
    'Rionegro',
    TRUE,
    FALSE,
    TRUE,
    FALSE
),
(
    '10000000-0000-0000-0000-000000000002',
    'Finca Villa María',
    45.00,
    450000.00,
    'Beatriz Elena Morales',
    'Jorge Iván Restrepo',
    'Colombia',
    'Córdoba',
    'Montería',
    TRUE,
    TRUE,
    FALSE,
    FALSE
),
(
    '10000000-0000-0000-0000-000000000003',
    'Hacienda El Paraíso',
    85.75,
    857500.00,
    'Jorge Isaacs Ferrer',
    'Aníbal Cardona',
    'Colombia',
    'Valle del Cauca',
    'El Cerrito',
    FALSE,
    FALSE,
    TRUE,
    FALSE
),
(
    '10000000-0000-0000-0000-000000000004',
    'Finca La Esperanza',
    30.20,
    302000.00,
    'Clara Inés Gómez',
    'Pedro Pablo Alarcón',
    'Colombia',
    'Cundinamarca',
    'Fusagasugá',
    FALSE,
    FALSE,
    TRUE,
    TRUE
),
(
    '10000000-0000-0000-0000-000000000005',
    'Agropecuaria Los Samanes',
    210.00,
    2100000.00,
    'Álvaro Uribe Moreno',
    'Héctor Fabio Zuluaga',
    'Colombia',
    'Santander',
    'Socorro',
    TRUE,
    TRUE,
    FALSE,
    FALSE
),
(
    '10000000-0000-0000-0000-000000000006',
    'Finca El Recuerdo',
    18.50,
    185000.00,
    'María Teresa Benítez',
    'Camilo Echeverry',
    'Colombia',
    'Boyacá',
    'Villa de Leyva',
    FALSE,
    TRUE,
    FALSE,
    TRUE
),
(
    '10000000-0000-0000-0000-000000000007',
    'Hacienda Santa Bárbara',
    95.00,
    950000.00,
    'Fernando Botero Zea',
    'Gonzalo Arango',
    'Colombia',
    'Nariño',
    'Pasto',
    TRUE,
    FALSE,
    FALSE,
    TRUE
)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);
