<?php
/*
Producto.php define una clase PHP llamada Producto que utiliza la biblioteca PDO para acceder a una base de datos. La clase Producto extiende otra clase llamada Conexion que se encarga de establecer la conexión con la base de datos.

Esta clase Producto se utiliza para representar objetos de productos y proporciona métodos para acceder y manipular los datos de la base de datos relacionados con los productos.*/

namespace Clases;

require '../vendor/autoload.php';

use \PDO;

class Producto extends Conexion
{
    /*La clase Producto tiene seis propiedades privadas: $id, $nombre, $nombre_corto, $pvp, $famila y $descripcion, que representan los campos de una tabla de productos en la base de datos.*/
    private $id;
    private $nombre;
    private $nombre_corto;
    private $pvp;
    private $famila;
    private $descripcion;

    /*La clase tiene un constructor que llama al constructor de la clase Conexion para establecer la conexión con la base de datos.*/
    /**
     * Producto constructor.
     */
    public function __construct()
    {
        parent::__construct();
    }

    /*La clase también tiene métodos getter y setter para cada propiedad, lo que permite acceder y establecer los valores de las propiedades desde fuera de la clase.*/
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

    /*La clase tiene dos métodos adicionales: productoFamila y getPrecio. El método productoFamila toma un parámetro $codF que representa el código de una familia de productos y devuelve un array con los nombres de los productos que pertenecen a esa familia. El método getPrecio devuelve el precio de un producto según su identificador de producto $id.*/
    /*
     * @param string $codF
     * @return array|null
     */
    public function productoFamila($codF)
    {
        $consulta = "select nombre from productos where familia=:f";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute([':f' => $codF]);
        } catch (\PDOException $ex) {
            die("Error al recuperar los productos x famila: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return null;
        while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
            $productos[] = $fila->nombre;
        }
        return $productos;
    }
    /*
     * @param
     * @return float|null
     */
    public function getPrecio()
    {
        $consulta = "select pvp from productos where id=:i";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute([':i' => $this->id]);
        } catch (\PDOException $ex) {
            die("Error al recuperar los productos x famila: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return null;
        return ($stmt->fetch(PDO::FETCH_OBJ))->pvp;
    }

    /*
    * @param string $codT
    * @return float|null
    */

    public function getMontante($codT)
    {
        $consulta = "SELECT SUM(stocks.unidades * productos.pvp) AS montante FROM stocks, productos WHERE stocks.producto = productos.id AND stocks.tienda = :i";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute([':i' => $codT]);
        } catch (\PDOException $ex) {
            die("Error al recuperar los productos: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return null;

        $fila = $stmt->fetch(PDO::FETCH_OBJ);

        return $fila;
    }


}
