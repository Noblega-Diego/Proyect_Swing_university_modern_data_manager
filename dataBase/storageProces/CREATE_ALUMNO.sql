DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `CREATE_ALUMNO`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_ALUMNO`(IN nombre VARCHAR(45), IN apellido VARCHAR(45), IN fec_nacimiento VARCHAR(45), IN domicilio VARCHAR(45), IN telefono VARCHAR(45), IN dni INT(11),IN cod_inscripcion INT(11))
INSERT INTO `alumno` (`alu_dni`,`alu_nombre`,`alu_apellido`,`alu_fec_nac`,`alu_domicilio`,`alu_telefono`,`alu_insc_cod`) VALUES (dni,nombre,apellido,fec_nacimiento,domicilio,telefono,cod_inscripcion)$$

DELIMITER ;