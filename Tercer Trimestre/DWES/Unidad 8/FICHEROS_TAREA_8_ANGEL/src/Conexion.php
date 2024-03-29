<?php

// Definimos la clase y sus atributos
class Conexion {
    private $host;
    private $db;
    private $user;
    private $pass;
    private $dsn;
    protected $conexion;

    // Constructor de la clase
    public function __construct() {
        $this->host = "localhost";
        $this->db = "proyecto";
        $this->user = "gestor";
        $this->pass = "secreto";
        $this->dsn = "mysql:host={$this->host};dbname={$this->db};charset=utf8mb4";
        $this->crearConexion();
    }

    // Función para crear la conexión, con bloque try-catch para capturar posibles errores y retornando la conexión
    public function crearConexion() {

        try {
            $this->conexion = new PDO($this->dsn, $this->user, $this->pass);
            $this->conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $ex) {
            die("Error en la conexión: mensaje: " . $ex->getMessage());
        }
        return $this->conexion;
    }
}
