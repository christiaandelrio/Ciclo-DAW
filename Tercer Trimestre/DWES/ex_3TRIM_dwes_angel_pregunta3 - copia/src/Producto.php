<?php

// Usamos namespaces y el autoload nuevamente
namespace Clases;
require '../vendor/autoload.php';

use PDO;
use PDOException;

class Producto extends Conexion {
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
     * @param string $codF
     * @return array|null
     */
    public function productoFamila($codF)
    {
        $consulta = "select nombre from productos where familia=:f";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute([':f' => $codF]);
        } catch (PDOException $ex) {
            die("Error al recuperar los productos x famila: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return null;
        while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
            $productos[] = $fila->nombre;
        }
        // Retornamos un array con los códigos de los productos de esa familia, porque así lo pide el enunciado
        return $productos;
    }

    /*
     * @param
     * @return float|null
     */
    public function getPrecio()
    {
        $consulta = "select pvp from productos where id=:i";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute([':i' => $this->id]);
        } catch (PDOException $ex) {
            die("Error al recuperar los productos x famila: " . $ex->getMessage());
        }
        // Retornamos null si no devuelve ninguna fila, o el precio del producto
        if ($stmt->rowCount() == 0) return null;
        return ($stmt->fetch(PDO::FETCH_OBJ))->pvp;
    }

    /*
     * @param
     * @return string|null
     */
    public function getNombreProducto() {    
        // ***** OJO ****** EJEMPLO DE COMO AÑADIR UNA FUNCION PARA EL EXAMEN, EN ESTE CASO UNA QUE OBTENGA EL NOMBRE
        // Es hacer una consulta muy básica, siguiendo las otras como ejemplo se saca                              
        $consulta = "select nombre from productos where id=:i";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute([':i' => $this->id]);
        } catch (PDOException $ex) {
            die("Error al recuperar el nombre del producto: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return null;
        return ($stmt->fetch(PDO::FETCH_OBJ))->nombre;
    }

    /*
     * @param int $codT
     * @return float|null
     */
    public function getMontante($codT) {
        // ********* OJOOOOOOOOO ******* EJEMPLO DE LA FUNCION DEL EXAMEN!!! OBTENER EL MONTANTE (VALOR TOTAL) DE PRODUCTOS DE UNA TIENDA
        $consulta = "SELECT SUM(stocks.unidades * productos.pvp) AS montante FROM stocks, productos WHERE stocks.producto = productos.id AND stocks.tienda = :i";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute([':i' => $codT]);
        } catch (PDOException $ex) {
            die("Error al recuperar el nombre del producto: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return null;

        $fila = $stmt->fetch(PDO::FETCH_OBJ);
        return $fila;
    }


}
