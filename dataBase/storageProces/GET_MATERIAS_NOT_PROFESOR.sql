DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `GET_MATERIAS_NOT_PROFESOR`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_MATERIAS_NOT_PROFESOR`(IN dni INT(11))
SELECT * FROM `materia` WHERE NOT`mat_prof_dni`=dni$$

DELIMITER ;