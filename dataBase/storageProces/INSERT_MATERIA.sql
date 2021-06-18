DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `CREATE_MATERIA`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_MATERIA`(IN nombre VARCHAR(45), IN dni_profesor INTEGER(11), IN codigo INTEGER(11))
INSERT INTO materia (`mat_nombre`,`mat_prof_dni`,`mat_cod`) VALUES (nombre, dni_profesor, codigo)$$

DELIMITER ;