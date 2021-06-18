DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `UPDATE_ALUMNO`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_ALUMNO`(IN nombre VARCHAR(45),IN apellido VARCHAR(45), IN fec_nacimiento DATE, IN domicilio VARCHAR(45), telefono VARCHAR(45), IN dni INT(11))
	UPDATE alumno SET `alu_nombre`=nombre, `alu_apellido`=apellido, `alu_fec_nac`=fec_nacimiento, `alu_domicilio`=domicilio, `alu_telefono`=telefono WHERE  `alu_dni`= dni$$

DELIMITER ;