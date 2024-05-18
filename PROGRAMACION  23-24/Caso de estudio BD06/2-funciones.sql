#---------- Función que va a devolver el número de miembros de la familia cuyo nombre se pase como parámetro ----------
DELIMITER //
DROP FUNCTION IF EXISTS contarMiembrosFamilia //
CREATE FUNCTION contarMiembrosFamilia (nombreFamilia VARCHAR(40)) RETURNS INT DETERMINISTIC
BEGIN
	DECLARE numeroFamilias INT;
	SELECT Count(*) INTO numeroFamilias FROM familias WHERE nombre= nombreFamilia;
    RETURN numeroFamilias;
END;
// DELIMITER ;

SHOW CREATE FUNCTION contarMiembrosFamilia;
SELECT * FROM familias;
SELECT contarMiembrosFamilia('Madrid-1') AS 'Número de familias';

#---------- Función que va a devolver el nombre de la familia cuyo identificador se pasa como parámetro  ----------
DELIMITER //
DROP FUNCTION IF EXISTS obtenerNombreFamiliaPorId //
CREATE FUNCTION obtenerNombreFamiliaPorId(id INT) RETURNS VARCHAR(40) DETERMINISTIC
BEGIN
	DECLARE nombreFamilia VARCHAR(40);
	SELECT nombre INTO nombreFamilia FROM familias WHERE identificador = id;
    IF nombreFamilia IS NULL THEN 
		RETURN 'El identificador facilitado no es válido';
    ELSE 
		RETURN nombreFamilia;
    END IF;
END;
// DELIMITER ;

SHOW CREATE FUNCTION obtenerNombreFamiliaPorId;
SELECT * FROM familias;
SELECT obtenerNombreFamiliaPorId(11) AS 'Nombre de familia';
SELECT obtenerNombreFamiliaPorId(100) AS 'Nombre de familia';

