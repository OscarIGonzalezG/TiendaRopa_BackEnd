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

| Rol      | Permisos principales                               |
|----------|----------------------------------------------------|
| `ADMIN`    | Crear y listar usuarios                           |
| `VENDEDOR` | CRUD de productos (crear, editar, eliminar)       |
| `CLIENTE`  | Visualizar productos y realizar compras (en desarrollo) |

---

## 🧪 Funcionalidades principales

✅ Autenticación con JWT  
✅ Gestión de usuarios y roles  
✅ Encriptación de contraseñas con BCrypt  
✅ CRUD de productos  
✅ Protección de rutas con autorización por rol  
✅ Registro de pedidos por clientes  
⏳ Carrito (próximamente)

---

## 📌 URL base (Deploy)

```plaintext
https://tu-dominio-o-ip/api
```

> Reemplaza `https://tu-dominio-o-ip` con la URL o IP real de tu backend desplegado.


---

## 🔐 Autenticación con JWT

### Login

**POST** `/api/auth/login`

**Body (JSON):**

```json
{
  "email": "admin1@tienda.com",
  "password": "123456"
}
```

**Respuesta exitosa:**

```json
{
  "name": "Admin 1",
  "email": "admin1@tienda.com",
  "role": "ADMIN",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Uso del token

Para acceder a rutas protegidas, agrega el siguiente header en tus peticiones:

```
Authorization: Bearer {token}
```

---

## 👥 Gestión de usuarios (Solo ADMIN)

- **Listar usuarios:**  
  `GET /api/users`  

- **Crear usuario:**  
  `POST /api/users`  

  **Body ejemplo:**

  ```json
  {
    "name": "Vendedor 1",
    "email": "vendedor1@tienda.com",
    "password": "123456",
    "role": "VENDEDOR"
  }
  ```

---

## 📦 Gestión de productos (Solo VENDEDOR)

| Método | Ruta                          | Descripción               |
|--------|-------------------------------|--------------------------|
| GET    | `/api/vendedor/productos`      | Listar productos         |
| POST   | `/api/vendedor/productos`      | Crear nuevo producto     |
| PUT    | `/api/vendedor/productos`      | Actualizar producto      |
| GET    | `/api/vendedor/productos/{id}` | Obtener producto por id  |
| DELETE | `/api/vendedor/productos/{id}` | Eliminar producto        |

**Ejemplo de producto (JSON):**

```json
{
  "name": "Parka Invierno",
  "description": "Abrigo grueso, resistente al agua.",
  "price": 59990,
  "stock": 10
}
```

---

## 🛍️ Visualización de productos (Acceso público)

- **Listar productos:**  
  `GET /api/productos`

- **Detalle producto:**  
  `GET /api/productos/{id}`

---

## 📦 Gestión de pedidos (Solo CLIENTE)

- **Crear pedido:**  
  `POST /api/cliente/pedidos`

  **Body ejemplo:**

  ```json
  {
    "userId": 21,
    "items": [
      {
        "productId": 1,
        "quantity": 2
      }
    ]
  }
  ```

- **Listar pedidos de un usuario:**  
  `GET /api/cliente/pedidos/{userId}`

---

## 🛡️ Seguridad y permisos por roles

| Ruta                  | Rol requerido          | Acceso                  |
|-----------------------|-----------------------|-------------------------|
| `/api/auth/**`        | Público               | Login y registro        |
| `/api/users/**`       | ADMIN                 | Gestión usuarios        |
| `/api/vendedor/**`    | VENDEDOR              | CRUD productos          |
| `/api/productos/**`   | Público (solo lectura) | Ver productos           |
| `/api/cliente/**`     | CLIENTE y VENDEDOR    | Gestión pedidos         |

---

## 🧪 Prueba con Postman o Thunder Client

1. Haz login con `/api/auth/login` para obtener el token JWT.  
2. Copia el token recibido.  
3. En las peticiones a rutas protegidas, agrega el header:  

```
Authorization: Bearer {token}
```

4. Realiza las operaciones según el rol asignado.  

---

## ❗ Errores comunes

| Código | Descripción                      |
|--------|---------------------------------|
| 400    | Solicitud mal formada            |
| 401    | No autorizado (token inválido)  |
| 403    | Prohibido (sin permisos)        |
| 404    | Recurso no encontrado            |
| 500    | Error interno del servidor       |

---

## 📁 Estructura del proyecto (paquetes)

```
com.tienda.backend
├── controller    // Controladores REST
├── dto           // Clases DTO para login, registro, etc.
├── entity        // Entidades JPA (User, Product, Order...)
├── repository    // Repositorios de datos
├── security      // Configuración de seguridad y filtros JWT
├── service       // Lógica de negocio
└── Application.java  // Clase principal
```

---

## ⏳ Estado actual del proyecto

| Módulo                | Estado          |
|-----------------------|-----------------|
| Autenticación JWT     | ✅ Completo      |
| Gestión de usuarios   | ✅ Completo      |
| Gestión de productos  | ✅ Completo      |
| Visualización pública | ✅ Completo      |
| Pedidos               | ✅ Completo      |
| Carrito               | ⏳ Próximamente  |

---

## 📚 Licencia

Este proyecto está creado con fines educativos y como parte del portafolio profesional de **Oscar González**.
