<!-- Esta vista se será la empleada para crear los datos de ejemplo "instalacion.php" -->

<!-- Cargamos la plantilla común a todas las vistas-->
@extends('plantillas.plantilla1')
<!-- Título de la página -->
@section('titulo')
{{ $titulo }}
@endsection
<!-- Encabezado -->
@section('encabezado')
{{ $encabezado }}
@endsection

<!-- Contenido específico de la vista de instalación -->
@section('contenido')

<div class="container-fluid">
	<div class="container mt-5 text-center">
		<!-- Tenemos un botón que me lleva a "crearDatos.php" que es donde se crean los datos de ejemplo. -->
		<a
			class="btn btn-success btn-lg btn-block mb-3 mt-3 p-3 text-center text-white text-decoration-none"
			href="crearDatos.php"
		>
			<i class="fa fa-database"></i> Instalar Datos de Ejemplo</a
		>
	</div>
</div>
@endsection
