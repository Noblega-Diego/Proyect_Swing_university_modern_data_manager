/*
SQLyog Ultimate v9.02 
MySQL - 5.6.17 : Database - sgauni512
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sgauni512` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sgauni512`;

/*Table structure for table `alumno` */

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `alu_dni` int(11) NOT NULL,
  `alu_nombre` varchar(45) DEFAULT NULL,
  `alu_apellido` varchar(45) DEFAULT NULL,
  `alu_fec_nac` date DEFAULT NULL,
  `alu_domicilio` varchar(45) DEFAULT NULL,
  `alu_telefono` varchar(45) DEFAULT NULL,
  `alu_insc_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`alu_dni`),
  UNIQUE KEY `alu_dni_UNIQUE` (`alu_dni`),
  KEY `FK_inscripcion_idx` (`alu_insc_cod`),
  CONSTRAINT `FK_inscripcion` FOREIGN KEY (`alu_insc_cod`) REFERENCES `inscripcion` (`insc_cod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `alumno` */

insert  into `alumno`(`alu_dni`,`alu_nombre`,`alu_apellido`,`alu_fec_nac`,`alu_domicilio`,`alu_telefono`,`alu_insc_cod`) values (232443,'Diego','Mar','2005-08-05','Cordoba','+23717434',1623563),(363532,'Micael','Thomson','2021-06-04','Oclajoma','+1244553',94373),(5635361,'Jaule','Mirlen','2021-06-16','Mendoza','+216352',23634);

/*Table structure for table `carrera` */

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `car_cod` int(11) NOT NULL,
  `car_nombre` varchar(45) DEFAULT NULL,
  `car_duracion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`car_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `carrera` */

insert  into `carrera`(`car_cod`,`car_nombre`,`car_duracion`) values (546,'Licenciatura en Higiene y Seguridad','2 Años'),(2525,'Lic. Ciencia de la Computacion','5 Meses'),(35464,'Tec. Superior en Programación','2 Años');

/*Table structure for table `cursado` */

DROP TABLE IF EXISTS `cursado`;

