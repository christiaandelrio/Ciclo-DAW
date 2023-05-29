<?php

/*ClasesOperacionesService extiende la clase SoapClient. Esta clase se utiliza para realizar llamadas a un servicio web SOAP a través de sus métodos públicos.

El constructor de la clase recibe dos parámetros: un array de opciones y una URL opcional del archivo WSDL. El array de opciones se fusiona con un array predefinido que contiene una configuración de características, como features con un valor de 1. Si la URL del archivo WSDL no se proporciona, se establece en una dirección de localhost que se espera que sea la dirección del servidor SOAP.*/

namespace Clases\Clases1;

class ClasesOperacionesService extends \SoapClient
{

    /**
     * @var array $classmap The defined classes
     */
    private static $classmap = array (
);

    /**
     * @param array $options A array of config values
     * @param string $wsdl The wsdl file to use
     */
    public function __construct(array $options = array(), $wsdl = null)
    {
    
  foreach (self::$classmap as $key => $value) {
    if (!isset($options['classmap'][$key])) {
      $options['classmap'][$key] = $value;
    }
  }
      $options = array_merge(array (
  'features' => 1,
), $options);
      if (!$wsdl) {
        $wsdl = 'http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap/servicioW.php?wsdl';
      }
      parent::__construct($wsdl, $options);
    }

    /*La clase tiene tres métodos públicos: getPvp, getStock y getProductosFamilia, que hacen llamadas a los métodos correspondientes del servicio web SOAP a través de __soapCall. Los métodos públicos toman los parámetros necesarios para hacer la llamada a los métodos del servicio web.*/

    /**
     * Obtiene el PVP de un producto a partir de su codigo
     *
     * @param int $codP
     * @return float
     */
    public function getPvp($codP)
    {
      return $this->__soapCall('getPvp', array($codP));
    }

    /**
     * Devuelve el numero de unidades que existen en una tienda de un producto
     *
     * @param int $codP
     * @param int $codT
     * @return int
     */
    public function getStock($codP, $codT)
    {
      return $this->__soapCall('getStock', array($codP, $codT));
    }

    /**
     * Devuelve un array con los codigos de todas las familias
     *
     * @return Array
     */
    public function getFamilias()
    {
      return $this->__soapCall('getFamilias', array());
    }

    /**
     * Devuelve un array con los nombres de los productos de una familia
     *
     * @param string $codF
     * @return Array
     */
    public function getProductosFamilia($codF)
    {
      return $this->__soapCall('getProductosFamilia', array($codF));
    }

}
