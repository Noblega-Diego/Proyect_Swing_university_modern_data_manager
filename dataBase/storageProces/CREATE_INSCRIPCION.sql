DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `CREATE_INSCRIPCION`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_INSCRIPCION`(IN nombre VARCHAR(45), IN fech_inscripcion VARCHAR(45), IN codigo_carrera INT(11), IN codigo_inscripcion INT(11))
INSERT INTO inscripcion (`insc_nombre`,`insc_fecha`,`insc_car_cod`,`insc_cod`) VALUES (nombre, fech_inscripcion, codigo_carrera, codigo_inscripcion)$$

DELIMITER ;