CREATE TABLE `cursado` (
  `cur_alu_dni` int(11) NOT NULL,
  `cur_mat_cod` int(11) DEFAULT NULL,
  `cur_nota` double DEFAULT NULL,
  KEY `FK_materia_idx` (`cur_mat_cod`),
  CONSTRAINT `FK_materia` FOREIGN KEY (`cur_mat_cod`) REFERENCES `materia` (`mat_cod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cursado` */

insert  into `cursado`(`cur_alu_dni`,`cur_mat_cod`,`cur_nota`) values (9564857,135,8),(78465756,137,10),(5635361,134,0),(232443,137,0);

/*Table structure for table `inscripcion` */

DROP TABLE IF EXISTS `inscripcion`;

CREATE TABLE `inscripcion` (
  `insc_cod` int(11) NOT NULL,
  `insc_nombre` varchar(45) DEFAULT NULL,
  `insc_fecha` date DEFAULT NULL,
  `insc_car_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`insc_cod`),
  KEY `FK_carrera_idx` (`insc_car_cod`),
  CONSTRAINT `FK_carrera` FOREIGN KEY (`insc_car_cod`) REFERENCES `carrera` (`car_cod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `inscripcion` */

insert  into `inscripcion`(`insc_cod`,`insc_nombre`,`insc_fecha`,`insc_car_cod`) values (1123,'Promo234','2020-06-12',546),(4432,'Promo254','2020-06-12',2525),(23634,'Mirlen','2021-06-22',2525),(94373,'Thomson','2019-06-11',2525),(1623563,'Mar','2021-06-16',2525);

/*Table structure for table `materia` */

DROP TABLE IF EXISTS `materia`;

CREATE TABLE `materia` (
  `mat_cod` int(11) NOT NULL,
  `mat_nombre` varchar(45) DEFAULT NULL,
  `mat_prof_dni` int(11) DEFAULT NULL,
  PRIMARY KEY (`mat_cod`),
  KEY `FK_profesor_idx` (`mat_prof_dni`),
  CONSTRAINT `FK_profesor` FOREIGN KEY (`mat_prof_dni`) REFERENCES `profesor` (`prof_dni`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `materia` */

insert  into `materia`(`mat_cod`,`mat_nombre`,`mat_prof_dni`) values (134,'Matematica',47118232),(135,'Quimica',35112416),(137,'Fisica Nuclear',47238232),(12754,'Electroica 1',47238232);

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `prof_dni` int(11) NOT NULL,
  `prof_nombre` varchar(45) DEFAULT NULL,
  `prof_apellido` varchar(45) DEFAULT NULL,
  `prof_fec_nac` varchar(45) DEFAULT NULL,
  `prof_domicilio` varchar(45) DEFAULT NULL,
  `prof_telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prof_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profesor` */

insert  into `profesor`(`prof_dni`,`prof_nombre`,`prof_apellido`,`prof_fec_nac`,`prof_domicilio`,`prof_telefono`) values (35112416,'Maximino','De La Fuente','2020-06-17','Neuquen','+235854732'),(47118232,'Eloi','Pintos','2010-06-11','Neuquen','+235854732'),(47238232,'Marcos','Dionisio','2020-06-17','Cordoba','+235854732'),(47238432,'Daniel','Aroca','2020-06-17','Neuquen','+235854732');

/* Procedure structure for procedure `CLEAR_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `CLEAR_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CLEAR_ALUMNO`(dni INT(11))
DELETE FROM alumno WHERE `alu_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `CLEAR_CURSADO` */

/*!50003 DROP PROCEDURE IF EXISTS  `CLEAR_CURSADO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CLEAR_CURSADO`(codigo INT(11))
DELETE FROM `cursado` WHERE `cur_alu_dni`=codigo */$$
DELIMITER ;

/* Procedure structure for procedure `CLEAR_CURSADO_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `CLEAR_CURSADO_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CLEAR_CURSADO_ALUMNO`(IN dni INT(11))
DELETE FROM cursado WHERE `cur_alu_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `CLEAR_INSCRIPCION` */

/*!50003 DROP PROCEDURE IF EXISTS  `CLEAR_INSCRIPCION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CLEAR_INSCRIPCION`(IN codigo INT(11))
DELETE FROM `inscripcion` WHERE `insc_cod`=codigo */$$
DELIMITER ;

/* Procedure structure for procedure `CLEAR_MATERIA` */

/*!50003 DROP PROCEDURE IF EXISTS  `CLEAR_MATERIA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CLEAR_MATERIA`(IN codigo INTEGER(11))
DELETE FROM materia WHERE `mat_cod`=codigo */$$
DELIMITER ;

/* Procedure structure for procedure `CLEAR_PROFESOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `CLEAR_PROFESOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CLEAR_PROFESOR`(IN dni int(11))
delete FROM profesor where `prof_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `CREATE_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `CREATE_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_ALUMNO`(IN nombre VARCHAR(45), IN apellido VARCHAR(45), IN fec_nacimiento VARCHAR(45), IN domicilio VARCHAR(45), IN telefono VARCHAR(45), IN dni INT(11),In cod_inscripcion INT(11))
INSERT INTO `alumno` (`alu_dni`,`alu_nombre`,`alu_apellido`,`alu_fec_nac`,`alu_domicilio`,`alu_telefono`,`alu_insc_cod`) VALUES (dni,nombre,apellido,fec_nacimiento,domicilio,telefono,cod_inscripcion) */$$
DELIMITER ;

/* Procedure structure for procedure `CREATE_CARRERA` */

/*!50003 DROP PROCEDURE IF EXISTS  `CREATE_CARRERA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_CARRERA`(IN nombre VARCHAR(45), IN duracion VARCHAR(45),in codigo INteger(11))
INSERT INTO carrera (`car_nombre`, `car_duracion`,`car_cod`) VALUES (nombre,duracion,codigo) */$$
DELIMITER ;

/* Procedure structure for procedure `CREATE_CURSADO` */

/*!50003 DROP PROCEDURE IF EXISTS  `CREATE_CURSADO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_CURSADO`(IN dni_alumno INT(45), In cod_cursado integer(11), IN nota double)
INSERT INTO cursado (`cur_alu_dni`,`cur_mat_cod`,`cur_nota`) VALUES (dni_alumno, cod_cursado, nota) */$$
DELIMITER ;

/* Procedure structure for procedure `CREATE_INSCRIPCION` */

/*!50003 DROP PROCEDURE IF EXISTS  `CREATE_INSCRIPCION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_INSCRIPCION`(IN nombre VARCHAR(45), IN fech_inscripcion varchar(45), IN codigo_carrera INT(11), IN codigo_inscripcion INT(11))
INSERT INTO inscripcion (`insc_nombre`,`insc_fecha`,`insc_car_cod`,`insc_cod`) VALUES (nombre, fech_inscripcion, codigo_carrera, codigo_inscripcion) */$$
DELIMITER ;

/* Procedure structure for procedure `CREATE_MATERIA` */

/*!50003 DROP PROCEDURE IF EXISTS  `CREATE_MATERIA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_MATERIA`(IN nombre VARCHAR(45), In dni_profesor integer(11), IN codigo INTEGER(11))
INSERT INTO materia (`mat_nombre`,`mat_prof_dni`,`mat_cod`) VALUES (nombre, dni_profesor, codigo) */$$
DELIMITER ;

/* Procedure structure for procedure `CREATE_PROFESOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `CREATE_PROFESOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_PROFESOR`(IN nombre VARCHAR(45), IN apellido VARCHAR(45), IN fec_nacimiento VARCHAR(45), IN domicilio VARCHAR(45), IN telefono VARCHAR(45), IN dni INT(11))
insert into profesor (`prof_dni`,`prof_nombre`,`prof_apellido`,`prof_fec_nac`,`prof_domicilio`,`prof_telefono`) values (dni,nombre,apellido,fec_nacimiento,domicilio,telefono) */$$
DELIMITER ;

/* Procedure structure for procedure `ELIMINAR_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `ELIMINAR_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_ALUMNO`(dni INT(11))
DELETE FROM alumno WHERE `alu_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `GET_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_ALUMNO`(IN dni int(11))
SELECT * FROM alumno WHERE `alu_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `GET_ALUMNOS` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_ALUMNOS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_ALUMNOS`()
SELECT * FROM alumno */$$
DELIMITER ;

/* Procedure structure for procedure `GET_CARRERA` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_CARRERA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_CARRERA`(IN codigo INT(11))
SELECT * FROM `carrera` WHERE `car_cod`=codigo */$$
DELIMITER ;

/* Procedure structure for procedure `GET_CARRERAS` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_CARRERAS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_CARRERAS`()
SELECT * FROM `carrera` */$$
DELIMITER ;

/* Procedure structure for procedure `GET_CURSADO_AlUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_CURSADO_AlUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_CURSADO_AlUMNO`(IN dni_alumno INT(11))
SELECT * FROM `cursado` WHERE `cur_alu_dni`= dni_alumno */$$
DELIMITER ;

/* Procedure structure for procedure `GET_INSCRIPCION` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_INSCRIPCION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_INSCRIPCION`(IN codigo INT(11))
SELECT * FROM `inscripcion` WHERE `insc_cod`=codigo */$$
DELIMITER ;

/* Procedure structure for procedure `GET_MATERIA` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_MATERIA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_MATERIA`(IN codigo INT(11))
SELECT * FROM `materia` WHERE `mat_cod`=codigo */$$
DELIMITER ;

/* Procedure structure for procedure `GET_MATERIAS` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_MATERIAS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_MATERIAS`()
SELECT * FROM `materia` */$$
DELIMITER ;

/* Procedure structure for procedure `GET_MATERIAS_NOT_PROFESOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_MATERIAS_NOT_PROFESOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_MATERIAS_NOT_PROFESOR`(IN dni INT(11))
SELECT * FROM `materia` 
	WHERE 	(`mat_prof_dni` IS NULL OR NOT`mat_prof_dni`=dni) */$$
DELIMITER ;

/* Procedure structure for procedure `GET_MATERIAS_PROFESOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_MATERIAS_PROFESOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_MATERIAS_PROFESOR`(IN dni int(11))
SELECT * FROM `materia` WHERE `mat_prof_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `GET_PROFESOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_PROFESOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_PROFESOR`(IN dni INT(11))
SELECT * FROM `profesor` WHERE `prof_dni`=dni */$$
DELIMITER ;

/* Procedure structure for procedure `GET_PROFESORES` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_PROFESORES` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_PROFESORES`()
SELECT * FROM `profesor` */$$
DELIMITER ;

/* Procedure structure for procedure `INSERTAR_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `INSERTAR_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `INSERTAR_ALUMNO`(IN dni int(11), IN nombre varchar(45),IN apellido VARCHAR(45), IN fec_nac DATE, IN domicilio varchar(45), telefono VARCHAR(45))
INSERT INTO alumno (alu_dni, alu_nombre,alu_apellido,alu_fec_nac,alu_domicilio, alu_telefono) VALUES (dni,nombre,apellido,fec_nac,domicilio, telefono) */$$
DELIMITER ;

/* Procedure structure for procedure `UPDATE_ALUMNO` */

/*!50003 DROP PROCEDURE IF EXISTS  `UPDATE_ALUMNO` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_ALUMNO`(IN nombre VARCHAR(45),IN apellido VARCHAR(45), IN fec_nacimiento DATE, IN domicilio VARCHAR(45), telefono VARCHAR(45), IN dni INT(11))
UPDATE alumno SET `alu_nombre`=nombre, `alu_apellido`=apellido, `alu_fec_nac`=fec_nacimiento, `alu_domicilio`=domicilio, `alu_telefono`=telefono WHERE  `alu_dni`= dni */$$
DELIMITER ;

/* Procedure structure for procedure `UPDATE_CARRERA` */

/*!50003 DROP PROCEDURE IF EXISTS  `UPDATE_CARRERA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_CARRERA`(IN nombre varchar(45), IN duracion VARCHAR(45), IN codigo_carrera int(11))
UPDATE carrera SET `car_nombre`=nombre,`car_duracion`=duracion WHERE `car_cod`=codigo_carrera */$$
DELIMITER ;

/* Procedure structure for procedure `UPDATE_MATERIA` */

/*!50003 DROP PROCEDURE IF EXISTS  `UPDATE_MATERIA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_MATERIA`(IN nombre varchar(45),IN dni_profesor INT(11),IN codigo INT(11))
UPDATE `materia` SET `mat_nombre`= nombre,`mat_prof_dni`= dni_profesor where `mat_cod`= codigo */$$
DELIMITER ;

/* Procedure structure for procedure `UPDATE_PROFESOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `UPDATE_PROFESOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_PROFESOR`(IN nombre varchar(45), IN apellido VARCHAR(45), IN fec_nacimiento VARCHAR(45), IN domicilio VARCHAR(45), IN telefono VARCHAR(45), IN dni int(11))
update profesor set `prof_nombre`=nombre, `prof_apellido`=apellido, `prof_fec_nac`=fec_nacimiento, `prof_domicilio`=domicilio, `prof_telefono`=telefono where  `prof_dni`= dni */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
