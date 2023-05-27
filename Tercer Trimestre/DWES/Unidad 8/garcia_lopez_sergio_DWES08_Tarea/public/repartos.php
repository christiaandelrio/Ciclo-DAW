<?php

// repartos.php es el punto de entrada de la aplicación, se encarga de mostrar los listados de tareas procedentes de Google Tasks y de gestionar las tareas y listas de tareas.
//En el caso que nos ocupa, emplearemos una lista de tareas para gestionar repartos de una empresa de paquetería

include '../src/Tasks.php';
include '../src/config.inc.php';

require(__DIR__ . '/../vendor/autoload.php');

use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

//Opciones de depuración para ver la salida paso a paso
ob_end_flush();
ob_implicit_flush();

//FUNCIONES PARA OBTENER DATOS DE GOOGLE TASKS
//Instanciamos el cliente de Google Tasks
$service    = new Google_Service_Tasks($client);

//Función para obtener el listado de tareas desde Google Tasks
function getListasTareas() {
    global $service;

    //Opciones para obtener las listas de tareas
    $optParams = ['maxResults' => 100];
    //Obtenemos la lista de tareas
    $results   = $service->tasklists->listTasklists($optParams);

    //Devolvemos el resultado
    return $results;
}

//Obtener tareas por id desde Google Tasks
function getTareas($id) {
    global $service;

    $res1 = $service->tasks->listTasks($id);

    return $res1;
}

//Función para obtener notas de una tarea
function getNotes($id, $titulo) {
    //LLamo a la función anterior para obtener tareas por id desde Google Tasks
    $tareas = getTareas($id);

    //Recorro las tareas y devuelvo las notas de la tarea que coincida con el título
    foreach ($tareas->getItems() as $tarea) {
        if ($tarea->getTitle() == $titulo) {
            return $tarea->getNotes();
        }
    }
}

include '../src/Tools.php';

// $jaxon = jaxon();

// $jaxon->register(Jaxon::CALLABLE_FUNCTION, 'ordenarEnvios');

// if($jaxon->canProcessRequest())  $jaxon->processRequest();


?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Repartos</title>
    <!-- Cargamos las funciones de javascript del archivo funciones.js a las que se va a llamar desde el formulario -->
    <script src="../js/funciones.js"></script>
</head>

<body style="background:#00bfa5;">
    <?php

    //En el siguiente código vamos a entrar cuando se crea una nueva tarea desde envio.php, cuyo fomulario tiene uno de sus campos correspondiente a la latitud 'lat'.
    if (isset($_POST['lat'])) {
        //Creamos una nota a partir de los datos (latitud y longitud) enviados por post desde envio.php: 'latitud,longitud'
        $note  = $_POST['lat'] . "," . $_POST['lon'];
        //Creamos un título a partir de los datos (producto y dirección) enviados por post desde envio.php: 'producto. direccion'. ucwords() convierte la primera letra de cada palabra en mayúscula
        $title = ucwords($_POST['pro']) . ". " . ucwords($_POST['dir']);
        //Capturamos el id de la lista de tareas
        $idLt  = $_POST['idLTarea'];

        //Borramos el idLt de la sesión
        unset($_SESSION[$idLt]);
        
        //Creamos un array asociativo que se define con los campos title (producto. direccion) y note (latitud,longitud)
        $op    = ['title' => $title, 'notes' => $note];
        //Creamos una neuva tarea instanciando un task de Google Tasks pasándole como parámetro el array asociativo anterior
        $tarea = new Google_Service_Tasks_Task($op);

        try {
            //Guardamos la tarea en Google Tasks pasándole como parámetros el id de la lista de tareas y la tarea
            $res = $service->tasks->insert($idLt, $tarea);
        } catch (Google_Exception $ex) {
            die("Error al guardar la tarea: " . $ex);
        }

        //Reiniciamos el valor del post vaciando el campo lat
        unset($_POST['lat']);
    }

    //Analizamos el action del formulario (será diferente en función del botón que pulsemos). Cada vez que hacemos un envío por get va a capturar el action y se va a realizar una acción determinada (borrar tareas, crear nueva lista, etc.)
    if (isset($_GET['action'])) {
        switch ($_GET['action']) {
            //Si el action es 'blt' borramos la lista de tareas
            case 'blt':     // borrar lista tareas
                try {
                    //Borramos la lista de tareas de Google Tasks por id de lista de tareas
                    $service->tasklists->delete($_GET['idlt']);
                } catch (Google_Exception $ex) {
                    die("Error al borrar la lista de tareas: " . $ex);
                }
                unset($_SESSION[$_GET['idlt']]);
                break;
                //Si el action es 'bt' borramos la tarea
            case 'bt':     // borrar tarea
                try {
                    //Borrar una tarea de Google Tasks por id de lista de tareas y por id de tarea
                    $service->tasks->delete($_GET['idlt'], $_GET['idt']);
                } catch (Google_Exception $ex) {
                    die("Error al borrar la tarea: " . $ex);
                }
                unset($_SESSION[$_GET['idlt']]);
                break;
                //Si el action es 'nlt' creamos una nueva lista de tareas
            case 'nlt':   // nueva lista tareas
                $fecha  = new DateTime($_GET['title']);
                $fecha1 = date_format($fecha, "d/m/Y");
                $titulo = "Repartos " . $fecha1;

                //Validamos que no exista la lista de tareas en Google Tasks
                if (!existeLista($titulo)) {
                    $opciones = ["title" => $titulo];
                    //Si no existe la lista de tareas la creamos y la guardamos
                    $taskList = new Google_Service_Tasks_TaskList($opciones);
                    try {
                        $service->tasklists->insert($taskList);
                    } catch (Google_Exception $ex) {
                        die("Error al crear una lista de tareas: " . $ex);
                    }
                } else {
                    echo "<script>alert('Ya existe un reparto para ese día !!!');</script>";
                }
                break;
                //Si el action es 'oEnvios' ordenamos los envios
            case 'oEnvios':     // ordenar envios
                if (!isset($_GET['pos'])) { 

                }
                
                $apos     = $_GET['pos'];
                $id_lista = $_GET['idLt'];

                unset($_SESSION['idLt']);
                
                //Obtenemos todas las tareas de esta lista de tareas
                $tareas = getTareas($id_lista);
                foreach ($apos as $k => $v) {
                    //los envios me los manda ordenados del 1 al n
                    //en php los array empiezan por cero, por eso restamos 1 
                    //asi el envio 1 pasa a ser el 0, el 2 el 1 ...
                    $p = $v - 1;
                    $arrayO[$k] = $tareas->getItems()[$p]->getTitle();
                }

                $_SESSION[$id_lista] = $arrayO;
                break;
                //Si el action es 'oce' ocultamos orden
            case 'oce':
                $lista = $_GET['idlt'];
                //Borra la lista de tareas de la sesion
                unset($_SESSION[$lista]);
        }
    }

    //Función para validar si la lista ya existe
    function existeLista($l)  {
        $listas = getListasTareas();
        foreach ($listas->getItems() as $lista) {
            if ($lista->getTitle() == $l) return true;
        }
        return false;
    }

