<?php

// Esta va a ser la página principal de la aplicación, donde mostraremos las listas de reparto y tendremos acceso al resto de funcionalidades

// En este caso no hacemos un inicio de sesión porque no trabajamos con Usuarios y por tanto tampoco con sesiones

// 1) Incluimos los ficheros donde tenemos la ubicación de nuestro almacén, la solicitud de cliente a Google Tasks, y los métodos necesarios
include '../src/Tasks.php';
include '../src/config.inc.php';
include '../src/Tools.php';

// 2) Hacemos el autoload (uso de google apiclient)
require(__DIR__ . '/../vendor/autoload.php');

// 3) Preparamos Jaxon
use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

// 4) Utilizamos las siguientes sentencias para que el contenido generado durante la ejecución del script se envíe en tiempo real al navegador,
// sin esperar a que el script termine su ejecución al completo. Esto es útil para mostrar actualizaciones de estado a medida que se realizan acciones.
ob_end_flush();
ob_implicit_flush();

// 5) Instanciamos el objeto de acceso a la API de Tasks pasando como parámetro la variable $client (del fichero Tasks.php)
$service = new Google_Service_Tasks($client);

// 6) Definimos las funciones necesarias para interactuar con el servicio de Google Tasks

// 7) Primero, la función para obtener las Listas de Tareas
function getListasTareas() {
    // 8) Llamamos a la variable global de acceso a la API de Tasks
    global $service;
    // 9) Establecemos como opciones el número máximo de resultados, dejándolo en 100
    $optParams = ['maxResults' => 100];
    // 10) Obtenemos los resultados tal y como haciamos en los ejemplos
    $results = $service->tasklists->listTasklists($optParams);
    // 11) Retornamos el resultado
    return $results;
}
// 12) Funcion para obtener las tareas de una lista con el id de la lista
function getTareas($id) {
    // 13) Volvemos a llamar a la variable global del servicio
    global $service;
    // 14) Obtenemos los resultados tal y como hacemos en los ejemplos
    $res1 = $service->tasks->listTasks($id);
    // 15) Retornamos el resultado
    return $res1;
}
// 16) Con esta función, obtenemos las notas asociadas a una tarea específica, y le pasamos el id de una lista específica, y el título de una tarea
function getNotes($id, $titulo) {
    // 17) Obtenemos todas las tareas de la lista identificada por el id que le pasamos
    $tareas = getTareas($id);  
    // 18) Iteramos en cada tarea de la lista con foreach
    foreach ($tareas->getItems() as $tarea) {
        // 19) Comparamos cada tarea de la lista con el valor de $titulo
        if ($tarea->getTitle() == $titulo) {
            // 20) Si tenemos coincidencias, devolvemos las notas asociadas
            return $tarea->getNotes();
        }
    }
}

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- 21) COMO HACEMOS USO DE Jquery, INCLUIMOS EL SCRIPT NECESARIO -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Repartos</title>
    <!-- 22) Incluimos el script con nuestras funciones JavaScript-->
    <script src="../js/funciones.js"></script>
</head>

