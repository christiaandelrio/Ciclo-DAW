<?php
// DOCUMENTACIÓN DE LA APIs REST: 
//  https://learn.microsoft.com/en-us/bingmaps/rest-services/
//   -> Locations
//   -> Elevation
//   -> Routes
//Coordenadas.php es una clase que se encarga de obtener las coordenadas a partir de una dirección. Hace uso de la API REST de BingMaps
class Coordenadas  {
    
    private $key = "";  
    private $iniciourl;
    private $coordenadas;

    private $finurl = "?include=ciso2&maxResults=1&c=es&key=";
    private $url;

    public function __construct()  {
        include("../../claves.inc.php");
        include '../src/config.inc.php';

        //Creamos la url pasándole el nombre de nuestra ciudad (definida en el archivo config.inc.php), donde vamos a realizar los repartos
        $this->iniciourl = "http://dev.virtualearth.net/REST/v1/Locations/ES/" . $miCiudad . "/"; 
        //Coordenadas de nuestro almacén (definidas en el archivo config.inc.php)
        $this->coordenadas = $corAlmacen;
        //Clave de acceso a la API REST de BingMaps (definida en el archivo claves.inc.php)
        $this->key = $keyBing;

        //Número de argumentos pasados al constructor
        $num = func_num_args();

        //Si se pasa un argumento, definimos la url= inicio + dirección + fin (fin incluye la clave de acceso a la API)
        if ($num == 1) {
            $this->finurl = "?include=ciso2&maxResults=1&c=es&key=" . $this->key;
            $dir = str_replace(" ", "%20", func_get_arg(0));
            $this->url = $this->iniciourl . "$dir" . $this->finurl;
        }

        //Si no se pasa argumento, definimos el fin de al url agregándole al final nuestra clave de acceso a la API
        if ($num == 0) {
            $this->finurl = "?include=ciso2&maxResults=1&c=es&key=" . $this->key;
        }
    }


    //Función para obtener coordenadas a partir de una dirección
    public function getCoordenadas()  {
        //file_get_contents devuelve el contenido de un fichero en una cadena
        $salida   = file_get_contents($this->url);
        //json_decode convierte un string codificado en JSON a una variable de PHP (tenemos un array)
        $salida1  = json_decode($salida, true);
        //Obtenemos las coordenadas de la dirección
        $valor    = $salida1["resourceSets"][0]["resources"][0]["point"]["coordinates"];
        //Agregamos a $Valor un nuevo elemento. Obtenemos la altitud pasándole las coordenadas a la función calculaAltitud()
        $valor[2] = $this->calculaAltitud($valor);
        //Devolvemos el array con las coordenadas y la altitud
        return $valor;
    }

    //Función para calcular la altitud a partir de unas coordenadas
    public function calculaAltitud($c)  {
        $lat    = $c[0];
        $lon    = $c[1];
        //Creamos la ulr con la url de la api + parámetros latitud y longitud + clave de acceso a la API
        $url    = "http://dev.virtualearth.net/REST/v1/Elevation/List?points=$lat,$lon&key=" . $this->key;
        //Obtenemos el contenido de la url
        $salida = file_get_contents($url);
        //json_decode convierte un string codificado en JSON a una variable de PHP (tenemos un array)
        $valor  = json_decode($salida, true);
        //Devolvemos la altitud
        return $valor["resourceSets"][0]["resources"][0]["elevations"][0];
    }

    //Método para ordenar envíos en base a la API REST de BingMaps
    public function ordenarEnvios($dato)   {
        //Creamos la URL para la petición pasándole las coordenadas del almacén de origen y los puntos intermedios de reparto
        $base   = "http://dev.virtualearth.net/REST/v1/Routes/Driving?c=es&wp.0=" . $this->coordenadas . "&";
        $puntos = explode("|", $dato);
        $num    = 0;
        $trozo  = "";

        for ($i = $num; $i < count($puntos); $i++) {
            $trozo .= "wp." . ++$num . "=" . $puntos[$i] . "&";
        }

        $trozo .= "wp." . ++$num . "=" . $this->coordenadas . "&optwp=true&optimize=distance&ra=routePath&key=" . $this->key;
        $url = $base . $trozo;

        //die($url);
        //Obtenemos el contenido de la url haciendo uso de la función que hemos creado getRemoteFile()
        $salida  = $this->getRemoteFile($url);
        //json_decode convierte un string codificado en JSON a una variable de PHP
        $salida1 = json_decode($salida, true); 

        //Si la respuesta (saida1) contiene errores y el código de error es 404, devolvemos 404
        if (isset($salida1["errors"]) && $salida1['statusCode'] == 404) {
            return "404";
        }
        
        //Obentemos los waypoints (puntos de reparto) ordenados
        $wayp = $salida1["resourceSets"][0]["resources"][0]['waypointsOrder'];
        
        //quitamos el primero y el ultimo (inicio y fin) (El almacen)
        array_shift($wayp);
        array_pop($wayp);

        //Creamos un array a partir de la cadena de waypoints ordenados
        for ($i = 0; $i < count($wayp); $i++) {
            $resp[] = substr(strstr($wayp[$i], '.'), 1);
        }

        //Devolvemos el array con los puntos de reparto ordenados
        return $resp;
    }

    //Método para obtener el contenido de una url
    public function getRemoteFile($url, $timeout = 10)  {
        // curl_init() Inicia una nueva sesión y devuelve el manipulador curl para el uso de las funciones curl_setopt(), curl_exec(), y curl_close().
        $ch = curl_init();
        //curl_setopt() Configura una opción para una transferencia cURL
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $timeout);
        //curl_exec() Establece una sesión cURL. Ejecuta la sesión cURL que se le pasa como parámetro.
        $file_contents = curl_exec($ch);
        //curl_close() Cierra una sesión cURL. Cierra una sesión cURL y libera todos los recursos. El recurso cURL, también es eliminado.
        curl_close($ch);

        //Si se ha obtenido contenido, lo devolvemos, si no, devolvemos FALSE
        return ($file_contents) ? $file_contents : FALSE;
    }
}
