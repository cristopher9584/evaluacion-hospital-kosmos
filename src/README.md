# Sistema de Gestión de Citas Médicas

Proyecto Spring Boot MVC que permite registrar, consultar, modificar y eliminar citas médicas. Utiliza Thymeleaf en el frontend, JPA/Hibernate para persistencia y soporta bases de datos Oracle o H2.

## Características

- Registro de nuevas citas
- Consulta y filtrado de citas por fecha, doctor y consultorio
- Modificación y cancelación de citas
- UI limpia usando solo HTML y CSS
- Datos precargados automáticamente para facilitar pruebas

## Tecnologías

- Java 17+
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Oracle o H2 como base de datos
- Maven

## Cómo ejecutar

### 1. Clonar el proyecto

bash
git clone https://github.com/usuario/proyecto-citas-medicas.git
cd proyecto-citas-medicas


### 2. Configurar base de datos (se usa BD h2 por default para poder probar sin instalar una BD externa)

Nota: Al iniciar con H2, el sistema insertará datos de prueba automáticamente 
(por ejemplo, doctores y consultorios).


### 3. Ejecutar el proyecto

bash
./mvnw spring-boot:run


### 4. Abrir la aplicación

Accede desde tu navegador a:
http://localhost:8080/citas/consulta
