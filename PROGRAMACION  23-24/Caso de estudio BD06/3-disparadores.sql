#Desactivar el modo seguro:
SET SQL_SAFE_UPDATES= 0;
#Activar el modo seguro:
SET SQL_SAFE_UPDATES= 1;

/*Nota:
		OLD      NEW
INSERT   X       Sí
UPDATE  Sí       Sí
DELETE  Sí        X
*/

# --- Código que se ejecuta al agregar una tupla en la tabla Oficinas, cuya finalidad es volcar esos datos sobre la tabla oficinasCopia ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_AGREGAR_OFICINA //
CREATE TRIGGER TRAS_AGREGAR_OFICINA 
AFTER INSERT ON oficinas FOR EACH ROW
BEGIN
	INSERT INTO oficinasCopia VALUES(NEW.identificador, NEW.nombre, NEW.domicilio, NEW.localidad, NEW.codigo_postal);
END;
// DELIMITER ;

SHOW CREATE TRIGGER TRAS_AGREGAR_OFICINA;
SELECT * FROM oficinas;
INSERT INTO oficinas VALUES (4, 'Noia', 'Venezuela, 15', 'Noia', '15200');
SELECT * FROM oficinasCopia;

# --- Código que se ejecuta al agregar una tupla en la tabla Familias, cuya finalidad es volcar esos datos obre la tabla familiasCopia ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_AGREGAR_FAMILIA //
CREATE TRIGGER TRAS_AGREGAR_FAMILIA AFTER INSERT ON familias FOR EACH ROW
BEGIN
	INSERT INTO familiasCopia VALUES(NEW.identificador, NEW.nombre, NEW.familia, NEW.oficina);
END;
// DELIMITER ;

SHOW CREATE TRIGGER TRAS_AGREGAR_FAMILIA;
SELECT * FROM familias;
DELETE FROM familias WHERE identificador = 5;
INSERT INTO familias VALUES (5, 'Noia-1', 5, 4);
SELECT * FROM familiasCopia;

# --- Código que se ejecuta al agregar una tupla en la tabla Agentes, cuya finalidad es volcar esos datos sobre la tabla agentesCopia ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_AGREGAR_AGENTE //
CREATE TRIGGER TRAS_AGREGAR_AGENTE AFTER INSERT ON agentes FOR EACH ROW
BEGIN
	INSERT INTO agentesCopia VALUES(NEW.identificador, NEW.nombre, NEW.usuario, NEW.clave, NEW.habilidad, NEW.categoria, NEW.familia, NEW.oficina);
END;
// DELIMITER ;

SHOW CREATE TRIGGER TRAS_AGREGAR_AGENTE;
SELECT * FROM agentes;
DELETE FROM agentes WHERE identificador = 5;
INSERT INTO agentes VALUES (5, 'Panchito Bonifacio Etxeberría', 'pbe', 'sup1', 9, 1, 5, 4);
SELECT * FROM agentesCopia;

# --- Código que se ejecuta al modificar datos de la tabla Oficinas, cuya finalidad es volcar esos datos sobre la tabla oficinasCopia. 
# En la tabla oficinasOLD se volcarán los datos que van a ser modificados ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_MODIFICAR_OFICINA //
CREATE TRIGGER TRAS_MODIFICAR_OFICINA AFTER UPDATE ON oficinas FOR EACH ROW
BEGIN
    DECLARE nombreOficina VARCHAR(40); #ESta variable la uso para comprobar si hay datos previos en oficinasOLD
    
	UPDATE oficinasCopia SET nombre = NEW.nombre, domicilio = NEW.domicilio, localidad = NEW.localidad, codigo_postal = NEW.codigo_postal 
		WHERE identificador = OLD.identificador;
	
	SET nombreOficina = (SELECT nombre FROM oficinasOLD WHERE identificador = NEW.identificador); #Guardo el nombre previo guardado en oficinasOLD
	IF nombreOficina IS NOT NULL THEN #Si hay algún dato previo en oficinasOLD lo actualizo
		UPDATE oficinasOLD SET identificador = OLD.identificador, nombre = OLD.nombre, domicilio = OLD.domicilio, localidad = OLD.localidad, codigo_postal = OLD.codigo_postal 
		WHERE identificador = OLD.identificador;
	ELSE #Si no hay datos previos en oficinasOLD lo inserto.
		INSERT INTO oficinasOLD VALUES(OLD.identificador, OLD.nombre, OLD.domicilio, OLD.localidad, OLD.codigo_postal);
	END IF;    
