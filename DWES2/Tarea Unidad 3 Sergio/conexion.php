<?php 
    //Opciones de la conexión
    $opciones = array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8");

    //CONSTANTES DE CONEXIÓN
    //Lugar donde está la base de datos
    define("HOST_DB", "localhost");
    //Usuario de conexión a la base de datos
    define("USER_DB", "root");
    //Contraseña de usuario
    define("PASS_DB", "");
    //Nombre de la base de datos
    define("NAME_DB", "proyecto"); //Aquí va el nombre de la base de datos

    //Conexión a la base de datos
    try{
        $conexion = new PDO(
            'mysql:host='.constant("HOST_DB").';dbname='.constant("NAME_DB"),
            constant("USER_DB"),
            constant("PASS_DB"),
            $opciones
        );
    }catch(PDOException $e){
        echo "Error: " . $e->getMessage() . "\n";
        exit;
    }
?>