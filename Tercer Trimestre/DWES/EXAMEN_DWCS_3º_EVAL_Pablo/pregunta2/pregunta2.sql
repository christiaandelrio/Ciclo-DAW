-- 1.- Creamos la Base de Datos
DROP DATABASE IF EXISTS pregunta2;
create database pregunta2 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- Seleccionamos la base de datos "pregunta2"
use pregunta2;

-- 2.- Creamos las tablas
-- 2.1- Creamos la tabla usuarios
create table usuarios(
   usuario varchar(20) primary key,
   pass varchar(64) not null
);

-- 2.2- Tabla palindromos
create table if not exists palindromos(
   id int auto_increment primary key,
   usuario varchar(20) NOT NULL,
   frase varchar(500) not null,
   esPalindromo int not null,
   CONSTRAINT FOREIGN KEY (usuario) REFERENCES usuarios(usuario)
     ON DELETE RESTRICT
	 ON UPDATE CASCADE
);

-- 3.- Creamos un par de usuarios de prueba, vamos a utilizar sha256
-- Para guardar las contraseñas, en realidad guardamos el hash.
insert into usuarios select 'usuario1' , sha2('pusuario1',256);
insert into usuarios select 'usuario2' , sha2('pusuario2',256);
insert into usuarios select 'usuario3' , sha2('pusuario3',256);

-- 4.- Creamos un usuario
create user adminpregunta2@'localhost' identified by "secreto";

-- 5.- Le damos permiso en la base de datos "pregunta2"
grant all on pregunta2.* to adminpregunta2@'localhost';