<?php
// Para obtener las coordenadas de una dirección, vamos a utilizar Jaxon para llamar a los servicios de Bing Maps
// Con esta clase (Que no extiende de Conexion), definimos los métodos para obtener los datos necesarios

// DOCUMENTACIÓN DE LA APIs REST: 
//  https://learn.microsoft.com/en-us/bingmaps/rest-services/
//   -> Locations
//   -> Elevation
//   -> Routes

// 1) Definimos la clase coordenadas
class Coordenadas  {
   
    // 2) Definimos los atributos para esta clase: Nuestra APIKey, URL para realizar la solicitud y las coordenadas (ubicación) de nuestro almacén
    private $key;  
    private $iniciourl;
    private $coordenadas;
    // 3) Definimos también como atributos de clase el fin de la URL (que concatenaremos al inicio de la misma)
    // ("?include=ciso2" significa que se incluirá el código ISO 2 de los países en la respuesta de la API)
    private $finurl = "?include=ciso2&maxResults=1&c=es&key=";
    // 4) Y como último atributo, la que será la URL de acceso a Bing Routes completa
    private $url;

    // 5) Definimos el constructor de la clase (donde usamos las URL completas)
    public function __construct()  {
        // 6) Incluímos el fichero con nuestras claves y también el que tiene las coordenadas de nuestro almacén
        include("../claves.inc.php");
        include 'config.inc.php';

        // 7) Definimos el inicio de la URL de acceso a Bing (la misma que los ejemplos del tema), y le concatenamos nuestra ciudad
        $this->iniciourl = "http://dev.virtualearth.net/REST/v1/Locations/ES/" . $miCiudad . "/";
        // 8) Definimos las coordenadas de nuestro almacén 
        $this->coordenadas = $corAlmacen;
        // 9) Definimos nuestra API Key
        $this->key = $keyBing;

        // 10) Obtenemos el número de argumentos que se pasaron al constructor de la clase
        $num = func_num_args();

        if ($num == 1) {
            // 11) Si se pasó un único argumento, le concatenamos al fin de la URL nuestra clave
            $this->finurl = "?include=ciso2&maxResults=1&c=es&key=" . $this->key;
            // 12) Después, obtenemos el primer argumento pasado al constructor y utilizamos 'str_replace' para reemplazar los espacios en blanco
            $dir = str_replace(" ", "%20", func_get_arg(0));
            // 13) Finalmente, obtenemos la URL completa concatenando las anteriores partes
            $this->url = $this->iniciourl . "$dir" . $this->finurl;
        }
        // 14) Si no se pasan argumentos al constructor, simplemente concatenamos nuestra clave al final de la URL
        if ($num == 0) {
            $this->finurl = "?include=ciso2&maxResults=1&c=es&key=" . $this->key;
        }
    }

    // 15) Ahora definimos las funciones para obtener los datos de las APIS

    // 16) Función para obtener las coordenadas de una ubicación
    public function getCoordenadas()  {
        // 17) Nos traemos los datos de la API de Bing mediante el método 'file_get_contents'
        $salida   = file_get_contents($this->url);
        // 18) Decodificamos estos datos
        $salida1  = json_decode($salida, true);
        // 19) Como nos devuelve un array asociativo con los datos, accedemos a los elementos que necesitamos
        // (Ver ejemplo de respuesta en 'ejemploRespuestaProbarBing.txt')
        $valor    = $salida1["resourceSets"][0]["resources"][0]["point"]["coordinates"];
        // 20) Llamamos a la función para calcular la altitud (la implementamos después), y la almacenamos en la posición 2 del array,
        // porque en la posición 0 estará la latitud, y en la posición 1 estará la longitud
        $valor[2] = $this->calculaAltitud($valor);

        // Retornamos las coordenadas
        return $valor;
    }

    // 21) Función para calcular la altitud
    public function calculaAltitud($c)  {
        // 22) Como dijimos antes, almacenamos latitud y longitud en las dos primeras posiciones del Array
        $lat    = $c[0];
        $lon    = $c[1];
        // 23) Definimos una nueva URL a la API donde nos devolverá la altitud (fijarse que ahora es 'Elevation' y NO 'Locations')
        $url    = "http://dev.virtualearth.net/REST/v1/Elevation/List?points=$lat,$lon&key=" . $this->key;
        // 24) Traemos los datos y los codificamos
        $salida = file_get_contents($url);
        $valor  = json_decode($salida, true);
        // 25) Accedemos a los elementos del array asociativo y devolvemos el que necesitamos
        return $valor["resourceSets"][0]["resources"][0]["elevations"][0];
    }

