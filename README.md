# Users Management & Fincas — Spring Boot, Arquitectura Hexagonal y DDD

Microservicio empresarial construido con **Java 17** y **Spring Boot 3**, aplicando los principios de **Diseño Guiado por el Dominio (DDD)**, **Arquitectura Hexagonal (Ports & Adapters)** y **CQRS (Segregación de Comandos y Consultas)**.

El sistema gestiona de forma integral dos módulos articulados pero desacoplados:
1. **Módulo de Usuarios:** Gestión de identidad, autenticación JWT, autorización por roles y notificaciones.
2. **Módulo de Fincas (Ejercicio N.º 4):** Ciclo de vida completo (CRUDL) para predios agropecuarios con 12 atributos de negocio e integridad espacial.

---

## 🏛️ Arquitectura del Sistema

La solución se construye estrictamente **desde el dominio hacia afuera**, garantizando la inversión de dependencias:

```
src/main/java/com/jcaa/usersmanagement/
├── domain/                               # NÚCLEO: Lógica pura de negocio (Cero dependencias)
│   ├── model/                            # UserModel, FincaModel (Aggregate Roots inmutables)
│   ├── valueobject/                      # FincaId, FincaNombre, FincaSuperficie, FincaPersonal...
│   ├── event/                            # FincaCreatedDomainEvent, FincaUpdatedDomainEvent...
│   ├── exception/                        # FincaNotFoundException, InvalidFincaDataException...
│   └── enums/                            # UserRole, UserStatus
├── application/                          # CASOS DE USO Y CONTRATOS
│   ├── port/in/                          # CreateFincaUseCase, GetFincaByIdUseCase, GetAll...
│   ├── port/out/                         # SaveFincaPort, GetFincaByIdPort, DeleteFincaPort...
│   ├── service/                          # CreateFincaService, UpdateFincaService, GetAll...
│   ├── service/dto/command/              # CreateFincaCommand, UpdateFincaCommand, Delete...
│   ├── service/dto/query/                # GetFincaByIdQuery
│   └── service/mapper/                   # FincaApplicationMapper
└── infrastructure/                       # TECNOLOGÍA Y ADAPTADORES EXTERNOS
    ├── adapter/persistence/              # FincaRepositoryMySQL (JDBC DataSource PreparedStatement)
    │   ├── entity/                       # FincaEntity
    │   ├── dto/                          # FincaPersistenceDto
    │   └── mapper/                       # FincaPersistenceMapper
    ├── entrypoint/rest/                  # FincaRestController, FincaRestControllerDocs (Swagger)
    │   ├── dto/request/                  # CreateFincaRestRequest, UpdateFincaRestRequest
    │   ├── dto/response/                 # FincaRestResponse
    │   └── mapper/                       # FincaRestMapper
    └── security/                         # SecurityConfig, JWT Filter y autorización
```

---

## 🌾 Módulo de Fincas (Ejercicio N.º 4)

El módulo modela las propiedades agrícolas a través de doce (12) atributos de negocio protegidos por **Value Objects**:

| Atributo | Tipo | Descripción | Validación / Regla |
| :--- | :--- | :--- | :--- |
| `id` | UUID (String) | Identificador único del predio | No vacío, formato válido |
| `nombre` | String | Denominación legal de la finca | Entre 3 y 100 caracteres |
| `numHectareas` | Double | Extensión superficial en hectáreas | Estrictamente positivo (> 0) |
| `metrosCuadrados`| Double | Área en metros cuadrados | Autocalculado (`numHectareas * 10,000`) |
| `propietario` | String | Nombre del titular legal | No vacío, mín. 3 caracteres |
| `capataz` | String | Encargado o administrador operativo| No vacío, mín. 3 caracteres |
| `pais` | String | País de radicación geográfica | Por defecto 'Colombia' |
| `departamento` | String | División administrativa departamental | No vacío |
| `ciudad` | String | Municipio o jurisdicción | No vacío |
| `produceLeche` | Boolean | Línea de producción lechera | Booleano (true/false) |
| `produceCereales`| Boolean | Línea de cultivo cerealero | Booleano (true/false) |
| `produceFrutas` | Boolean | Línea de árboles frutales | Booleano (true/false) |
| `produceVerduras`| Boolean | Línea de huerta y hortalizas | Booleano (true/false) |

