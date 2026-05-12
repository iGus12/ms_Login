# Ms_Auth (Login) - Sanos y Salvos

Microservicio encargado de la seguridad, autenticación y autorización de usuarios para el ecosistema **Sanos y Salvos**. Este componente valida las credenciales y emite los tokens de acceso necesarios para interactuar de forma segura con el resto de la plataforma.

---

#  Funcionalidades Principales
*Autenticación de Usuarios:** Verificación segura de credenciales (correo y contraseña).
*Emisión de Tokens (JWT):** Generación de JSON Web Tokens para mantener la sesión del usuario en el Frontend de manera segura y sin estado (stateless).
*Protección de Rutas:** Actúa como la primera barrera de defensa antes de permitir el acceso a recursos protegidos de otros microservicios.
*Integración con CORS:** Configurado para aceptar peticiones de forma segura desde el Frontend en React (puerto 5173).

---

#  Stack Tecnológico
*Framework:* Spring Boot (Java)
*Seguridad:* Spring Security & JWT (JSON Web Tokens)
*Gestor de Dependencias:* Maven
*Arquitectura:* Diseño basado en Microservicios

---

#  Configuración y Ejecución local

# Requisitos previos
* Java Development Kit (JDK) 17 o superior.
* Maven instalado.
* El microservicio de Usuarios (`Ms_Usuarios` en el puerto 8083) debe estar configurado si la validación se cruza con esa base de datos.
