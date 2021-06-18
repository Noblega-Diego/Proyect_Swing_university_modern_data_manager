DROP PROCEDURE IF EXISTS `UPDATE_PROFESOR`;

CREATE PROCEDURE UPDATE_PROFESOR(IN nombre VARCHAR(45), IN apellido VARCHAR(45), IN fec_nacimiento VARCHAR(45), IN domicilio VARCHAR(45), IN telefono VARCHAR(45), IN dni INT(11))
	UPDATE profesor SET `prof_nombre`=nombre, `prof_apellido`=apellido, `prof_fec_nac`=fec_nacimiento, `prof_domicilio`=domicilio, `prof_telefono`=telefono WHERE  `prof_dni`= dni;