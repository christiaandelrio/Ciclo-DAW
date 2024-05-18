#--------------  CREAR LA BASE DE DATOS Empresa --------------
drop database if exists `Empresa`;
create database if not exists `Empresa`;
use `Empresa`;

#--------------  BORRAR TODAS LAS TABLAS --------------
DROP TABLE IF EXISTS oficinas, oficinasCopia, oficinasOLD;
DROP TABLE IF EXISTS familias, familiasCopia, familiasOLD;
DROP TABLE IF EXISTS agentes, agentesCopia, agentesOLD;

#--------------  CREAR TODAS LAS TABLAS --------------
create table oficinas (
	identificador     	int,
	nombre          	varchar( 40 ),
	domicilio     		varchar( 40 ),
	localidad     		varchar( 20 ),
	codigo_postal      varchar( 5 ),
	CONSTRAINT	PK_oficinas				PRIMARY KEY( identificador ),
	CONSTRAINT	UQ_oficinas_nombre		UNIQUE( nombre ),
	CONSTRAINT oficinas_nombre_NULO	CHECK( nombre IS NOT NULL )
);

create table familias (
	identificador     	int,
	nombre         	varchar( 40 ),
	familia          	int,
	oficina          	int,
	CONSTRAINT	PK_familias				PRIMARY KEY( identificador ),
	CONSTRAINT	FK_familias_familias	FOREIGN KEY( familia )		REFERENCES familias( identificador )	ON DELETE CASCADE,
	CONSTRAINT	FK_familias_oficinas	FOREIGN KEY( oficina )		REFERENCES oficinas( identificador )	ON DELETE CASCADE,
	CONSTRAINT	UQ_familias_nombre		UNIQUE( nombre ),
	CONSTRAINT	familia_nombre_NULO		CHECK( nombre IS NOT NULL )
);

create table agentes (
	identificador    	int,
	nombre          	varchar( 60 ),
	usuario          	varchar( 20 ),
	clave          	varchar( 20 ),
	habilidad     		int,
	categoria     		int,
	familia          	int,
	oficina          	int,
	CONSTRAINT	PK_agentes				PRIMARY KEY( identificador ),
	CONSTRAINT FK_agentes_familias		FOREIGN KEY( familia )		REFERENCES familias( identificador )	ON DELETE CASCADE,
	CONSTRAINT FK_agentes_oficinas		FOREIGN KEY( oficina )		REFERENCES oficinas( identificador )	ON DELETE CASCADE,
	CONSTRAINT UQ_agentes_usuario		UNIQUE( usuario ),
	CONSTRAINT agentes_nombre_NULO		CHECK( nombre IS NOT NULL ),
	CONSTRAINT agentes_usuario_NULO	CHECK( usuario IS NOT NULL ),
	CONSTRAINT agentes_clave_NULA		CHECK( clave IS NOT NULL ),
	CONSTRAINT agentes_habilidad_NULA	CHECK( habilidad IS NOT NULL ),
	CONSTRAINT agentes_categoria_NULA	CHECK( categoria IS NOT NULL )
);

#--------------  CREAR LAS TABLAS COPIA --------------
CREATE TABLE oficinasCopia AS SELECT * FROM oficinas;	
CREATE TABLE oficinasOLD AS SELECT * FROM oficinas;
CREATE TABLE familiasCopia AS SELECT * FROM familias;	
CREATE TABLE familiasOLD AS SELECT * FROM familias;
CREATE TABLE agentesCopia  AS SELECT * FROM agentes;	
CREATE TABLE agentesOLD  AS SELECT * FROM agentes;