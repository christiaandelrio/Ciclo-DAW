DELETE FROM oficinas;
DELETE FROM familias;
DELETE FROM agentes;

#---------- Procedimiento para añadir los datos de una nueva Oficina ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarOficina //
CREATE PROCEDURE agregarOficina(id int, Nom varchar(40), Dom varchar(40), Loc varchar(20), CP varchar(5))
BEGIN
	INSERT INTO oficinas (identificador, nombre, domicilio, localidad, codigo_postal) VALUES (id, Nom, Dom, Loc, CP);
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarOficina;

#---------- Procedimiento para agregar datos haciendo uso del procedimiento anterior ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarOficinas //
CREATE PROCEDURE agregarOficinas()
BEGIN
	CALL agregarOficina(1, 'Madrid', 'Gran vía, 37', 'Madrid', '28000');
    CALL agregarOficina(2, 'Granada', 'Camino Ronda, 50', 'Granada', '36000');
    CALL agregarOficina(3, 'Jaén', 'Gran Eje, 80', 'Jaén', '27000');
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarOficinas;

#----------	Procedimiento para añadir los datos de una nueva Familia ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarFamilia //
CREATE PROCEDURE agregarFamilia (id int, Nom varchar(40), Fam int, Ofi int)
BEGIN
	INSERT INTO familias (identificador, nombre, familia, oficina) VALUES (id, Nom, Fam, Ofi);
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarFamilia;

#---------- Procedimiento para agregar datos haciendo uso del procedimiento anterior ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarFamilias //
CREATE PROCEDURE agregarFamilias()
BEGIN
	CALL agregarFamilia(11, 'Madrid-1', NULL, 1);
	CALL agregarFamilia(111, 'Madrid-1.1', 11, NULL);
	CALL agregarFamilia(112, 'Madrid-1.2', 11, NULL);
	CALL agregarFamilia(1121, 'Madrid-1.2.1', 112, NULL);
	CALL agregarFamilia(1122, 'Madrid-1.2.2', 112, NULL);
	CALL agregarFamilia(1123, 'Madrid-1.2.3', 112, NULL);
	CALL agregarFamilia(21, 'Granada-1', NULL, 2);
	CALL agregarFamilia(211, 'Granada-1.1', 21, NULL);
	CALL agregarFamilia(212, 'Granada-1.2', 21, NULL);
	CALL agregarFamilia(213, 'Granada-1.3', 21, NULL);
	CALL agregarFamilia(31, 'Jaén-1', NULL, 3);
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarFamilias;

#---------- Procedimiento para añadir los datos de un nuevo Agente ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarAgente //
CREATE PROCEDURE agregarAgente (id int, Nom varchar(60), Usu varchar(20), Cla varchar(20), Hab int, Cat int, Fam int, Ofi int)
BEGIN
	INSERT INTO agentes (identificador, nombre, usuario, clave, habilidad, categoria, familia, oficina)
    VALUES (id, Nom, Usu, Cla, hab, Cat, Fam, Ofi);
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarAgente;

