-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2020 a las 18:31:22
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_curso_estructura`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuela`
--

CREATE TABLE `escuela` (
                           `id` int(11) NOT NULL,
                           `nombre` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `escuela`
--

INSERT INTO `escuela` (`id`, `nombre`) VALUES
(1, 'AGRONOMÍA'),
(2, 'BIOLOGÍA'),
(3, 'EDUCACIÓN INICIAL'),
(4, 'EDUCACIÓN PRIMARIA'),
(5, 'EDUCACIÓN SECUNDARIA'),
(6, 'EDUCACIÓN FÍSICA'),
(7, 'ADMINISTRACIÓN DE EMPRESAS'),
(8, 'CONTABILIDAD Y AUDITORÍA'),
(9, 'ECONOMÍA'),
(10, 'ANTROPOLOGÍA SOCIAL'),
(11, 'ARQUEOLOGÍA E HISTORIA'),
(12, 'TRABAJO SOCIAL'),
(13, 'DERECHO'),
(14, 'ENFERMERÍA'),
(15, 'INGENIERÍA DE MINAS'),
(16, 'INGENIERÍA CIVIL'),
(17, 'INGENIERÍA QUIMÍCA'),
(18, 'OBSTETRICIA'),
(19, 'INGENIERÍA EN INDUSTRIAS ALIMENTARIAS'),
(20, 'FARMACIA Y BIOQUIMÍCA'),
(21, 'INGENIERÍA AGRÍCOLA'),
(22, 'INGENIERÍA AGROINDUSTRIAL'),
(23, 'CIENCIAS DE LA COMUNICACIÓN'),
(24, 'MEDICINA VETERINARIA'),
(25, 'INGENIERÍA INFORMÁTICA'),
(26, 'CIENCIAS FÍSICO-MATEMÁTICAS'),
(27, 'INGENIERÍA DE SISTEMAS'),
(28, 'INGENIERÍA AGROFORESTAL'),
(29, 'MEDICINA HUMANA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `escuela`
--
ALTER TABLE `escuela`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `escuela`
--
ALTER TABLE `escuela`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
