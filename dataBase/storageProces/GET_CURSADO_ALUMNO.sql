DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `GET_CURSADO_ALUMNO`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_CURSADO_AlUMNO`(IN dni_alumno INT(11))
SELECT * FROM `cursado` WHERE `cur_alu_dni`= dni_alumno$$

DELIMITER ;