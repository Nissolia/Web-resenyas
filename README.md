# Gestor de Biblioteca Online

## Descripción Detallada

El Gestor de Biblioteca Online es una aplicación web diseñada para la gestión de libros y reseñas por parte de usuarios registrados. Permite a los administradores gestionar usuarios, libros y reseñas, mientras que los usuarios pueden explorar libros, filtrar por género, escribir reseñas y calificarlos.

## Funcionalidades Principales

### Sistema de Usuarios
- **Registro e inicio de sesión** con autenticación.
- **Perfiles de usuario**: Administrador y Usuario.
- El **administrador** puede agregar, eliminar y gestionar usuarios.

### Gestión de Libros
- **Listado de libros** disponibles en la biblioteca.
- **Búsqueda y filtrado** de libros por género.
- **Visualización de detalles** de cada libro.

### Sistema de Reseñas
- Los **usuarios pueden agregar reseñas** y calificar libros de 0 a 5 estrellas.
- Se muestra la **fecha de la reseña** y el **usuario** que la escribió.

### Filtrado Avanzado de Libros
- **Filtrado por género** usando la base de datos, con limpieza de datos (split-trim y eliminación de duplicados).
- **Búsqueda por título y autor**.

### Validaciones de Formularios
- **Validación en el front-end** con JavaScript y en el back-end para evitar datos incorrectos o inseguros.

### Mejoras Adicionales
- **Paginación** en listados.
- **Exportación de listas de libros y reseñas** a formato PDF o CSV.

## Tecnologías Utilizadas

### Frontend
- **HTML, CSS, JavaScript** para la estructura y diseño de la interfaz.
- **Bootstrap** para un diseño responsivo.

### Backend
- **PHP con Laravel** para la lógica del servidor.
- **MySQL** para la gestión de la base de datos.

## Base de Datos

### Tablas Principales

- **Usuarios**: `id`, `nombre`, `perfil`
- **Libros**: `id`, `título`, `autor`, `género` (separado por ","), `sinopsis`
- **Reseñas**: `id`, `id_libro`, `id_usuario`, `estrellas` (0-5), `reseña`, `fecha`
