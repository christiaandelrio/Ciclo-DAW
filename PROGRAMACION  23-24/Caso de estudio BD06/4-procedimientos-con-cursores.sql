# ---	Creamos un cursor cuya finalidad será restaurar la tabla oficinas tras un borrado a partir de las tuplas que hay en la tabla oficinasOLD ---
DELIMITER //
DROP PROCEDURE IF EXISTS restaurarOficinas //
CREATE PROCEDURE restaurarOficinas()
BEGIN
	DECLARE	FIN	INT	DEFAULT	FALSE;
    
	DECLARE var_id INT;
	DECLARE	var_nom VARCHAR(40);
	DECLARE	var_dom VARCHAR(40);
	DECLARE	var_loc VARCHAR(20);
	DECLARE	var_cp VARCHAR(5);
    
    #Declaramos el cursor a partir de los datos existentes en oficinasOLD
    DECLARE cursorOficinas CURSOR FOR SELECT * FROM oficinasOLD;
    #Declaramos el manejo de error para finalizar el loop cuando no queden datos en el cursor
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FIN = TRUE;
    
    #Abrimos el cursor
    OPEN cursorOficinas;
    
    #Recorremos el cursor
    leerOficinas: LOOP
		FETCH cursorOficinas INTO var_id, var_nom, var_dom, var_loc, var_cp; #Guardamos los datos en las variables auxiliares.
        IF FIN THEN #Si FIN es true por que no quedan más datos que leer en el cursor
			LEAVE leerOficinas; #Dejamos el loop
		END IF;
        
        IF EXISTS (SELECT * FROM oficinas WHERE identificador = var_id) THEN #Si ya existe el dato en la tabla oficinas lo actualizo
			UPDATE oficinas SET nombre = var_nom, domicilio = var_dom, localidad = var_loc, codigo_postal = var_cp
				WHERE identificador = var_id;
        ELSE #Sino existe el dato previamente en la tabla oficinas lo inserto.
			INSERT INTO oficinas VALUES (var_id, var_nom, var_dom, var_loc, var_cp);
        END IF;
	END LOOP;
    
    #Cerramos el cursor
    CLOSE cursorOficinas;
END;
// DELIMITER ;

SELECT * FROM oficinas;
SELECT * FROM oficinasOLD;

CALL restaurarOficinas();

# ---	Creamos un cursor cuya finalidad será restaurar la tabla familias tras un borrado a partir de las tuplas que hay en la tabla familiasOLD ---
DELIMITER //
DROP PROCEDURE IF EXISTS restaurarFamilias //
CREATE PROCEDURE restaurarFamilias()
BEGIN
	DECLARE	FIN	INT	DEFAULT	FALSE;
    
	DECLARE var_id INT;
	DECLARE	var_nom VARCHAR(40);
	DECLARE	var_fam INT;
	DECLARE	var_ofi INT;
    
    DECLARE cursorFamilias CURSOR FOR SELECT * FROM familiasOLD;
    DECLARE	CONTINUE HANDLER FOR NOT FOUND SET FIN = true;
    
    OPEN cursorFamilias;
    
    leerFamilias: LOOP
		FETCH cursorFamilias INTO var_id, var_nom, var_fam, var_ofi;
        
        IF FIN THEN 
			LEAVE leerFamilias;
		END IF;
        
		IF EXISTS (	SELECT * FROM familias WHERE identificador = var_id )	THEN
			UPDATE familias SET nombre = var_nom, familia = var_fam, oficina = var_ofi WHERE identificador = var_id;
		ELSE
			INSERT familias VALUES ( var_id, var_nom, var_fam, var_ofi );
		END IF;
    END LOOP;
    
    CLOSE cursorFamilias;
END;
// DELIMITER ;

SELECT * FROM familias;
SELECT * FROM familiasOLD;

CALL restaurarFamilias();

