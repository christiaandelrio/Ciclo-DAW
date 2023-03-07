@extends('plantillas.plantilla1')
@section('titulo')
{{$titulo}}
@endsection
@section('encabezado')
{{$encabezado}}
@endsection

@section('contenido')
<div class="container mt-5">
        <div style="text-align: center">
            <button type="submit" class="btn btn-success" style="background-color:#366959; border-color:  #ffff ; font-size: x-large;">
                <a href="crearDatos.php" style="color: #ffffff;appearance: button;text-decoration: none;">Instalar Datos de Prueba</a>
            </button>
        </div>
    </div>
@endsection