<body style="background:#00bfa5;">
    <?php
    // 23) Verificamos si se ha enviado la latitud en el formulario de 'envio.php' a través de POST
    if (isset($_POST['lat'])) {
        // 24) Creamos una cadena donde concatenamos latitud y longitud separadas por una coma
        $note  = $_POST['lat'] . "," . $_POST['lon'];
        // 25) Creamos otra cadena donde concatenamos el producto y la dirección, enviadas mediante POST también del formulario de 'envio.php'
        // Con ucwords convertimos la primera letra de cada palabra en mayúsculas
        $title = ucwords($_POST['pro']) . ". " . ucwords($_POST['dir']);
        // 26) Guardamos en una variable el id que también obtenemos del formulario de 'envio.php'
        $idLt  = $_POST['idLTarea'];
        // 27) Eliminamos la variable de sesión para asegurarnos que no haya datos antiguos almacenados antes de guardar la nueva tarea
        unset($_SESSION[$idLt]);
        
        // 28) Procedemos a guardar la tarea
        // 29) Creamos un array donde almacenamos titulo y notas de la tarea (antes definimos estas cadenas)
        $op    = ['title' => $title, 'notes' => $note];
        // 30) Creamos nueva instancia pasandole el array anterior
        $tarea = new Google_Service_Tasks_Task($op);
        // 31) Ahora insertamos la nueva tarea con el método insert(), capturando posibles excepciones
        try {
            // Le pasamos como parámetros el id de la lista de tareas, y la propia tarea nueva
            $res = $service->tasks->insert($idLt, $tarea);
        } catch (Google_Exception $ex) {
            die("Error al guardar la tarea: " . $ex);
        }
        // 32) Eliminamos la variable POST con la latitud, para asegurarnos que no se procesa nuevamente la misma tarea
        unset($_POST['lat']);
    }

    // 33) IMPORTANTE: Verificamos si se ha enviado por GET la variable 'action', y según su valor, ejecutamos un código u otro
    // Esta variable GET la obtenemos de los enlaces definidos en este mismo fichero, con las opciones de crear nueva lista, borrarla, borrar tarea...
    if (isset($_GET['action'])) {
        // 34) Utilizamos estructura de control switch
        switch ($_GET['action']) {
            // 35) Borrar lista de tareas
            case 'blt':
                // 36) Intentamos borrar la lista de tareas con el método 'delete()', capturando posibles excepciones
                try {
                    $service->tasklists->delete($_GET['idlt']);
                } catch (Google_Exception $ex) {
                    die("Error al borrar la lista de tareas: " . $ex);
                }
                // 37) Eliminamos la variable de sesión asociada al id de la lista de tareas
                unset($_SESSION[$_GET['idlt']]);
                break;
            // 38) Borrar tarea específica
            case 'bt':
                // 39) Nuevamente método 'delete()' capturando posibles excepciones  
                try {
                    $service->tasks->delete($_GET['idlt'], $_GET['idt']);
                } catch (Google_Exception $ex) {
                    die("Error al borrar la tarea: " . $ex);
                }
                // 40) Volvemos a eliminar la variable de sesión
                unset($_SESSION[$_GET['idlt']]);
                break;
            // 41) Nueva lista tareas
            case 'nlt': 
                // 42) Creamos objeto DateTime a partir del valor de title (obtenido del id del HTML)
                $fecha  = new DateTime($_GET['title']);
                // 43) Formateamos la fecha
                $fecha1 = date_format($fecha, "d/m/Y");
                // 44) Concatenamos la fecha con 'Repartos' para mostrarla
                $titulo = "Repartos " . $fecha1;
                // 45) Verificamos si ya existe una lista de tareas con ese mismo título
                if (!existeLista($titulo)) {
                    // 46) Si no existe, procedemos a guardar la nueva lista con 'insert()', capturando posibles excepciones
                    $opciones = ["title" => $titulo];
                    $taskList = new Google_Service_Tasks_TaskList($opciones);
                    try {
                        $service->tasklists->insert($taskList);
                    } catch (Google_Exception $ex) {
                        die("Error al crear una lista de tareas: " . $ex);
                    }
                } else {
                    // 47) Si la lista de tareas ya existe, mostramos mensaje de alerta en JavaScript
                    echo "<script>alert('Ya existe un reparto para ese día !!!');</script>";
                }
                break;
            /* ************* TODO ESTO LO PASAMOS AL NUEVO FICHERO 'repartosAux.php'
            // 48) Ordenar envios (con 'oEnvios' y con 'pos' trabajamos en el fichero 'funciones.js')
            case 'oEnvios':
                // 49) Verificamos si hemos recibido por GET la variable 'pos', si no es así, no realizamos ninguna accion  
                if (!isset($_GET['pos'])) { 

                }
                // 50) Obtenemos los valores de posicion y el id de la lista de las tareas de la solicitud GET
                $apos = $_GET['pos'];
                $id_lista = $_GET['idLt'];
                // 51) Eliminamos la variable de sesion correspondiente al id de la lista de tareas
                unset($_SESSION['idLt']);
                
                // 52) Obtenemos todas las tareas de esta lista de tareas llamando al método que definimos arriba
                $tareas = getTareas($id_lista);
                // 53) Iteramos sobre el Array que contiene las posiciones de las tareas
                foreach ($apos as $k => $v) {
                    //los envios me los manda ordenados del 1 al n
                    //en php los array empiezan por cero, por eso restamos 1 
                    //asi el envio 1 pasa a ser el 0, el 2 el 1 ...
                    $p = $v - 1;
                    $arrayO[$k] = $tareas->getItems()[$p]->getTitle();
                }
                // 54) Guardamos el array creado en la variable de sesión asociada a la lista de tareas
                $_SESSION[$id_lista] = $arrayO;
                break;
            // 55) Ocultar el orden de los envios, eliminando la variable de sesión
            case 'oce':
                $lista = $_GET['idlt'];
                unset($_SESSION[$lista]); */
        }
    }
    // 56) Funcion para comprobar si existe una lista
    function existeLista($l)  {
        // 57) Llamamos al metodo que definimos arriba
        $listas = getListasTareas();
        // 58) Iteramos para obtener las listas de tareas
        foreach ($listas->getItems() as $lista) {
            // 59) Si hay coincidencia, retornamos true, pues la lista existe
            if ($lista->getTitle() == $l) return true;
        }
        return false;
    }

