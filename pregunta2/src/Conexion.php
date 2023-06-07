<?php

class Conexion
{
    protected $conexion;

    public function __construct()
    {
        if ($this->conexion == null) {
            $this->crearConexion();
        }
    }

    protected function crearConexion()
    {
        $user = "adminpregunta2"; // Parámetros especificados en el enunciado, usuario gestor, contraseña secreto para acceder a la bd tarea6
        $pass = "secreto";
        $base = 'pregunta2';
        $dsn = "mysql:host=localhost;dbname=$base;charset=utf8mb4";

        try {
            $this->conexion = new PDO($dsn, $user, $pass);
            $this->conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $ex) {
            die("Error en la conexión: " . $ex->getMessage());
        }
    }
}

