<?php
// EXAMEN 3º AVALIACION DWES


namespace Clases;

require '../vendor/autoload.php';

use PDO;

class Familia extends Conexion
{
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
        $consulta = "select cod from familias order by cod";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute();
        } catch (\PDOException $ex) {
            die("Error al devolver las familias: " . $ex->getMessage());
        }
        while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
            $familias[] = $fila->cod;
        }
        return $familias;
    }
}
