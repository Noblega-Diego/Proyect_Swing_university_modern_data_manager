DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `CREATE_PROFESOR`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_PROFESOR`(IN nombre VARCHAR(45), IN apellido VARCHAR(45), IN fec_nacimiento VARCHAR(45), IN domicilio VARCHAR(45), IN telefono VARCHAR(45), IN dni INT(11))
INSERT INTO profesor (`prof_dni`,`prof_nombre`,`prof_apellido`,`prof_fec_nac`,`prof_domicilio`,`prof_telefono`) VALUES (dni,nombre,apellido,fec_nacimiento,domicilio,telefono)$$

DELIMITER ;