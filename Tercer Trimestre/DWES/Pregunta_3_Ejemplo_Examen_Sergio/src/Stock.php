<?php
/*Stock.php define una clase en PHP llamada Stock. La clase extiende otra clase llamada Conexion, que se utiliza para conectarse a una base de datos.*/

namespace Clases;

use \PDO;

require '../vendor/autoload.php';

class Stock extends Conexion
{
    /*Hay tres propiedades privadas: $producto, $tienda y $unidades. Estas propiedades representan un producto, una tienda y la cantidad de unidades de un producto en una tienda determinada, respectivamente.*/
    private $producto;
    private $tienda;
    private $unidades;

    /*El constructor de la clase invoca el constructor de la clase padre, Conexion.*/
    public function __construct()
    {
        parent::__construct();
    }

    /*Hay varios métodos que proporcionan acceso a las propiedades. Los métodos getProducto(), getTienda() y getUnidades() devuelven los valores de las propiedades correspondientes, mientras que los métodos setProducto(), setTienda() y setUnidades() establecen los valores de las propiedades correspondientes.*/
    /**
     * @return mixed
     */
    public function getProducto()
    {
        return $this->producto;
    }

    /**
     * @param mixed $producto
     */
    public function setProducto($producto)
    {
        $this->producto = $producto;
    }

    /**
     * @return mixed
     */
    public function getTienda()
    {
        return $this->tienda;
    }

    /**
     * @param mixed $tienda
     */
    public function setTienda($tienda)
    {
        $this->tienda = $tienda;
    }

    /**
     * @return mixed
     */
    public function getUnidades()
    {
        return $this->unidades;
    }

    /**
     * @param mixed $unidades
     */
    public function setUnidades($unidades)
    {
        $this->unidades = $unidades;
    }


    /*El método llamado getUnidadesTienda() ejecuta una consulta SQL a través de la conexión a la base de datos establecida por la clase padre. La consulta busca las unidades de un producto en una tienda específica y devuelve el resultado como un número entero. Si no hay resultados, el método devuelve 0.*/
    /*
     * @param
     * @return int|null
     */
    public function getUnidadesTienda()
    {
        $consulta = "select unidades from stocks where producto=:p AND tienda=:t";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute([
                ':p' => $this->producto,
                ':t' => $this->tienda
            ]);
        } catch (\PDOException $ex) {
            die("Error al recuperar los unidades: " . $ex->getMessage());
        }
        if ($stmt->rowCount() == 0) return 0;
        return ($stmt->fetch(PDO::FETCH_OBJ))->unidades;
    }
}