?>
    <!-- Dibujar título de la página "Gestión de Pedidos   " -->
    <h4 class="text-center mt-3">Gestión de Pedidos</h4>

    <!-- Contenedor -->
    <div class="container mt-4" style='width:80rem;'>

    <!-- Formulario para crear una nueva lista de reparto. Al hacer submit hace una validación llamando a la función validarFecha que está en funciones.js y ejecuta el código de esta misma página-->
        <form action='<?php echo $_SERVER['PHP_SELF'] ?>' method='get' onsubmit="return validarFecha();">
            <div class="row">
                <!-- Botón de submit del formulario de creación de lista de tareas -->
                <div class="col-md-3 mb-2">
                    <button type='submit' class="btn btn-info"><i class='fas fa-plus mr-1'></i>Nueva Lista de Reparto</button>
                </div>
                <!-- Campo de texto oculto que indica que name 'action' es 'nlt' (nueva lista de tareas) -->
                <input type='hidden' name='action' value='nlt'>
                <!-- Input de tipo fecha que nos permite seleccionar la fecha de reparto -->
                <div class="col-md-4">
                    <input type='date' class="form form-control" id="title" name="title" required>
                </div>
            </div>
        </form>

        <!-- Dibujar las listas de tareas -->
        <?php
        // Obtenemos las listas de tareas. getListasTareas() es una función definida en este mismo fichero y que se encarga de obtener las listas de tareas de Google Tasks
        $listas = getListasTareas();

        // Recorremos y dibujamos las listas de tareas
        foreach ($listas->getItems() as $lista) {
            if ($lista->getTitle() == "My Tasks" || $lista->getTitle() == "Mis tareas") continue;
            // ID de la lista de tareas
            echo "<table class='table mt-2' id='t_{$lista->getId()}'>\n";
            echo "<thead class='bg-secondary'>\n";
            echo "<tr>\n";
            //Título de la lista de tareas
            echo "<th scope='col' style='width:42rem;'>{$lista->getTitle()}</th>\n";
            echo "<th scope='col' class='text-right'>\n";
            //Botón que llama a envio.php que pasa por parámetro de la url el id de la lista de tareas. envio.php se encarga de crear una nueva tarea
            echo "<a href='envio.php?id={$lista->getId()}' class='btn btn-info mr-2 btn-sm'><i class='fas fa-plus mr-1'></i>Nuevo</a>\n";
            //Botón que llama a la función ordenarEnvios(id), que está definida en funciones.js, pasándole como parámetro el id de la lista de tareas
            echo "<button class='btn btn-success mr-2 btn-sm' onclick=\"ordenarEnvios('{$lista->getId()}')\"><i class='fas fa-sort mr-1'></i>ordenar</button>\n";
            //Botón que llama a repartos.php pasándole por parámetro de la url el id de la lista de tareas y el action 'oce' (ocultar orden)
            echo "<a href='repartos.php?action=oce&idlt={$lista->getId()}' class='btn btn-primary mr-2 btn-sm'><i class='fas fa-eye-slash mr-1'></i></i>Ocultar orden</a>\n";
            //Botón que llama a repartos.php pasándole por parámetro de la url el id de la lista de tareas y el action 'blt' (borrar lista de tareas)
            echo "<a href='repartos.php?action=blt&idlt={$lista->getId()}' class='btn btn-danger btn-sm' onclick=\"return confirm('¿Borrar Lista?')\"><i class='fas fa-trash mr-1'></i>Borrar</a>\n";
            echo "</th></tr>\n";
            echo "</thead>\n";
            echo "<tbody style='font-size:0.8rem'>\n";

            //Obtener las tareas de cada lista:
            $tareas = getTareas($lista->getId());

            //Dibujar las tareas de cada lista:
            foreach ($tareas->getItems() as $tarea) {
                //Cogemos las notas y con explode() las separamos por comas guardando en un array
                $c   = explode(",", $tarea->getNotes());
                //Latitud
                $lat = $c[0];
                //Longitud
                $lon = count($c) < 2 ? "" : $c[1];
                echo "<tr>\n";
                //Título de la tarea y notas
                echo "<th scope='row'>{$tarea->getTitle()} ({$tarea->getNotes()})\n";
                //Campo oculto con la nota
                echo "<input type='hidden' value='{$tarea->getNotes()}'></th>\n";
                //Botón que llama a repartos.php pasándole por parámetro de la url el id de la lista de tareas, el id de la tarea y el action 'bt' (borrar tarea)
                echo "<th scope='row' class='text-right'>\n<a href='repartos.php?action=bt&idlt={$lista->getId()}&idt={$tarea->getId()}' class='btn btn-danger btn-sm' onclick=\"return confirm('¿Borrar Tarea?')\">";
                echo "<i class='fas fa-trash mr-1'></i>Borrar</a>\n";
                // Botón que llama a mapas.php pasándole por parámetro de la url la latitud y longitud de la tarea. mapas.php se encarga de mostrarnos la ubicación del reparto en un mapa de Bing Maps
                echo "<a href='mapas.php?lat=$lat&lon=$lon' class='btn btn-info ml-2 btn-sm'><i class='fas fa-map mr-1'></i>Mapa</a>\n</th>\n";
                echo "</tr>\n";
            }

            echo "</tbody>\n";
            echo "</table>\n";

            // Formulario para el cálculo de la ruta:
            if (isset($_SESSION[$lista->getId()])) {
                //El formulario envía los datos por post a rutas.php
                echo "<form name='1' action='rutas.php' method='POST'>";

                //el primer waypoint de la ruta será el almacen. Campo oculto para guardar las coordenadas del almacén (punto inicial de la ruta)
                echo "<input type='hidden' name='wp[]' value=$corAlmacen>";
                echo "<div class='container mt-2 mb-2' style='font-size:0.8rem'>";
                echo "<ul class='list-group'>";

                //Cuando pulsamos el botón "ordenar" de la lista de tareas, se nos mostrará la lista de tareas ordenados por la ruta y el botón para "ver ruta en mapa"
                //Cogemos de la sesión la lista de tareas por su id y lo recorremos obteniendo los puntos intermedios (puntos de reparto) de la ruta
                foreach ($_SESSION[$lista->getId()] as $k => $v) {
                    echo "<li class='list-group-item list-group-item-info'>" . ($k + 1) . ".- " . $v . "</li>\n";
                    echo "<input type='hidden' name='wp[]' value='" . getNotes($lista->getId(), $v) . "'>\n";
                }
                //El último waypoint volverá a ser el almacen.
                echo "<input type='hidden' name='wp[]' value=$corAlmacen>";
                echo "<p class='text-center mt-2'>\n";
                //Botón de submit que hace que se envíen los datos a rutas.php por post
                echo "<button type='submit' class='btn btn-info btn-sm'><i class='fas fa-route mr-1'></i>Ver Ruta en Mapa</button>\n";
                echo "</div>";
                echo "</form>";
            }
        }
        ?>
    </div>
</body>
<?php
//  (JAXON) Injectamos los scripts javascript antes de enviar la página:
$jaxon = jaxon();
echo $jaxon->getCss(), "\n", $jaxon->getJs(), "\n", $jaxon->getScript(), "\n";
echo "<!-- HTTP comment  -->\n"
?>
</html>