<?php
require 'Conexion.php';

//Clase Producto que hereda de la clase Conexion y que se encarga de gestionar los productos con la base de datos
class Producto extends Conexion
{
    private $id;
    private $nombre;
    private $nombre_corto;
    private $pvp;
    private $famila;
    private $descripcion;

    /**
     * Producto constructor.
     */
    public function __construct()
    {
        parent::__construct();
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
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
     * @return mixed
     */
    public function getNombreCorto()
    {
        return $this->nombre_corto;
    }

    /**
     * @param mixed $nombre_corto
     */
    public function setNombreCorto($nombre_corto)
    {
        $this->nombre_corto = $nombre_corto;
    }

    /**
     * @return mixed
     */
    public function getPvp()
    {
        return $this->pvp;
    }

    /**
     * @param mixed $pvp
     */
    public function setPvp($pvp)
    {
        $this->pvp = $pvp;
    }

    /**
     * @return mixed
     */
    public function getFamila()
    {
        return $this->famila;
    }

    /**
     * @param mixed $famila
     */
    public function setFamila($famila)
    {
        $this->famila = $famila;
    }

    /**
     * @return mixed
     */
    public function getDescripcion()
    {
        return $this->descripcion;
    }

    /**
     * @param mixed $descripcion
     */
    public function setDescripcion($descripcion)
    {
        $this->descripcion = $descripcion;
    }

    /*
     * @param
     * @return mixed
     */
    public function listadoProductos()
    {
        $consulta = "select * from productos order by nombre, familia";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute();
        } catch (\PDOException $ex) {
            die("Error al recuperar los productos" . $ex->getMessage());
        }
        return $stmt;
    }
}