# ---	Creamos un cursor cuya finalidad será restaurar la tabla agentes tras un borrado a partir de las tuplas que hay en la tabla agentesOLD ---
DELIMITER //
DROP PROCEDURE IF EXISTS restaurarAgentes //
CREATE PROCEDURE restaurarAgentes()
BEGIN
	DECLARE FIN INT DEFAULT FALSE;
    
	DECLARE var_id INT;
	DECLARE	var_nom VARCHAR( 40 );
	DECLARE	var_usu VARCHAR( 20 );
	DECLARE var_cla VARCHAR( 20 );
	DECLARE var_hab INT;
	DECLARE	var_cat INT;
	DECLARE	var_fam INT;
	DECLARE	var_ofi INT;
    
    DECLARE cursorAgente CURSOR FOR SELECT * FROM agentesOLD;
    
	DECLARE	CONTINUE HANDLER FOR NOT FOUND SET FIN = true;
    
    OPEN cursorAgente;
    
    leerAgentes: LOOP
		FETCH cursorAgente INTO var_id, var_nom, var_usu, var_cla, var_hab, var_cat, var_fam, var_ofi;
        
        IF FIN THEN
			LEAVE leerAgentes;
        END IF;
        
        IF(SELECT * FROM agentes WHERE identificador = var_id) THEN
			UPDATE agentes SET nombre = var_nom, usuario = var_usu, clave = var_cla, habilidad = var_hab, categoria = var_cat, familia = var_fam, oficina = var_ofi
				WHERE identificador = id;
        ELSE
			INSERT INTO agentes VALUES(var_id, var_nom, var_usu, var_cla, var_hab, var_cat, var_fam, var_ofi);
		END IF;
    END LOOP;  
    
    CLOSE cursorAgente;   
END;
// DELIMITER ;

SELECT * FROM agentes;
SELECT * FROM agentesOLD;

CALL restaurarAgentes();

# ---	Procedimiento para restaurar todos los datos de las tablas a partir de sus procedimientos ---
DELIMITER //
DROP PROCEDURE IF EXISTS restaurarDatos //
CREATE PROCEDURE restaurarDatos()
	BEGIN
		CALL restaurarOficinas();
		CALL restaurarFamilias();
		CALL restaurarAgentes();
	END;
// DELIMITER ;

CALL restaurarDatos();

# --- Creamos un procedimiento cuya finalidad es incrementar la categoría de los agentes en una unidad. ---
DELIMITER //
DROP PROCEDURE IF EXISTS aumentarCategoriaAgentes //
CREATE PROCEDURE aumentarCategoriaAgentes()
BEGIN
	DECLARE FIN INT DEFAULT FALSE;
    
    DECLARE var_id INT;
    DECLARE var_cat INT;
    
    DECLARE cursorAgentes CURSOR FOR SELECT identificador, categoria FROM agentes;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET FIN = TRUE;
    
    OPEN cursorAgentes;
    
    leerAgentes: LOOP
		FETCH cursorAgentes INTO var_id, var_cat;
        
		IF FIN THEN
			LEAVE leerAgentes;
		END IF;
        
        UPDATE agentes SET categoria = var_cat + 1 WHERE identificador = var_id;
    END LOOP;
    
    CLOSE cursorAgentes;
END;    
// DELIMITER ;

SELECT * FROM agentes;
CALL aumentarCategoriaAgentes();
SELECT * FROM agentes;


# --- Creamos un procedimiento cuya finalidad es decrementar la categoría de los agentes en una unidad. ---
DELIMITER //
DROP PROCEDURE IF EXISTS disminuirCategoriaAgentes //
CREATE PROCEDURE disminuirCategoriaAgentes()
BEGIN
	DECLARE FIN INT DEFAULT FALSE;
	DECLARE var_id INT;
	DECLARE var_cat INT;

	DECLARE cursorAgentes CURSOR FOR SELECT identificador, categoria FROM agentes;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET FIN = TRUE;

	OPEN cursorAgentes;
	leerAgentes: LOOP
		FETCH cursorAgentes INTO var_id, var_cat;
		IF FIN THEN
			LEAVE leerAgentes;
		END IF;
	
		UPDATE agentes SET categoria = categoria - 1 WHERE identificador = var_id;
	END LOOP;
	CLOSE cursorAgentes;
END;
// DELIMITER ;

SELECT * FROM agentes;
CALL disminuirCategoriaAgentes();
SELECT * FROM agentes;