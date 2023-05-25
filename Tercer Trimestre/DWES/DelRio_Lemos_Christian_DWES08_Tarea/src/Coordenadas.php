<?php
// DOCUMENTACIÓN DE LA APIs REST: 
//  https://learn.microsoft.com/en-us/bingmaps/rest-services/
//   -> Locations
//   -> Elevation
//   -> Routes

class Coordenadas  {
   
    private $key = "";  
    private $iniciourl;
    private $coordenadas;

    private $finurl = "?include=ciso2&maxResults=1&c=es&key=";
    private $url;

    public function __construct()  {
        include("../../claves.inc.php"); //Hacemos un include de las claves necesarias
        include '../src/config.inc.php';

        $this->iniciourl = "http://dev.virtualearth.net/REST/v1/Locations/ES/" . $miCiudad . "/"; 
        $this->coordenadas = $corAlmacen;
        $this->key = $keyBing;

        $num = func_num_args();

        if ($num == 1) {
            $this->finurl = "?include=ciso2&maxResults=1&c=es&key=" . $this->key;
            $dir = str_replace(" ", "%20", func_get_arg(0));
            $this->url = $this->iniciourl . "$dir" . $this->finurl;
        }

        if ($num == 0) {
            $this->finurl = "?include=ciso2&maxResults=1&c=es&key=" . $this->key;
        }
    }


    public function getCoordenadas()  { //Esta función se encarga de obtener las coordenadas del punto solicitado al crear un envío
        $salida   = file_get_contents($this->url);
        $salida1  = json_decode($salida, true);
        $valor    = $salida1["resourceSets"][0]["resources"][0]["point"]["coordinates"];
        $valor[2] = $this->calculaAltitud($valor);

        return $valor;
    }

    public function calculaAltitud($c)  { //Calcula la altitud
        $lat    = $c[0];
        $lon    = $c[1];
        $url    = "http://dev.virtualearth.net/REST/v1/Elevation/List?points=$lat,$lon&key=" . $this->key;
        $salida = file_get_contents($url);
        $valor  = json_decode($salida, true);
        
        return $valor["resourceSets"][0]["resources"][0]["elevations"][0];
    }

    public function ordenarEnvios($dato)   {
        //Ponemos las coordenadas del alamacen de origen y destino
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
        $salida  = $this->getRemoteFile($url);
        $salida1 = json_decode($salida, true); 

        if (isset($salida1["errors"]) && $salida1['statusCode'] == 404) { //Si hay algún error, devuelve el código de estado 404
            return "404";
        }
        
        $wayp = $salida1["resourceSets"][0]["resources"][0]['waypointsOrder'];
        
        //quitamos el primero y el ultimo (inicio y fin) (El almacen)
        array_shift($wayp);
        array_pop($wayp);

        for ($i = 0; $i < count($wayp); $i++) {
            $resp[] = substr(strstr($wayp[$i], '.'), 1);
        }

        return $resp;
    }

    public function getRemoteFile($url, $timeout = 10)  {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $timeout);
        $file_contents = curl_exec($ch);
        curl_close($ch);

        return ($file_contents) ? $file_contents : FALSE;
    }
}