    // 26) Función para ordenar envíos de acuerdo a una ruta optimizada utilizando Bing
    public function ordenarEnvios($dato)   {
        // 27) Construimos la URL base para acceder a Bing y obtener las rutas de conducción. Le concatenamos las coordenadas de NUESTRO ALMACEN
        $base   = "http://dev.virtualearth.net/REST/v1/Routes/Driving?c=es&wp.0=" . $this->coordenadas . "&";
        // 28) Utilizamos la función 'explode()' para dividir la cadena $dato en un array. Cada elemento resultante será un punto de entrega
        $puntos = explode("|", $dato);
        // 29) Inicializamos las dos siguientes variables para usarlas en el bucle posterior
        $num    = 0;
        $trozo  = "";
        // 30) Iteramos sobre el array $puntos antes definido
        for ($i = $num; $i < count($puntos); $i++) {
            // 31) IMPORTANTE: En cada iteración, se construye una cadena llamada $trozo que contiene los puntos de entrega en formato requerido por Bing
            // (Ejemplo de URL de la documentación de la API: 
            // http://dev.virtualearth.net/REST/v1/Routes/{travelMode}?wayPoint.1={wayPoint1}&viaWaypoint.2={viaWaypoint2}&waypoint.3={waypoint3}&wayPoint.n={waypointN}&heading={heading}&optimize={optimize}&avoid={avoid}&distanceBeforeFirstTurn={distanceBeforeFirstTurn}&routeAttributes={routeAttributes}&timeType={timeType}&dateTime={dateTime}&maxSolutions={maxSolutions}&tolerances={tolerances}&distanceUnit={distanceUnit}&key={BingMapsKey})
            $trozo .= "wp." . ++$num . "=" . $puntos[$i] . "&";
        }

        // 32) Tras el bucle, se concatena al final de la cadena $trozo detalles adicionales para optimizar la ruta
        $trozo .= "wp." . ++$num . "=" . $this->coordenadas . "&optwp=true&optimize=distance&ra=routePath&key=" . $this->key;
        // 33) Combinamos la URL base antes definida, con la cadena $trozo con la que acabamos de trabajar
        $url = $base . $trozo;

        // 34) Llamamos al método 'getRemoteFile()' que implementaremos después
        $salida  = $this->getRemoteFile($url);
        $salida1 = json_decode($salida, true); 

        // 35) Verificamos si hay errores en la respuesta o el codigo de estado
        if (isset($salida1["errors"]) && $salida1['statusCode'] == 404) {
            return "404";
        }
        // 36) Accedemos al array asociativo nuevamente y nos vamos al elemento 'waypointsOrder'
        $wayp = $salida1["resourceSets"][0]["resources"][0]['waypointsOrder'];
        
        // 37) Estas funciones eliminan el primer y último elemento del array, que corresponden al almacen de origen y destino
        array_shift($wayp);
        array_pop($wayp);

        // 38) Iteramos sobre los elementos restantes del array $wayp
        for ($i = 0; $i < count($wayp); $i++) {
            // 39) En cada iteración extraemos el número del punto de entrega eliminando el prefijo 'wp'
            $resp[] = substr(strstr($wayp[$i], '.'), 1);
        }

        // 39) Finalmente, retornamos el array obtenido
        return $resp;
    }

    // 40) Función para realizar una solicitud a una URL remota con la biblioteca cURL 
    public function getRemoteFile($url, $timeout = 10)  {
        // 41) Iniciamos sesión como en los ejemplos del tema
        $ch = curl_init();
        // 42) Le especificamos la URL a la que se hace la solicitud
        curl_setopt($ch, CURLOPT_URL, $url);
        // 43) Opción para que la respuesta de la solicitud se devuelva como cadena de texto
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        // 44) Tiempo máximo que se esperará para establecer conexion con el servidor remoto
        curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $timeout);
        // 45) Ejecutamos
        $file_contents = curl_exec($ch);
        // 46) Cerramos sesión para liberar recursos
        curl_close($ch);

        // Hacemos un ternario y si la solicitud fue exitosa, devolvemos el contenido. Si no, devolvemos FALSE
        return ($file_contents) ? $file_contents : FALSE;
    }
}
