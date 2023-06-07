<?php
// EXAMEN 3º AVALIACION DWES


namespace Clases;

use \PDO;

require '../vendor/autoload.php';

class Stock extends Conexion
{
    private $producto;
    private $tienda;
    private $unidades;

    public function __construct()
    {
        parent::__construct();
    }

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