END;
// DELIMITER ;

DELETE FROM oficinasOLD;

SHOW CREATE TRIGGER TRAS_MODIFICAR_OFICINA;
SELECT * FROM oficinas;
UPDATE oficinas SET  nombre = 'Sanxenxo' WHERE identificador = 4;
SELECT * FROM oficinasCopia;
SELECT * FROM oficinasOLD;

# ---	Código que se ejecuta al modificar datos de la tabla Familias, cuya finalidad es volcar esos datos sobre la tabla familiasCopia. 
# En la tabla familiasOLD se volcarán los datos que van a ser modificados ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_MODIFICAR_FAMILIA //
CREATE TRIGGER TRAS_MODIFICAR_FAMILIA
	AFTER UPDATE ON familias	FOR EACH ROW
	BEGIN
		UPDATE familiasCopia
			SET nombre = NEW.nombre, familia = NEW.familia, oficina = NEW.oficina
				WHERE identificador = OLD.identificador;
		INSERT INTO familiasOLD	VALUES  ( OLD.identificador, OLD.nombre, OLD.familia, OLD.oficina );
	END;
// DELIMITER ;

# ---	Código que se ejecuta al modificar datos de la tabla Agentes, cuya finalidad es volcar esos datos sobre la tabla agentesCopia. 
# En la tabla agentesOLD se volcarán los datos que van a ser modificados ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_MODIFICAR_AGENTE //
CREATE TRIGGER TRAS_MODIFICAR_AGENTE
	AFTER UPDATE ON agentes		FOR EACH ROW
	BEGIN
		UPDATE agentesCopia
			SET nombre = NEW.nombre, usuario = NEW.usuario, clave = NEW.clave, habilidad = NEW.habilidad, categoria = NEW.categoria, familia = NEW.familia, oficina = NEW.oficina
				WHERE identificador = OLD.identificador;
		INSERT INTO agentesOLD	VALUES	( OLD.identificador, OLD.nombre, OLD.usuario, OLD.clave, OLD.habilidad, OLD.categoria, OLD.familia, OLD.oficina );
	END;
// DELIMITER ;

# --- Código que se ejecuta al borrar datos de la tabla Oficinas, cuya finalidad es volcar esos datos sobre la tabla agentesCopia. 
# En la tabla agentesOLD se volcarán los datos que van a ser eliminados
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_BORRAR_OFICINA //
CREATE TRIGGER TRAS_BORRAR_OFICINA
AFTER DELETE ON oficinas	FOR EACH ROW
BEGIN
	DELETE FROM oficinasCopia WHERE identificador = OLD.identificador;
	INSERT INTO oficinasOLD VALUES (OLD.identificador, OLD.nombre, OLD.domicilio, OLD.localidad, OLD.codigo_postal );
END;
// DELIMITER ;

# --- Código que se ejecuta al borrar datos de la tabla Familias, cuya finalidad es volcar esos datos sobre la tabla familiasCopia. 
# En la tabla familiasOLD se volcarán los datos que van a ser eliminados ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_BORRAR_FAMILIA //
CREATE TRIGGER TRAS_BORRAR_FAMILIA
AFTER DELETE ON familias FOR EACH ROW
BEGIN
	DELETE FROM familiasCopia WHERE identificador = OLD.identificador;
	INSERT INTO familiasOLD VALUES (OLD.identificador, OLD.nombre, OLD.familia, OLD.oficina);
END;
// DELIMITER ;

# --- Código que se ejecuta al borrar datos de la tabla Agentes, cuya finalidad es volcar esos datos sobre la tabla agentesCopia. 
# En la tabla agentesOLD se volcarán los datos que van a ser eliminados ---
DELIMITER //
DROP TRIGGER IF EXISTS TRAS_BORRAR_AGENTE //
CREATE TRIGGER TRAS_BORRAR_AGENTE
AFTER DELETE ON agentes	FOR EACH ROW
BEGIN
	DELETE FROM agentesCopia	WHERE identificador = OLD.identificador;
	INSERT INTO agentesOLD		VALUES	( OLD.identificador, OLD.nombre, OLD.usuario, OLD.clave, OLD.habilidad, OLD.categoria, OLD.familia, OLD.oficina );
END;
// DELIMITER ;

