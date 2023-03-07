@extends('plantillas.plantilla1')
@section('titulo')
{{$titulo}}
@endsection
@section('encabezado')
{{$encabezado}}
@endsection

@section('contenido')
    @if (!empty($mensaje))
        <div class="alert alert-success">
            <p>{{ $mensaje }}</p>
        </div>
    @endif
    <div class="d-flex flex-column mb-3" style="padding:50px;background-color:#1e3222;border:2px solid white">
        <form class="form-floating" method="POST" action="crearJugador.php">

            <label for="nombre" style="color: #ffffff">Nombre</label>
            <input id="nombre" name="nombre" type="text" class="form-control" placeholder="Nombre" required>
            <br>
            <label for="apellidos" style="color: #ffffff">Apellidos</label>
            <input id="apellidos" name="apellidos" type="text" class="form-control" placeholder="Apellidos" required>
            <br>
            <label for="dorsal" style="color: #ffffff">Dorsal</label>
            <input id="dorsal" name="dorsal" type="number" class="form-control" placeholder="Dorsal" min="1" max="99">
            <br>
            <label for="posicion" style="color: #ffffff">Posición</label>
            <select id="posicion" name="posicion" class="form-select">
                        <option value="1">Portero</option> 
                        <option value="2">Defensa</option>
                        <option value="3">Lateral Izquierdo</option>
                        <option value="4">Lateral Derecho</option>
                        <option value="5">Central</option>
                        <option value="6">Delantero</option>
            </select>
            <br>
            <label for="barcode" style="color: #ffffff">Código de barras</label>
            @if(!isset($barcode)) <!-- En caso de que no exista un código de barras, generamos el input vacío, en caso de haberlo generado, generamos el input con el value del barcode y de tipo readonly-->
                <input id="barcode" name="barcode" type="text" class="form-control" placeholder="Código de barras" readonly="readonly" />
            @else
                <input type="text" value="{{$barcode}}" class="form-control" name="barcode" readonly="readonly">
            @endif        
            <br><br>
            <button type="submit" name="crear" class="btn btn-primary">Crear</button>
            <button type="reset" class="btn btn-success" style="margin-left: 1em;background-color:#3db215;border-color:#3db215 ;">Limpiar</button>
            <button type="button" class="btn btn-info" style="margin-left: 1em">
                <a href="index.php" style="color: #ffffff;appearance: button;text-decoration: none;">Volver</a>
            </button>
            <button type="button" class="btn" style="background-color:#9a9a9a ;border-color: #9a9a9a ;margin-left: 1em">
                <a href="generarCode.php" style="color: #ffffff;appearance: button;text-decoration: none;">Generar barcode</a>
            </button>
        </form>
    </div>
@endsection