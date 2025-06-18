# 🏍️ Tienda Online - Backend API

Este proyecto es una API RESTful para una tienda de ropa, desarrollada con **Java Spring Boot**. Permite registrar usuarios, autenticarse con JWT, gestionar productos y controlar accesos por rol: `ADMIN`, `VENDEDOR`, `CLIENTE`.

---

## 🚀 Tecnologías usadas

- Java 17
- Spring Boot 3
- Spring Security
- JWT (Json Web Token)
- PostgreSQL
- Lombok
- Maven

---

## ⚙️ Requisitos previos

- Java 17+
- PostgreSQL 14+
- Maven

---

## 🧹 Estructura de roles

| Rol      | Permisos principales |
| -------- | -------------------- |
| ADMIN    | Gestión de usuarios  |
| VENDEDOR | Gestión de productos |
| CLIENTE  | (por implementar)    |

---

## 📦 Endpoints disponibles

### 🔐 Autenticación (JWT)

```http
POST /api/auth/login
```

**Body:**

```json
{
  "email": "admin1@tienda.com",
  "password": "123456"
}
```

**Respuesta:**

```json
{
  "name": "Admin 1",
  "email": "admin1@tienda.com",
  "role": "ADMIN",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

> El token debe usarse en futuras peticiones como: `Authorization: Bearer {token}`

---

### 👤 Gestión de usuarios (solo `ADMIN`)

```http
GET /api/users
POST /api/users
```

**Ejemplo de creación de usuario:**

```json
{
  "name": "Vendedor 1",
  "email": "vendedor1@tienda.com",
  "password": "123456",
  "role": "VENDEDOR"
}
```

---

### 📟 Gestión de productos (solo `VENDEDOR`)

```http
GET    /api/vendedor/productos
POST   /api/vendedor/productos
PUT    /api/vendedor/productos
GET    /api/vendedor/productos/{id}
DELETE /api/vendedor/productos/{id}
```

**Ejemplo de creación de producto:**

```json
{
  "name": "Parka Invierno",
  "description": "Abrigo grueso, resistente al agua.",
  "price": 59990,
  "stock": 10
}
```

---

## 🧰 Probar con Postman

1. Realiza `POST /api/auth/login` con credenciales válidas.
2. Copia el token de respuesta.
3. Usa el token en el header `Authorization: Bearer {token}` para las siguientes rutas.

---

## 🔒 Seguridad y roles

La seguridad se gestiona con Spring Security y JWT. Las rutas están protegidas por roles:

```java
"/api/auth/**"             -> acceso libre
"/api/users/**"            -> solo ADMIN
"/api/vendedor/**"         -> solo VENDEDOR
```

---

## 📌 Estado actual


✅ Registro y login de usuarios

✅ Encriptación de contraseñas (BCrypt)

✅ Generación de tokens JWT

✅ Protección de rutas por rol

✅ CRUD de productos para vendedores

⏳ Gestión de clientes (en desarrollo)

⏳ Carrito de compras y pagos (próximamente)


---

## 🛠️ Cómo correr el proyecto

1. Clona el repositorio.
2. Configura tu conexión PostgreSQL en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tienda_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

3. Ejecuta:

```bash
./mvnw spring-boot:run
```

El backend estará disponible en `http://localhost:8080`

---

## 📚 Licencia

Este proyecto está en desarrollo para fines educativos y portafolio personal.

---
