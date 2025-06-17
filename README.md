## 🛠️ Configuración de la base de datos

El proyecto está conectado a PostgreSQL. Asegúrate de tener la base de datos creada con el siguiente nombre:

- Nombre de la BD: `tienda_ropa`
- Usuario: `postgres`
- Contraseña: **(tu contraseña)**

Archivo de configuración:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tienda_ropa
spring.datasource.username=postgres
spring.datasource.password=TU_CONTRASEÑA
```

## 📘 Entidad: User

Representa a los usuarios del sistema (admin, vendedor o cliente).

Campos:
- `id`: clave primaria.
- `name`: nombre completo.
- `email`: correo único para login.
- `password`: se encriptará.
- `role`: ADMIN, VENDEDOR o CLIENTE.
- `isActive`: cuenta activa o no.
- `createdAt`: fecha de creación.

## 📂 Repositorio: UserRepository

Repositorio para acceder a los datos de usuarios.

Extiende de `JpaRepository<User, Long>`, por lo que hereda métodos para:
- Listar todos los usuarios.
- Buscar por ID.
- Guardar y eliminar usuarios.
- Buscar por email: `findByEmail(String email)`
- Verificar si un email existe: `existsByEmail(String email)`


## 🧪 Carga inicial de datos

El proyecto crea 4 usuarios al iniciar por primera vez, usando la clase `DataLoader`.

```java
User.builder()
    .name("Admin Principal")
    .email("admin1@tienda.com")
    .password("123456") // En el futuro será cifrada
    .role("ADMIN")
    .build();
```

## 🌐 Endpoint: Listado de usuarios

**GET** `/api/users`

Retorna todos los usuarios registrados en la base de datos.

#### Ejemplo de respuesta:
```json
[
  {
    "id": 1,
    "name": "Admin Principal",
    "email": "admin1@tienda.com",
    "role": "ADMIN",
    "isActive": true,
    "createdAt": "2025-06-16T01:05:00"
  },
  ...
]
```

## 🔍 Endpoint: Buscar usuario por ID

**GET** `/api/users/{id}`

Busca un usuario específico por su identificador único.

### Parámetros:
- `id`: número del usuario que deseas buscar

### Ejemplo:
GET `/api/users/2`

### Respuestas posibles:

- `200 OK` + Usuario encontrado
- `404 Not Found` si el usuario no existe


## 📝 Endpoint: Crear usuario

**POST** `/api/users`

Crea un nuevo usuario en la base de datos.

### Campos requeridos:
- `name`: Nombre del usuario (obligatorio)
- `email`: Correo único y válido (obligatorio)
- `password`: Mínimo 6 caracteres (obligatorio)
- `role`: ADMIN, VENDEDOR o CLIENTE (obligatorio)

### Ejemplo de petición:
```json
{
  "name": "Juan Pérez",
  "email": "juan@tienda.com",
  "password": "123456",
  "role": "CLIENTE"
}
```


🔐 Seguridad:
- Las contraseñas se almacenan de forma segura usando BCrypt.
- Esto se configura en `SecurityConfig.java` y se usa al crear el usuario.

Código relevante:
```java
passwordEncoder.encode(request.getPassword());

```


🔍 Validación de Email Duplicado

Antes de crear un nuevo usuario, el backend verifica que el email no esté ya registrado.

### Ejemplo:
POST `/api/users`

```json
{
  "name": "Pedro",
  "email": "admin1@tienda.com",
  "password": "123456",
  "role": "CLIENTE"
}
```
## 🔐 Endpoint de Login

**POST** `/api/auth/login`

Verifica que el email y contraseña sean válidos para iniciar sesión.

### Campos requeridos:

- `email`: Email registrado
- `password`: Contraseña del usuario

### Respuestas posibles:

- `200 OK`: Login exitoso
- `400 Bad Request`: Usuario no encontrado o contraseña inválida


📤 LoginResponse

Después del login, el backend devuelve un objeto limpio con datos básicos del usuario:

- `id`: ID del usuario
- `name`: Nombre completo
- `email`: Correo electrónico
- `role`: Rol (ADMIN, VENDEDOR, CLIENTE)

No se expone el campo `password` por seguridad.


📤 Login con JWT

**POST /api/auth/login**

Devuelve los datos del usuario + token JWT válido por 4 horas.

Campos:
- name
- email
- role
- token (usar en headers como Authorization: Bearer {token})

🔐 Autenticación con JWT

- El cliente obtiene un JWT con `/api/auth/login`.
- Debe enviar ese token en cada request:
  Header → Authorization: Bearer {token}
- Las rutas se protegen por rol usando `.hasAuthority("ROL")`.

Ejemplo:
- Solo ADMIN puede acceder a `/api/users`


🔒 Rutas protegidas por rol

- /api/auth/** → acceso libre
- /api/users/** → solo ADMIN
- /api/vendedor/** → solo VENDEDOR

Cada usuario recibe un token JWT con su `role`, el cual debe enviarse en cada petición.