DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `GET_INSCRIPCION`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_INSCRIPCION`(IN codigo INT(11))
SELECT * FROM `inscripcion` WHERE `insc_cod`=codigo$

DELIMITER ;