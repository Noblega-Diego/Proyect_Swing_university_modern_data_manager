DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `UPDATE_CARRERA`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_CARRERA`(IN nombre VARCHAR(45), IN duracion VARCHAR(45), IN codigo_carrera INT(11))
UPDATE carrera SET `car_nombre`=nombre,`car_duracion`=duracion WHERE `car_cod`=codigo_carrera$$
DELIMITER ;