?>
    <!-- Hacemos el diseño de la página principal -->
    <h4 class="text-center mt-3">Gestión de Pedidos</h4>
    <div class="container mt-4" style='width:80rem;'>
        <!-- 60) El formulario apunta a esta propia página. También agregamos el atributo 'onsubmit' para validar la fecha antes de enviarlo-->
        <form action='<?php echo $_SERVER['PHP_SELF'] ?>' method='get' onsubmit="return validarFecha();">
            <div class="row">
                <div class="col-md-3 mb-2">
                    <!-- 61) Boton para crear una nueva lista de reparto, donde pasamos por hidden el valor 'nlt' -->
                    <button type='submit' class="btn btn-info"><i class='fas fa-plus mr-1'></i>Nueva Lista de Reparto</button>
                </div>
                <input type='hidden' name='action' value='nlt'>
                <div class="col-md-4">
                    <!-- 62) Input para meter la fecha -->
                    <input type='date' class="form form-control" id="title" name="title" required></div>
            </div>
        </form>
        <?php
        // 63) Llamamos a la funcion para obtener las listas de tareas (la definimos arriba) e iteramos sobre ellas
        $listas = getListasTareas();
        foreach ($listas->getItems() as $lista) {
            // 64) Verificamos que el título de la lista sea igual a lo indicado. Si se cumple, usamos 'continue' para pasar a la siguiente iteración del bucle
            if ($lista->getTitle() == "My Tasks" || $lista->getTitle() == "Mis tareas") continue;
            // 65) Creamos una tabla que asociamos a una lista de tareas de forma única
            echo "<table class='table mt-2' id='t_{$lista->getId()}'>\n";
            echo "<thead class='bg-secondary'>\n";
            echo "<tr>\n";
            // 66) Se muestra el título de una lista de tareas
            echo "<th scope='col' style='width:42rem;'>{$lista->getTitle()}</th>\n";
            // 67) En la misma fila de encabezado, mostramos una celda de encabezado adicional donde mostraremos los botones
            echo "<th scope='col' class='text-right'>\n";
            // 68) Boton para agregar un envio nuevo (que nos lleva al formulario de crear envios)
            echo "<a href='envio.php?id={$lista->getId()}' class='btn btn-info mr-2 btn-sm'><i class='fas fa-plus mr-1'></i>Nuevo</a>\n";
            // 69) Boton para ordenar los envios
            echo "<button class='btn btn-success mr-2 btn-sm' onclick=\"ordenarEnvios('{$lista->getId()}')\"><i class='fas fa-sort mr-1'></i>Ordenada ON/OFF</button>\n";
            // 70) Eliminamos ahora el boton para ocultar el orden
            // echo "<a href='repartos.php?action=oce&idlt={$lista->getId()}' class='btn btn-primary mr-2 btn-sm'><i class='fas fa-eye-slash mr-1'></i></i>Ocultar orden</a>\n";
            // 71) Boton para borrar la lista de tareas. Le añadimos un mensaje de confirmacion
            echo "<a href='repartos.php?action=blt&idlt={$lista->getId()}' class='btn btn-danger btn-sm' onclick=\"return confirm('¿Borrar Lista?')\"><i class='fas fa-trash mr-1'></i>Borrar</a>\n";
            // 72) Cerramos los elementos de cabecera y abrimos el 'tbody' o cuerpo de la tabla
            echo "</th></tr>\n";
            echo "</thead>\n";
            echo "<tbody style='font-size:0.8rem'>\n";

            // 73) Ahora llamamos a la función para obtener las tareas de cada lista, pasandole el id de la lista de tareas
            $tareas = getTareas($lista->getId());
            // 74) Iteramos sobre la lista
            foreach ($tareas->getItems() as $tarea) {
                // 75) Dividimos el contenido de la propiedad 'getNotes', esto divide en un array con la coma como separador
                $c   = explode(",", $tarea->getNotes());
                // 76) Asignamos el primer elemento del array a $lat (latitud)
                $lat = $c[0];
                // 77) Si el array $c tiene al menos dos elementos, asignamos el segundo a $lon (longitud)
                $lon = count($c) < 2 ? "" : $c[1];
                // 78) Imprimimos nueva fila y mostraremos el titulo de la tarea y las notas asociadas a la misma
                echo "<tr>\n";
                echo "<th scope='row'>{$tarea->getTitle()} ({$tarea->getNotes()})\n";
                // 79) Almacenamos en un campo oculto las notas de la tarea
                echo "<input type='hidden' value='{$tarea->getNotes()}'></th>\n";
                // 80) A la derecha mostramos una nueva celda con el boton para borrar una tarea especifica pasandole el id de la lista y el de la tarea
                echo "<th scope='row' class='text-right'>\n<a href='repartos.php?action=bt&idlt={$lista->getId()}&idt={$tarea->getId()}' class='btn btn-danger btn-sm' onclick=\"return confirm('¿Borrar Tarea?')\">";
                echo "<i class='fas fa-trash mr-1'></i>Borrar</a>\n";
                // 81) Boton para ver el mapa que nos lleva al fichero 'mapas.php'
                echo "<a href='mapas.php?lat=$lat&lon=$lon' class='btn btn-info ml-2 btn-sm'><i class='fas fa-map mr-1'></i>Mapa</a>\n</th>\n";
                echo "</tr>\n";
            }
            // 82) Cerramos tanto el cuerpo de la tabla, como la tabla
            echo "</tbody>\n";
            echo "</table>\n";
            // CODIGO NUEVO
            echo "<div id='" . "lista_" . $lista->getId() . "'> </div>";

            /* ************* TODO ESTO TAMBIÉN LO PASAMOS AL NUEVO FICHERO 'repartosAux.php'

            // 83) Verificamos que existe la variable de sesion con el id de la lista de tareas
            if (isset($_SESSION[$lista->getId()])) {
                // 84) Imprimimos un formulario cuyo action nos llevará al fichero php restante, 'rutas.php'
                echo "<form name='1' action='rutas.php' method='POST'>";
                // 85) Almacenamos en un campo oculto el primer waypoint, que será el almacen.
                echo "<input type='hidden' name='wp[]' value=$corAlmacen>";
                echo "<div class='container mt-2 mb-2' style='font-size:0.8rem'>";
                // 86) Imprimimos una lista
                echo "<ul class='list-group'>";
                // 87) Iteramos sobre cada variable de sesion que contenga el id de una lista de tareas
                foreach ($_SESSION[$lista->getId()] as $k => $v) {
                    // 88) Por cada iteración, mostramos el elemento y su índice
                    echo "<li class='list-group-item list-group-item-info'>" . ($k + 1) . ".- " . $v . "</li>\n";
                    // 89) También agregamos un campo oculto adicional que almacena el contenido de las notas
                    echo "<input type='hidden' name='wp[]' value='" . getNotes($lista->getId(), $v) . "'>\n";
                }
                // 90) El último waypoint volverá a ser el almacen.
                echo "<input type='hidden' name='wp[]' value=$corAlmacen>";
                echo "<p class='text-center mt-2'>\n";
                // 91) Incluimos el boton para ver la ruta en un mapa
                echo "<button type='submit' class='btn btn-info btn-sm'><i class='fas fa-route mr-1'></i>Ver Ruta en Mapa</button>\n";
                echo "</div>";
                echo "</form>";
            } */
        }
        ?>
    </div>
</body>
<?php
// 92) Inyectamos los scripts javascript antes de enviar la página (como en los ejemplos):
$jaxon = jaxon();
echo $jaxon->getCss(), "\n", $jaxon->getJs(), "\n", $jaxon->getScript(), "\n";
echo "<!-- HTTP comment  -->\n"
?>
</html>