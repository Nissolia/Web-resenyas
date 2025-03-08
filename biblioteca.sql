-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-03-2025 a las 14:19:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` bigint(20) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `sinopsis` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `titulo`, `autor`, `genero`, `sinopsis`) VALUES
(1, 'El camino de los reyes', 'Brandon Sanderson', 'Fantasía épica', 'Un mundo azotado por tormentas donde guerreros y reyes buscan redimir un destino roto.'),
(2, 'Palabras radiantes', 'Brandon Sanderson', 'Fantasía épica', 'Kaladin, Shallan y Dalinar desvelan los secretos de los Portadores del Vacío'),
(3, 'El aliento de los dioses', 'Brandon Sanderson', 'Fantasía', 'Una princesa y un mercenario se ven envueltos en una lucha divina por el poder.'),
(4, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 'Clásico', 'Un hidalgo enloquece por los libros de caballería y emprende aventuras con su escudero Sancho Panza.'),
(5, 'La sombra del viento', 'Carlos Ruiz Zafón', 'Misterio, Drama', 'Un joven descubre un libro maldito y desentraña un oscuro misterio en la Barcelona del siglo XX.'),
(6, 'El tiempo entre costuras', 'María Dueñas', 'Histórico, Drama', 'Una modista se convierte en espía durante la Guerra Civil Española y la Segunda Guerra Mundial.'),
(7, 'Los renglones torcidos de Dios', 'Torcuato Luca de Tena', 'Suspense, Psicológico', 'Una detective ingresa en un hospital psiquiátrico para investigar un crimen, pero su propia cordura es puesta en duda.'),
(8, 'Patria', 'Fernando Aramburu', 'Drama, Histórico', 'Dos familias amigas se separan debido a la violencia de ETA en el País Vasco.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resenas`
--

CREATE TABLE `resenas` (
  `id` int(11) NOT NULL,
  `estrellas` int(1) NOT NULL,
  `resena` varchar(500) NOT NULL,
  `fecha` date NOT NULL,
  `libro_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `resenas`
--

INSERT INTO `resenas` (`id`, `estrellas`, `resena`, `fecha`, `libro_id`) VALUES
(1, 5, 'Fascinante,una vez que empiezas a leerlo no puedes parar,creo que para mí es uno de esos libros que no podré olvidar nuca,una verdadera obra maestra', '2025-03-07', 2),
(2, 4, 'He disfrutado cada libro que he leído de Brandon Sanderson.  Seguiré leyendo todos sus libros que pueda.  Es excelente en la construcción de mundos. sientes que estás allí.', '2025-03-07', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(225) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  `contrasena` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `perfil`, `contrasena`) VALUES
(1, 'admin', 'ADMIN', '$2a$10$LLOJeVfHOSdTbFs8r1RtMOsMlJ7PVb9Mw6Z4y0aJgJst3ry18kIiW'),
(2, 'usuario1', 'USER', '$2a$10$S2Ryvf5ME9szRxj0v3uO0eS6ezktR2gWjfWeX1K9HTcd0mbBfyi/a'),
(3, 'usuario2', 'USER', '$2a$10$Z8yW7ru9bI5QjAqECslGEwRg5Dh.vAnrBLvhZG2l3I8lJlHlTYy8C');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `resenas`
--
ALTER TABLE `resenas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_resenas_libros` (`libro_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `resenas`
--
ALTER TABLE `resenas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `resenas`
--
ALTER TABLE `resenas`
  ADD CONSTRAINT `fk_resenas_libros` FOREIGN KEY (`libro_id`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
