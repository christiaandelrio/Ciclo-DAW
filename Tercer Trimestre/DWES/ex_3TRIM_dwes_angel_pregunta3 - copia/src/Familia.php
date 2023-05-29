<?php
// Usamos el namespace nuevamente y hacemos el require
namespace Clases;
require '../vendor/autoload.php';

use PDO;
use PDOException;

// La clase extiende de 'Conexion', y definimos sus atributos, cod y nombre
class Familia extends Conexion {
    private $cod;
    private $nombre;

    /**
     * Familia constructor.
     */
    public function __construct()
    {
        parent::__construct();
    }

    /**
     * @return mixed
     */
    public function getCod()
    {
        return $this->cod;
    }

    /**
     * @param mixed $cod
     */
    public function setCod($cod)
    {
        $this->cod = $cod;
    }

    /**
     * @return mixed
     */
    public function getNombre()
    {
        return $this->nombre;
    }

    /**
     * @param mixed $nombre
     */
    public function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }

    /**
     * @param
     * @return array
     */
    public function getFamilias()
    {
        // Este método va a devolver los códigos de las familias, porque así lo solicita el enunciado
        $consulta = "select cod from familias order by cod";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute();
        } catch (PDOException $ex) {
            die("Error al devolver las familias: " . $ex->getMessage());
        }
        while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
            $familias[] = $fila->cod;
        }
        // Retornamos un array con los códigos, porque asi lo pide el enunciado
        return $familias;   
    }
}
