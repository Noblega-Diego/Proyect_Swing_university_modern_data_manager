DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `GET_ALUMNOS`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_ALUMNOS`()
SELECT * FROM alumno$$

DELIMITER ;