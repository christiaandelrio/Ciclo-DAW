@extends('plantillas.plantilla1')
@section('titulo')
{{$titulo}}
@endsection
@section('encabezado')
{{$encabezado}}
@endsection
@section('contenido')

<div class="container-fluid" style="width:100%;background-color: #1e3222;padding:10px">
    @if (isset($mensaje))
    <div class="alert alert-success">
        <p>{{ $mensaje }}</p>
    </div>
    @endif

    <div class="container mt-5">
        <button type="submit" class="btn btn-success"
            style="margin-bottom: 1em;background-color:   #bdecb6 ; border-color:  #ffff ;"><a href="fcrear.php"
                style="color: #060404;appearance: button;text-decoration: none;"><i class="fa fa-plus"></i> Nuevo
                jugador</a></button>

        <table class="table table-dark table-striped">
            <thead>
                <tr class="text-center">
                    <th scope="col">Nombre Completo</th>
                    <th scope="col">Posición</th>
                    <th scope="col">Dorsal</th>
                    <th scope="col">Código de barras</th>
                </tr>
            </thead>
            <tbody>
                @foreach($jugadores as $jugador)
                <tr class="text-center">
                    <td scope="row">{{$jugador->apellidos.", ".$jugador->nombre}}</td>
                    <td>{{$jugador->posicion}}</td>
                    @if(isset($jugador->dorsal))
                    <td>{{$jugador->dorsal}}</td>
                    @else
                    <td>Sin Asignar</td>
                    @endif
                    <td class="d-flex justify-content-center">@php echo $d->getBarcodeHTML($jugador->barcode, 'EAN13',2,33,
                        'white') @endphp</td>
                </tr>
                @endforeach
            </tbody>

        </table>
    </div>
</div>

@endsection