---

## 🚀 Endpoints REST (API Fincas)

| Método | Endpoint | Descripción | Acceso |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/fincas` | Registra una nueva finca | Público / PermitAll |
| `GET` | `/api/fincas` | Lista todas las fincas registradas | Público / PermitAll |
| `GET` | `/api/fincas/{id}` | Obtiene los detalles de una finca por ID | Público / PermitAll |
| `PUT` | `/api/fincas/{id}` | Actualiza los datos de una finca existente | Público / PermitAll |
| `DELETE`| `/api/fincas/{id}` | Elimina un predio del sistema | Público / PermitAll |

Documentación interactiva disponible en **Swagger UI**:  
👉 `http://localhost:8080/swagger-ui.html` (o `http://localhost:8080/swagger-ui/index.html`).

---

## ⚙️ Ejecución y Pruebas Locales

### 1. Requisitos Previos
- **Java 17** (Temurin JDK 17).
- **Maven 3.9+** (o el wrapper `./mvnw`).
- **MySQL / MariaDB** ejecutándose en el puerto 3306.

### 2. Base de Datos
Ejecutar los scripts de inicialización ubicados en `src/main/resources/`:
```bash
# Crear base de datos y esquema
mariadb -u Estudiante -pDeveloper < src/main/resources/schema.sql
mariadb -u Estudiante -pDeveloper < src/main/resources/schema_fincas.sql

# Cargar banco de datos con 7 fincas colombianas
mariadb -u Estudiante -pDeveloper < src/main/resources/fincas_seed.sql
```

### 3. Compilación y Ejecución
```bash
# Ejecutar suite de pruebas unitarias
./mvnw clean test

# Iniciar la aplicación Spring Boot
./mvnw spring-boot:run
```

La aplicación arrancará por defecto en el puerto `8080`.

---

## 🐳 Ejecución con Docker y Docker Compose (Paso 25)

El proyecto incluye contenedores listos para producción que levantan tanto el motor MySQL 8.4 como la API Spring Boot con las semillas de fincas y usuarios precargadas:

```bash
# Construir y levantar servicios en segundo plano
docker compose up -d

# Verificar estado y salud
docker compose ps
```

---

## 📮 Pruebas con Colección de Postman (Paso 24)

Se incluye una colección exportada en formato Postman v2.1 ubicada en `postman/users-management-spring-boot.postman_collection.json`.

Cubre:
* `GET /api/fincas` (Listar todas las fincas)
* `GET /api/fincas/{id}` (Consultar finca por identificador)
* `POST /api/fincas` (Crear finca con cálculo automático de $m^2$)
* `PUT /api/fincas/{id}` (Actualizar finca)
* `DELETE /api/fincas/{id}` (Eliminar finca)
* `POST /api/auth/login` (Autenticación JWT)
* `GET /v3/api-docs` y `/swagger-ui/index.html` (OpenAPI)

---

## ☁️ Base de Datos en la Nube y Despliegue en Render (Pasos 18 y 26)

El microservicio está preparado para conectarse a **Aiven Cloud MySQL** compartiendo el mismo esquema de datos con el módulo PHP:
* **Host:** `mysql-afcef2d-unicartagena-finca.c.aivencloud.com`
* **Puerto:** `20622`
* **Base de datos:** `defaultdb`

### Despliegue en Render
El archivo `render.yaml` contiene la especificación de infraestructura como código (IaC) para desplegar el contenedor Docker con Java 17 en Render asociando las variables de entorno de Aiven.

---

## 🚀 Integración Continua con GitHub Actions (Paso 27)

El workflow configurado en `.github/workflows/ci.yml` ejecuta en cada commit:
1. **Compilación y Pruebas Automatizadas:** Ejecución de `./mvnw clean test` validando los **198 tests unitarios** en runners de GitHub con Java 17 Temurin.
2. **Build de Contenedor Docker:** Construcción multi-etapa de la imagen Docker de producción.

