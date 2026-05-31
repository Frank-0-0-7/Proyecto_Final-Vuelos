# ✈️ Vuelos_Springboot — Proyecto Final

Sistema de gestión de reservas aéreas con arquitectura cliente-servidor. Backend REST en Spring Boot con persistencia en MySQL y frontend HTML estático de una sola página.

---

## 🧰 Stack Tecnológico

| Capa                     | Tecnología                                          |
| ------------------------ | --------------------------------------------------- |
| Lenguaje                 | Java 21                                             |
| Framework Backend        | Spring Boot                                         |
| Persistencia             | Spring Data JPA + Hibernate                         |
| Base de Datos            | MySQL (puerto `3307`)                               |
| Reducción de boilerplate | Lombok                                              |
| Frontend                 | HTML5 + CSS3 (Bootstrap 5) + Vanilla JS (Fetch API) |

---

## ⚙️ Configuración de la Base de Datos

```properties
spring.application.name=Vuelos_Springboot
spring.datasource.url=jdbc:mysql://localhost:3307/vuelos-spring?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

server.port=9000
```

> ⚠️ `create-drop` destruye y recrea el esquema en cada arranque.

---

## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/Vuelos_Springboot/
│   │   ├── entities/        # Modelos JPA
│   │   ├── repositories/    # Acceso a datos
│   │   ├── services/        # Lógica de negocio
│   │   ├── controllers/     # Endpoints REST
│   │   └── dto/             # Records de transferencia
│   └── resources/
│       ├── application.properties
│       ├── import.sql       # Datos iniciales
│       └── index.html       # Frontend SPA
```

---

## 📦 Módulos

### 1. Entities — Modelo de Dominio

Las entidades están mapeadas con JPA usando relaciones `@OneToMany`, `@ManyToOne` y `@ManyToMany`.

**Estrategia de herencia (`InheritanceType.JOINED`):**  
La jerarquía `Persona → Usuario / Piloto` usa tablas separadas unidas por clave foránea. Evita columnas nulas y normaliza correctamente el modelo relacional.

**Entidades principales:**

| Entidad   | Descripción                                              |
| --------- | -------------------------------------------------------- |
| `Persona` | Superclase: nombre, DNI, contacto                        |
| `Usuario` | Extiende `Persona`. Datos de cuenta                      |
| `Piloto`  | Extiende `Persona`. Habilitaciones                       |
| `Vuelo`   | Origen, destino, fecha, tarifa, impuesto, avión asignado |
| `Avion`   | Matrícula, aerolínea, capacidad                          |
| `Asiento` | Número, clase (`BUSINESS`, `TURISTA`, `ECONOMY`), avión  |
| `Reserva` | Vincula Usuario, Vuelo y Asiento                         |
| `Pago`    | Monto, fecha, estado, asociado a una Reserva             |
| `Tarjeta` | Datos enmascarados de medio de pago del Usuario          |

---

### 2. Repositories — Acceso a Datos

Arquitectura genérica: todos los repositorios extienden un `BaseRepository<T, ID>` que hereda de `JpaRepository`.

**Consulta nativa destacada en `AsientoRepository`:**

```java
@Query(value = """
    SELECT a.* FROM asiento a
    WHERE a.avion_id = :avionId
    AND a.id NOT IN (
        SELECT r.asiento_id FROM reserva r WHERE r.vuelo_id = :vueloId
    )
    """, nativeQuery = true)
List<Asiento> findAsientosLibresPorVuelo(
    @Param("avionId") Long avionId,
    @Param("vueloId") Long vueloId
);
```

> Calcula en tiempo real los asientos disponibles descartando los ya asociados a una reserva para ese vuelo específico.

---

### 3. Services — Lógica de Negocio

Todos los servicios extienden `BaseServiceImpl<T, ID>` que provee las operaciones CRUD genéricas.

**Core del negocio: `realizarReservaCompletaConIds` en `ReservaServiceImpl`**

```java
@Transactional(rollbackFor = Exception.class)
public Reserva realizarReservaCompletaConIds(ReservaRequestDTO dto) {
    // 1. Validar o crear Usuario
    // 2. Registrar o reutilizar Tarjeta
    // 3. Persistir Reserva (Usuario + Vuelo + Asiento)
    // 4. Registrar Pago asociado
}
```

- `@Transactional(rollbackFor = Exception.class)` garantiza **atomicidad total**: si cualquier paso falla, se hace rollback de toda la operación.
- Elimina estados inconsistentes (reserva sin pago, pago sin reserva, etc.).

---

### 4. Controllers — API REST

- Ruta base: `/api/v1/`
- `@CrossOrigin(origins = "*")` habilitado en todos los controllers para permitir el consumo desde el frontend local.
- `ReservaDTO` implementado como **Java Record** para transferir datos aplanados y optimizados a la grilla del panel web.

**Endpoints principales:**

| Método | Ruta                                        | Descripción                                    |
| ------ | ------------------------------------------- | ---------------------------------------------- |
| `GET`  | `/api/v1/vuelos`                            | Lista todos los vuelos disponibles             |
| `GET`  | `/api/v1/asientos/libres?avionId=&vueloId=` | Asientos libres para un vuelo                  |
| `POST` | `/api/v1/reservas/completa`                 | Crea reserva + pago en una sola transacción    |
| `GET`  | `/api/v1/reservas`                          | Lista reservas (proyectadas como `ReservaDTO`) |
| `GET`  | `/api/v1/usuarios`                          | Lista usuarios registrados                     |

---

### 5. Frontend — Panel Web (`index.html`)

SPA estática servida desde `src/main/resources/static/`. No requiere servidor adicional.

**Comportamiento reactivo al seleccionar un vuelo:**

1. Realiza un `fetch` al endpoint de asientos libres con el `avionId` y `vueloId` del vuelo seleccionado.
2. Actualiza el `<select>` de asientos dinámicamente, mostrando solo los disponibles.
3. Calcula automáticamente el **monto total** (`tarifa + impuesto`) y lo muestra en tiempo real sin recargar la página.

Todo el dinamismo usa **Fetch API nativa** (`async/await`), sin ningún framework de JavaScript.

---

## 🚀 Instrucciones de Despliegue

### Requisitos previos

- ☕ JDK 21 o superior
- 🗄️ MySQL corriendo en `localhost:3307`
- Base de datos `vuelos-spring` creada:

```sql
CREATE DATABASE vuelos-spring;
```

### Levantar el Backend

> El servidor inicia en `http://localhost:9000`.  
> Al arrancar, Hibernate ejecuta `import.sql` automáticamente, cargando: ciudades, aeropuertos, aerolíneas, aviones con 25 asientos cada uno, vuelos pre-programados y reservas históricas de ejemplo.

### Abrir el Frontend

No requiere servidor. Abrir directamente en el navegador:

```
src/main/resources/static/index.html
```

O, con el servidor corriendo, acceder desde:

```
http://localhost:9000/index.html
```

---

## 👤 Autor

**Franco Chirino** — Proyecto Final Programacion Orientada a Objetos  
🔗 [github.com/Frank-0-0-7/Proyecto_Final-Vuelos](https://github.com/Frank-0-0-7/Proyecto_Final-Vuelos)
