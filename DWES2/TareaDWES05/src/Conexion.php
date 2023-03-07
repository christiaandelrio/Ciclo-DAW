<?php 

namespace Clases; //hago uso de un namespaces para las clases

use PDO;
use PDOException;

class Conexion 
{
    private $host;
    private $db;
    private $user;
    private $password;
    private $dsn;
    protected $conexion; //protected para crear un método que se herede a las subclases  y no sea visible al exterior

    public function __construct() //Creamos un constructor y definimos los atributos de la conexion
    {
        $this->host ="localhost";
        $this->db ="practicaUnidad5";
        $this->user = "gestor";
        $this->password = "secreto";
        $this->dsn = "mysql:host={$this->host};dbname={$this->db};charset=utf8mb4";
        $this->conectar();
    }

    public function conectar()
    {
        try{
            $this->conexion = new PDO($this->dsn,$this->user,$this->password);
            $this->conexion->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
        }catch(PDOException $ex){
            die("Error al conectar con la base de datos: ".$ex->getMessage());
        }

        return $this->conexion;
    }
}