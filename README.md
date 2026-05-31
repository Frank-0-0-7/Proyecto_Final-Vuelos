# ✈️ Proyecto Final — Sistema de Vuelos

Aplicación web backend desarrollada con **Spring Boot** para gestionar vuelos, pasajeros y reservas. Proyecto final universitario.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Descripción |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot 4 | Framework base del backend |
| Spring Data JPA | Acceso y manejo de la base de datos |
| Spring Data REST | Exposición automática de endpoints REST |
| Spring Web MVC | Controladores y lógica HTTP |
| MySQL | Base de datos relacional |
| Hibernate Envers | Auditoría y historial de cambios |
| Lombok | Reducción de código repetitivo (getters, setters, etc.) |
| Maven | Gestión de dependencias y build |

---

## 📁 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/Vuelos_Springboot/
│   │   ├── controller/    # Controladores REST
│   │   ├── model/         # Entidades (Vuelo, Pasajero, Reserva...)
│   │   ├── repository/    # Interfaces JPA
│   │   └── service/       # Lógica de negocio
│   └── resources/
│       └── application.properties  # Configuración de BD y servidor
```

---

## ⚙️ Módulos principales

### 🛫 Vuelos
Gestión de vuelos disponibles: origen, destino, fecha, capacidad y estado.

### 👤 Pasajeros
Registro de pasajeros con sus datos personales (nombre, DNI, contacto).

### 📋 Reservas
Vincula pasajeros con vuelos. Permite crear, consultar y cancelar reservas.

### 🕵️ Auditoría (Hibernate Envers)
Registra automáticamente quién y cuándo se modificó cada entidad. Útil para trazabilidad.

---

## 🚀 Cómo correr el proyecto

### Requisitos previos
- Java 21 instalado
- MySQL corriendo en local
- Maven instalado (o usar el wrapper incluido `./mvnw`)

### Pasos

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Frank-0-0-7/Proyecto_Final-Vuelos.git
   cd Proyecto_Final-Vuelos
   ```

2. Crear la base de datos en MySQL:
   ```sql
   CREATE DATABASE vuelos_db;
   ```

3. Configurar `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/vuelos_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Correr la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

5. La API estará disponible en: `http://localhost:8080`

---

## 📡 Endpoints principales (REST)

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/vuelos` | Lista todos los vuelos |
| POST | `/vuelos` | Crea un nuevo vuelo |
| GET | `/pasajeros` | Lista pasajeros |
| POST | `/reservas` | Crea una reserva |
| DELETE | `/reservas/{id}` | Cancela una reserva |

---

## 👨‍💻 Autor

Proyecto desarrollado como trabajo final de facultad.  
GitHub: [@Frank-0-0-7](https://github.com/Frank-0-0-7)
