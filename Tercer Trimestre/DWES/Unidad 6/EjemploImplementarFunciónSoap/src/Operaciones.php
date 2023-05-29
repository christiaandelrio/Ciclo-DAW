<?php


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
    }    /**
    * Obtiene el PVP de un producto a partir de su codigo
    * @soap
    * @param int $codP
    * @return float
    */
   public function getPrecio($codP)   //Es igual que la función getPVP, para implementar una función, primero nos fijamos en producto.php, luego implementamos la función aquí
   {                                  //después vamos al cliente para hacer la llamada y por último generamos el wsdl y las clases para que se implementen los cambios. 
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
     * Devuelve un array con los nombres de los productos de una familia
     * @soap
     * @param int $codP
     * @return string
     */
    public function getNombre($codP) //Le pasaremos como parámetro el código de producto, que estableceremos con setId y que será necesario para que 
    {                                //getNombreProducto() devuelva correctamente el nombre
        $producto = new Producto();//Instancio un nuevo producto
        $producto->setId($codP);   //Utilizo un setter para pasarle el id del producto
        $datos = $producto->getNombreProducto(); //Con esta función, selecciono el nombre del producto cuyo id coincide con el que pasamos
        $producto = null; //Liberamos memoria 
        return $datos;  //Devolvemos el nombre del producto
    }
}
