CREATE DATABASE IF NOT EXISTS `sauap` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sauap`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asignado`
--

DROP TABLE IF EXISTS `asignado`;
CREATE TABLE `asignado` (
  `IdAsignado` int NOT NULL AUTO_INCREMENT,
  `IdProfesor` int NOT NULL,
  `IdUA` int NOT NULL,
  `Dia` enum('Lunes','Martes','Miercoles','Jueves','Viernes') DEFAULT NULL,
  `HrInicio` time DEFAULT NULL,
  `HrFin` time DEFAULT NULL,
  PRIMARY KEY (`IdAsignado`),
  UNIQUE KEY `uq_profesor_ua_dia_hora` (`IdProfesor`, `IdUA`, `Dia`, `HrInicio`),
  KEY `ID_ua_idx` (`IdUA`),
  CONSTRAINT `ID_p` FOREIGN KEY (`IdProfesor`) REFERENCES `profesor` (`Id`),
  CONSTRAINT `ID_ua` FOREIGN KEY (`IdUA`) REFERENCES `unidadaprendizaje` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `profesor`
--
DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NombreProfesor` varchar(50) NOT NULL DEFAULT '',
  `ApellidoP` varchar(50) NOT NULL DEFAULT '',
  `ApellidoM` varchar(50) NOT NULL DEFAULT '',
  `RFC` varchar(13) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `uq_rfc` (`RFC`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `unidadaprendizaje`
--

DROP TABLE IF EXISTS `unidadaprendizaje`;
CREATE TABLE `unidadaprendizaje` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NombreUA` varchar(50) NOT NULL DEFAULT '',
  `HrsClase` tinyint DEFAULT '0',
  `HrsTaller` tinyint DEFAULT '0',
  `HrsLab` tinyint DEFAULT '0',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `uq_nombre_ua` (`NombreUA`),
  CONSTRAINT `unidadaprendizaje_chk_1` CHECK ((`HrsClase` between 0 and 4)),
  CONSTRAINT `unidadaprendizaje_chk_2` CHECK ((`HrsTaller` between 0 and 4)),
  CONSTRAINT `unidadaprendizaje_chk_3` CHECK ((`HrsLab` between 0 and 4))
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NombreUsuario` varchar(50) NOT NULL,
  `ContrasenaUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `uq_nombre_usuario` (`NombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
