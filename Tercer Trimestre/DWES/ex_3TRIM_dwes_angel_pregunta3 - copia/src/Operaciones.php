<?php

// Hacemos uso del namespace y del autoload nuevamente
namespace Clases;
require '../vendor/autoload.php';

// Usamos las clases donde definimos los métodos para trabajar con la base de datos
use Clases\{Producto, Stock, Familia};

// En esta clase, implementamos los métodos que nos pide el enunciado (Haciendo comentarios nuevamente para el soap)
class Operaciones
{
    /**
     * Obtiene el PVP de un producto a partir de su codigo
     * @soap
     * @param int $codP
     * @return float
     */
    public function getPvp($codP)   
    {   // Creamos objeto de la clase, y usamos el setter para definir el id que necesitamos para la consulta
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
    {   // Creamos objeto de la clase, y usamos los setters para definir los parámetros necesarios para la consulta en la clase Stock
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
    {   // Creamos objeto de la clase, y llamamos al método
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
    {   // Creamos objeto de la clase, y llamamos al método para obtener los datos
        // OJO!!! Aqui no hacemos un 'setFamilia', porque el método 'productoFamila' RECIBE UN PARÁMETRO (VERLO EN LA CLASE PRODUCTO)
        $productos = new Producto();
        $datos = $productos->productoFamila($codF);
        $productos = null;
        return $datos;
    }

    // ***** OJOOOOO ***** IMPLEMENTAMOS LA FUNCION 'getNombreProducto', DE PRUEBA PARA EL EXAMEN. LA TENEMOS EN LA CLASE 'PRODUCTO.PHP'

    /**
     * Devuelve el nombre de un producto
     * @soap
     * @param int $codP
     * @return string
     */
    public function getNombreProducto($codP) {
        $producto = new Producto();
        $producto->setId($codP);
        $nombre = $producto->getNombreProducto();
        $producto = null;
        return $nombre;
    }

    // ************* OJOOOOOOOOOOOOOOO **** FUNCION DEL MONTANTE 'getMontante', VER CLASE 'PRODUCTO.PHP'

    /**
     * Devuelve el montante de los productos de una tienda
     * @soap
     * @param int $codT
     * @return float
     */
    public function getMontante($codT) {
        $producto = new Producto();
        $datos = $producto->getMontante($codT);
        $producto = null;
        return $datos->montante;
    }


}   

