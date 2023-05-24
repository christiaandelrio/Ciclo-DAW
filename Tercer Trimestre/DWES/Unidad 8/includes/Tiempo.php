<?php

// Pequeña aplicacion que hace uso de las APIs de BING MAPS y de OPENWEATHERMAP. Seguimos los ejemplos de cada API para hacerla
// Con esta clase, definimos los métodos para obtener los datos necesarios
// Realmente en esta clase ESTAMOS HACIENDO LAS SOLICITUDES QUE HICIMOS EN LOS EJEMPLOS

// 1) Definimos la clase
class Tiempo  {
    // 2) En los atributos, definimos respuesta (aunque no la utilizamos)
    private $respuesta;

    // 3) Definimos las URL de acceso a las APIs (Aqui lo hacemos ANTES DE iniciar la sesión con Curl)
    private $urlTiempo       = 'https://api.openweathermap.org/data/2.5/weather?';
    // 4) También definimos la URL de acceso a Bing Maps (la cual NO UTILIZA Curl)
    private $urlLocalizacion = "http://dev.virtualearth.net/REST/v1/Locations";

    // 5) Definimos las opciones de sesión para el Curl del OpenWeatherMap
    private $opciones;
    // 6) También definimos la URL de acceso a Bing que concatenaremos (IGUAL QUE EN EL EJEMPLO)
    private $revGeocodeUrl;

    // 7) Definimos el constructor (BASICAMENTE AQUI METEMOS LAS URL DE SOLICITUD A LAS APIs)
    // OJO!!!!!! Tenemos que pasarle como parámetros LATITUD Y LONGITUD, porque haremos las solicitudes con estos datos a ambas APIs
    public function __construct($la, $lo)   {
        // 8) Incluimos el fichero de las claves de acceso
        include("../claves.inc.php");
     
        // 9) Preparamos la URL completa de acceso a API tiempo (igual que en el ejemplo, MISMOS PARÁMETROS):
        $urlCompleto = $this->urlTiempo . '&lat=' . $la . "&lon=" . $lo . "&units=metric". "&lang=es" ."&appid=" . $keyOpenWeatherMap;
        
        // 10) OJOO!!! Aqui cogemos el atributo OPCIONES, y le pasamos la configuracion del CURL en un array como siempre, mismas opciones 
        $this->opciones = array(
            CURLOPT_URL => $urlCompleto,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => "",
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 30,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => "GET",
            CURLOPT_HTTPHEADER => array(
                "cache-control: no-cache"
            )
        ); 

        // 11) Preparamos la URL completa de acceso al API Bing (MISMOS PARÁMETROS):
        $this->revGeocodeUrl = $this->urlLocalizacion . "/$la,$lo?c=es&output=json&key={$keyBing}";
    }

    // 12) Ahora definimos las funciones para obtener los datos de las APIS

    public function getTiempo()  {
        // 13) Iniciamos la sesión (en el ejemplo básico lo haciamos al principio del fichero)
        $ch = curl_init();
        // 14) OJOO!!! Aqui llamamos al metodo de configuración, Y LE PASAMOS LA VARIABLE CON LAS OPCIONES DEFINIDAS ANTES
        curl_setopt_array($ch, $this->opciones);
        // 15) Ejecutamos consulta igualmente
        $respuesta = curl_exec($ch);
        // 16) Cerramos la sesión
        curl_close($ch);
        // 17) OJO!!! Aqui ya decodificamos directamente, asumimos que NO HUBO ERRORES EN LA SOLICITUD
        $salida = json_decode($respuesta, true);
        
        // 18) Retornamos la respuesta (SIN imprimirla con print_r)
        return $salida;
    }

    public function getLocalizacion() {
        // 19) Como ya hicimos la URL de solicitud antes, simplemente nos traemos los datos
        $salida  = file_get_contents($this->revGeocodeUrl);
        // 20) Decodificamos la respuesta del servidor
        $salida1 = json_decode($salida, true);

        // ****************HACEMOS IGUAL QUE PARA BING RUTAS***********
        // ***OJO AQUI*** Devolvemos un 'resourceSets' (es un array) PORQUE ES LO QUE DEVUELVE CUANDO HACEMOS SOLICITUD A BING (comprobar en su ejemplo)
        // Dentro del 'resourceSets', en el elemento 0 hay otro array donde está 'resources', y dentro de este array, tenemos 'address' q es lo q devuelve
        // Este ultimo 'address' tambien es un array y es donde tenemos los datos realmente
        return $salida1["resourceSets"][0]["resources"][0]["address"];
    }
}
