<?php
/*conexion.php define una clase llamada "Conexion" en un namespace llamado "Clases". La clase tiene dos métodos, el constructor y un método estático llamado "crearConexion".

El propósito principal de esta clase es crear una conexión a una base de datos MySQL utilizando la extensión PDO (PHP Data Objects).*/
namespace Clases;

use PDO;
use PDOException;

class Conexion
{
    /* La clase tiene una propiedad estática llamada "conexion" que se utiliza para almacenar la conexión a la base de datos. La propiedad estática se utiliza para asegurar que sólo se crea una conexión a la base de datos, incluso si se crean múltiples instancias de la clase. */
    protected static $conexion;

    /* En el constructor, se verifica si ya existe una conexión establecida. Si no existe una conexión, se llama al método estático "crearConexion" para crear una nueva conexión.*/
    public function __construct()
    {
        if (self::$conexion == null) {
            self::crearConexion();
        }
    }

    /*En el método estático "crearConexion", se definen los datos de conexión a la base de datos (nombre de usuario, contraseña, nombre de la base de datos y la dirección del servidor MySQL), y se crea una instancia de PDO utilizando esos datos. También se establece el modo de error a "EXCEPTION" para que PDO lance una excepción si ocurre algún error durante la conexión.*/
    public static function crearConexion()
    {
        $user = "gestor";
        $pass = "secreto";
        $base='tarea6';
        $dsn = "mysql:host=localhost;dbname=$base;charset=utf8mb4";
        
        try {
            self::$conexion = new PDO($dsn, $user, $pass);
            self::$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $ex) {
            die("Error en la conexión: mensaje: " . $ex->getMessage());
        }
    }
}

