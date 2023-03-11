<!-- Importamos la plantilla común -->
@extends('plantillas.plantilla1')
<!-- Título de la página -->
@section('titulo')
{{ $titulo }}
@endsection
<!-- Encabezado -->
@section('encabezado')
{{ $encabezado }}
@endsection

<!-- Contenido específico de la vista de jugadores -->
@section('contenido')
<div class="container-fluid">
	<!-- Si existe un mensaje en la sesión lo mostramos en una alerta -->
	@if (isset($mensaje))
	<div class="alert alert-success">
		<p>{{ $mensaje }}</p>
	</div>
	@endif

	<div class="container mt-3">
        <!-- Botón para crear un nuevo jugador que nos lleva a fcrear.php -->
		<a
			href="fcrear.php"
			class="btn btn-success btn-md btn-block mb-3 p-2 text-center text-white text-decoration-none"
			><i class="fa fa-plus me-2"></i>Nuevo jugador</a
		>

        <!-- Tabla para mostrar el listado de todos los jugadores -->
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
                <!-- Recorremos el array de jugadores y mostramos los datos de cada uno de ellos -->
				@foreach($jugadores as $jugador)
				<tr class="text-center">
					<th scope="row">{{$jugador->apellidos.", ".$jugador->nombre}}</th>
					<td>{{$jugador->posicion}}</td>
					@if(isset($jugador->dorsal))
					<td>{{$jugador->dorsal}}</td>
					@else
					<td>Sin Asignar</td>
					@endif
					<td class="d-flex justify-content-center">
						@php echo $d->getBarcodeHTML($jugador->barcode,
						'EAN13',2,33, 'white') @endphp
					</td>
				</tr>
				@endforeach
			</tbody>
		</table>
	</div>
</div>
@endsection
