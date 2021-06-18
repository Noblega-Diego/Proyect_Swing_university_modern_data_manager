DELIMITER $$

USE `sgauni512`$$

DROP PROCEDURE IF EXISTS `CREATE_CURSADO`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CREATE_CURSADO`(IN dni_alumno INT(45), IN cod_cursado INTEGER(11), IN nota DOUBLE)
INSERT INTO cursado (`cur_alu_dni`,`cur_mat_cod`,`cur_nota`) VALUES (dni_alumno, cod_cursado, nota)$$

DELIMITER ;