#---------- Procedimiento para agregar datos haciendo uso del procedimiento anterior ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarAgentes //
CREATE PROCEDURE agregarAgentes()
BEGIN
	CALL agregarAgente( 31, 'José Ramón Jiménez Reyes', 'jrjr', 'sup31', 9, 2, NULL, 3);
	CALL agregarAgente( 311, 'Pedro Fernández Arias', 'pfa', 'ag311', 5, 0, 31, NULL);
	CALL agregarAgente( 312, 'Vanesa Sánchez Rojo', 'vsr', 'ag312', 5, 0, 31, NULL);
	CALL agregarAgente( 313, 'Francisco Javier García Escobedo', 'fjge', 'ag313', 5, 0, 31, NULL);
	CALL agregarAgente( 314, 'Pilar Ramirez Pérez', 'prp', 'ag314', 5, 0, 31, NULL);
	CALL agregarAgente( 315, 'José Luis García Martínez', 'jlgm', 'ag315', 5, 0, 31, NULL);
	CALL agregarAgente( 21, 'Sebastián López Ojeda', 'slo', 'sup21', 9, 2, NULL, 2);
	CALL agregarAgente( 211, 'Diosdado Sánchez Hernández', 'dsh', 'ag211', 8, 1, 21, NULL);
	CALL agregarAgente( 2111, 'José Juan Cano Pardo', 'jjcp', 'ag2111', 5, 0, 211, NULL);
	CALL agregarAgente( 2112, 'Flor Moncada Añón', 'ag2112', 'fma', 5, 0, 211, NULL);
	CALL agregarAgente( 2113, 'Juan Manuel Alcazar Donaire', 'jmad', 'ag2113', 5, 0, 211, NULL);
	CALL agregarAgente( 2121, 'Manuel Jesús Rubia Mateos', 'mjrm', 'ag2121', 5, 0, 212, NULL);
	CALL agregarAgente( 2122, 'Esther López Delgado', 'eld', 'ag2122', 5, 0, 212, NULL);
	CALL agregarAgente( 2123, 'Francisco Javier Cabrerizo Membrilla', 'fjcm', 'ag2123', 5, 0, 212, NULL);
	CALL agregarAgente( 2124, 'Verónica Cabrerizo Menbrilla', 'vcm', 'ag2124', 5, 0, 212, NULL);
	CALL agregarAgente( 2125, 'María José Navascués Morales', 'mjnm', 'ag2125', 5, 0, 212, NULL);
	CALL agregarAgente( 2131, 'Isabel Cruz Granados', 'icg', 'ag2131', 5, 0, 213, NULL);
	CALL agregarAgente( 2132, 'Antonio Casado Fernández', 'acf', 'ag2132', 5, 0, 213, NULL);
	CALL agregarAgente( 2133, 'Gabriel Callejón García', 'gcg', 'ag2133', 5, 0, 213, NULL);
	CALL agregarAgente( 2134, 'Enrique Cano Balsera', 'ecb', 'ag2134', 5, 0, 213, NULL);
	CALL agregarAgente( 11, 'Narciso Jáimez Toro', 'njt', 'sup11', 9, 2, NULL, 1);
	CALL agregarAgente( 111, 'Jesús Baños Sancho', 'jbs', 'ag111', 8, 1, 11, NULL);
	CALL agregarAgente( 1111, 'Salvador Romero Villegas', 'srv', 'ag1111', 7, 1, 111, NULL);
	CALL agregarAgente( 1112, 'José Javier Bermúdez Hernández', 'jjbh', 'ag1112', 7, 1, 111, NULL);
	CALL agregarAgente( 1113, 'Alfonso Bonillo Sierra', 'abs', 'ag1113', 7, 1, 111, NULL);
	CALL agregarAgente( 1121, 'Silvia Thomas Barrós', 'stb', 'ag1121', 7, 1, 112, NULL);
	CALL agregarAgente( 11211, 'Ernesto Osoro Gorrotxategi', 'eog', 'ag11211', 5, 0, 1121, NULL);
	CALL agregarAgente( 11212, 'Guillermo Campos Guillén', 'gcag', 'ag11212', 5, 0, 1121, NULL);
	CALL agregarAgente( 11213, 'Antonio Fernández Ruíz', 'afr', 'ag11213', 5, 0, 1121, NULL);
	CALL agregarAgente( 11214, 'María Luisa López Caballero', 'mllc', 'ag11214', 5, 0, 1121, NULL);
	CALL agregarAgente( 11221, 'Virginia Morenas Rubio', 'vmr', 'ag11221', 5, 0, 1121, NULL);
	CALL agregarAgente( 11222, 'Rosario Castro García', 'rcg', 'ag11222', 5, 0, 1122, NULL);
	CALL agregarAgente( 11223, 'Antonio Álvarez Palomeque', 'aap', 'ag11223', 5, 0, 1122, NULL);
	CALL agregarAgente( 11224, 'David Martínez Martínez', 'dmm', 'ag11224', 5, 0, 1122, NULL);
	CALL agregarAgente( 11225, 'Juan Corral González', 'jcg', 'ag11225', 5, 0, 1122, NULL);
	CALL agregarAgente( 11226, 'Eduardo Alfada Pereira', 'eap', 'ag11226', 5, 0, 1122, NULL);
	CALL agregarAgente( 11231, 'Cayetano García Herrera', 'cgh', 'ag11231', 5, 0, 1123, NULL);
	CALL agregarAgente( 11232, 'José Antonio Sieres Vega', 'jasv', 'ag11232', 5, 0, 1123, NULL);
	CALL agregarAgente( 11233, 'Juan Manuel Guzmán García', 'jmgg', 'ag11233', 5, 0, 1123, NULL);
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarAgentes;

#---------- Procedimiento para llamar a los procedimientos anteriores de inserción de datos ----------
DELIMITER //
DROP PROCEDURE IF EXISTS agregarDatos //
CREATE PROCEDURE agregarDatos()
BEGIN
	CALL agregarOficinas;
	CALL agregarFamilias;
	CALL agregarAgentes;
END;
// DELIMITER ;

SHOW CREATE PROCEDURE agregarDatos;

SHOW PROCEDURE STATUS;

CALL agregarDatos();

SELECT * FROM oficinas;
SELECT * FROM familias;
SELECT * FROM agentes;


#---------- Procedimiento para mostrar los datos de las oficinas ----------
DELIMITER //
DROP PROCEDURE IF EXISTS mostrarOficinas //
CREATE PROCEDURE mostrarOficinas()
BEGIN
	SELECT * FROM oficinas;
END;
// DELIMITER ;

SHOW CREATE PROCEDURE mostrarOficinas;
CALL mostrarOficinas();


#---------- Procedimiento para mostrar los datos de las familias ----------
DELIMITER //
DROP PROCEDURE IF EXISTS mostrarFamilias //
CREATE PROCEDURE mostrarFamilias()
BEGIN
	SELECT * FROM familias;
END;
// DELIMITER ;

SHOW CREATE PROCEDURE mostrarFamilias;
CALL mostrarFamilias();


#---------- Procedimiento para mostrar los datos de los agentes ----------
DELIMITER //
DROP PROCEDURE IF EXISTS mostrarAgentes //
CREATE PROCEDURE mostrarAgentes()
BEGIN
	SELECT * FROM agentes;
END;
// DELIMITER ;

SHOW CREATE PROCEDURE mostrarAgentes;
CALL mostrarAgentes();

#---------- Procedimiento para mostrar todos los datos que hace uso de los anteriores procedimientos ----------
DELIMITER //
DROP PROCEDURE IF EXISTS mostrarDatos //
CREATE PROCEDURE mostrarDatos()
	BEGIN
		CALL mostrarOficinas();
		CALL mostrarFamilias();
		CALL mostrarAgentes();
END;
// DELIMITER ;   

SHOW CREATE PROCEDURE mostrarDatos;
CALL mostrarDatos();

#---------- Procedimiento para borrar todos los datos de las tablas agentes, familias y oficinas ----------
DELIMITER //
DROP PROCEDURE IF EXISTS borrarDatos //
CREATE PROCEDURE borrarDatos()
	BEGIN
		DELETE FROM agentes;
		DELETE FROM familias;
		DELETE FROM oficinas;
	END;
// DELIMITER ;

CALL borrarDatos();