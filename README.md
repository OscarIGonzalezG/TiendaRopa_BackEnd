# 🛒 Tienda Online - Backend API

Este proyecto es una API RESTful para una tienda de ropa online, desarrollada en **Java con Spring Boot**. Su propósito es simular una plataforma real donde se pueden registrar usuarios, gestionar productos, controlar el acceso según roles y preparar pedidos.

Ideal como parte de un portafolio técnico, ya que refleja buenas prácticas de seguridad, estructura escalable y separación de responsabilidades.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Security + JWT
- PostgreSQL
- Lombok
- Maven

---

## 📐 Roles definidos

| Rol      | Permisos principales               |
|----------|------------------------------------|
| `ADMIN`    | Crear y listar usuarios             |
| `VENDEDOR` | CRUD de productos (crear, editar, eliminar) |
| `CLIENTE`  | Visualizar productos y realizar compras (en desarrollo) |

---

## 🧪 Funcionalidades principales

✅ Autenticación con JWT  
✅ Gestión de usuarios y roles  
✅ Encriptación de contraseñas con BCrypt  
✅ CRUD de productos  
✅ Protección de rutas con autorización por rol  
⏳ Carrito y pedidos (en desarrollo)

---

## 🔐 Autenticación con JWT

**Login:**

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

> Usa el token en futuras peticiones como:
```
Authorization: Bearer {token}
```

---

## 👥 Gestión de usuarios (ADMIN)

- Listar usuarios: `GET /api/users`
- Crear usuario: `POST /api/users`

```json
{
  "name": "Vendedor 1",
  "email": "vendedor1@tienda.com",
  "password": "123456",
  "role": "VENDEDOR"
}
```

---

## 📦 Gestión de productos (VENDEDOR)

```http
GET    /api/vendedor/productos
POST   /api/vendedor/productos
PUT    /api/vendedor/productos
GET    /api/vendedor/productos/{id}
DELETE /api/vendedor/productos/{id}
```

**Ejemplo:**

```json
{
  "name": "Parka Invierno",
  "description": "Abrigo grueso, resistente al agua.",
  "price": 59990,
  "stock": 10
}
```

---

## 🛍️ Visualización de productos (CLIENTE - acceso público)

```http
GET /api/productos
GET /api/productos/{id}
```

---

## 🔒 Seguridad

Se utiliza Spring Security con JWT. Las rutas están protegidas de acuerdo al rol:

```java
/api/auth/**          -> público
/api/users/**         -> ADMIN
/api/vendedor/**      -> VENDEDOR
/api/productos/**     -> público (lectura)
```

El token se valida en cada petición y se interpreta para otorgar permisos por rol.

---

## 🧰 Prueba con Postman o Thunder Client

1. Login → recibe el token
2. Usa el token en el header:
```
Authorization: Bearer {token}
```
3. Accede a rutas protegidas según el rol

---

## ⚙️ Cómo ejecutar el proyecto

1. Clona este repositorio.
2. Configura tu PostgreSQL en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tienda_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

3. Ejecuta el proyecto:

```bash
./mvnw spring-boot:run
```

El backend estará disponible en:
```
http://localhost:8080
```

---

## 📌 Estado actual

| Módulo                 | Estado        |
|------------------------|---------------|
| Autenticación JWT      | ✅ Completo    |
| Gestión de usuarios    | ✅ Completo    |
| Gestión de productos   | ✅ Completo    |
| Visualización pública  | ✅ Completo    |
| Pedidos y carrito      | 🔄 En desarrollo |

---

## 📚 Licencia

Este proyecto está creado con fines educativos y como parte del portafolio profesional de **Oscar González**.
