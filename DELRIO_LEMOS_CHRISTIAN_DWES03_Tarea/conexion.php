<?php 

$host = "localhost";
$db = "proyecto";
$user = "usuario";
$pass = "abc123.";

$dsn = "mysql:host=$host;dbname=$db";

try {
    $conProyecto = new PDO($dsn, $user, $pass);
    $conProyecto->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $ex) {
            die("Error en la conexion, mensaje de error:  " . $ex->getMessage());
}