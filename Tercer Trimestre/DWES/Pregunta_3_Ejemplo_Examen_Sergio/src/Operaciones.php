<?php

/*Operacciones.php define una clase de PHP llamada Operaciones que contiene cuatro métodos públicos, cada uno de los cuales tiene un comentario de documentación que indica su propósito y los parámetros que recibe.*/

namespace Clases;

require '../vendor/autoload.php';


use Clases\{Producto, Stock, Familia};

class Operaciones
{
    /**
     * Obtiene el PVP de un producto a partir de su codigo
     * @soap
     * @param int $codP
     * @return float
     */
    public function getPvp($codP)
    {
        $producto = new Producto();
        $producto->setId($codP);
        $precio = $producto->getPrecio();
        $producto = null;
        return $precio;
    }
    /**
     * Devuelve el numero de unidades que existen en una tienda de un producto
     * @soap
     * @param int $codP
     * @param int $codT
     * @return int
     */
    public function getStock($codP, $codT)
    {
        $stock = new Stock();
        $stock->setProducto($codP);
        $stock->setTienda($codT);
        $uni = $stock->getUnidadesTienda();
        $stock = null;
        return $uni;
    }
    /**
     * Devuelve un array con los codigos de todas las familias
     * @soap
     * @param
     * @return string[]
     */
    public function getFamilias()
    {
        $familas = new Familia();
        $valores = $familas->getFamilias();
        $familas = null;
        return $valores;
    }
    /**
     * Devuelve un array con los nombres de los productos de una familia
     * @soap
     * @param string $codF
     * @return string[]
     */
    public function getProductosFamilia($codF)
    {
        $productos = new Producto();
        $datos = $productos->productoFamila($codF);
        $productos = null;
        return $datos;
    }

    /**
     * Devuelve un string con el montante de productos
     * @soap
     * @param string $codT
     * @return string
     */
    public function getMontante($codT)
    {
        $productos = new Producto();
        $datos = $productos->getMontante($codT);
        $productos = null;
        return $datos->montante;
    }
    
}
