# Sistema de Reserva de Clases de Gimnasio

# Descripción del proyecto 
- Un sistema web desarrollado utilizando Java y Spring Boot para poder gestionar usuarios, entrenadores y clases de un gimnasio. El sistema permite que se pueda registrar usuarios, iniciar sesión, administrar clientes, gestionar clases y hacer reversas.

# Tecnologías utilizadas
- Java 23
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Base de datos H2
- Thymeleaf
- Bootstrap 5
- Maven

# Funcionalidades del sistema para el primer avance
# Módulo 1: Registro e inicio de sesión

- Registro de usuarios
- Validación de campos
- Validación del formato del correo
- Validación de correo y cédula únicos
- Cifrado de la contraseña mediante BCrypt
- Inicio y cierre de sesión
- Control de acceso mediante roles (CLIENTE y ENTRENADOR)

# Módulo 2: Gestión de clientes y usuarios

- Consulta de usuarios registrado
- Edición de datos del usuario
- Desactivar cuentas
- Actualizar perfil del cliente
- Cambio de contraseña
- Validación de unicidad al editar

# Módulo 3: Gestión de clases

- Registro y edición de clases
- Asignación de entrenadores
- Registro de fecha, hora de inicio y capacidad
- Duración fija de una hora
- Cálculo automático de la hora de finalización
- Validación de traslapes de horarios de los entrenadores
- Visualización de cupos ocupados y disponibles
- Reserva de clases por parte de los clientes
- Validación de reservas duplicadas y capacidad máxima

# Requisitos para poder ejecutar el avance

- Java 23
- Visual Studio Code
- Maven Wrapper incluido en el proyecto

# Instrucción de ejecución

1. Descargar o clonar el repositorio de GitHub
2. Abrir la carpeta del proyecto en Visual Studio Code
3. Esperar a que cargue las dependecias de Maven
4. Ejecutar la aplicación de cualquiera de las sigueintes maneras:

1. Desde Visual Studio Code, se abre el archivo GimnasioApplication.java y se presiona donde dice Run

2. Desde la terminal de Visual Studio Code, se ubica en la carpeta principal del proyecto. Donde esta el archivo pom.xlm y se ejecuta el comando:
.\mvnw.cmd spring-boot:run

- Con cualquiera de las dos opciones, se debe abrir el navegador poniendo:

http://localhost:8080

# Base de datos con H2

- En el proyecto se utilizo H2 como base de datos, la configuración se encuentra en el archivo application.properties, la base de datos está configurada para poder conservar los datos mediante almacenamiento en archivo.

# Credenciales para probar el sistema

- Las credenciales que se dan a continuacion sirven para entrar al sistema:

- Entrar como entrenador:

**Correo: maria@prueba.com**
**Contraseña: maria123**

- Entrar como cliente:

**Correo: carlosmax@prueba.com**
**Contraseña: carlos1234**

# Repositorio en GitHub
https://github.com/naza0932/Gimnasio


