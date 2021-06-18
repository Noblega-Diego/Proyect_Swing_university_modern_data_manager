DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `UPDATE_MATERIA`$$

CREATE PROCEDURE `UPDATE_MATERIA`(IN nombre VARCHAR(45),IN dni_profesor INT(11),IN codigo INT(11))
	UPDATE `materia` SET `mat_nombre`= nombre,`mat_prof_dni`= dni_profesor WHERE `mat_cod`= codigo$$
	
DELIMITER ;