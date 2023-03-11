<!-- Esta será la vista que se mostrará para introducir la información para crear un nuevo jugador -->
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

<!-- Contenido específico de la vista de crear jugador -->
@section('contenido')
<div class="container-fluid">
	<div class="container mt-5">
		<!-- Formulario para crear un nuevo jugador que enviará los datos a crearJugador.php-->
		<form method="POST" action="crearJugador.php">
			<div class="row">
				<div class="col">
					<label for="nombre">Nombre</label>
					<input
						id="nombre"
						name="nombre"
						type="text"
						class="form-control"
						placeholder="Nombre"
					/>
				</div>
				<div class="col">
					<label for="apellidos">Apellidos</label>
					<input
						id="apellidos"
						name="apellidos"
						type="text"
						class="form-control"
						placeholder="Apellidos"
					/>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="dorsal">Dorsal</label>
					<input
						id="dorsal"
						name="dorsal"
						type="number"
						class="form-control"
						placeholder="Dorsal"
						min="1"
						step="1"
						max="40"
					/>
				</div>
				<div class="col">
					<label for="posicion">Posición</label>
					<select id="posicion" name="posicion" class="form-select">
						<option value="1">Portero</option>
						<option value="2">Defensa</option>
						<option value="3">Lateral Izquierdo</option>
						<option value="4">Lateral Derecho</option>
						<option value="5">Central</option>
						<option value="6">Delantero</option>
					</select>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="barcode">Código de barras</label>
						<!-- Si el código de barras no está definido sólo muestra el placeholder -->
						@if(!isset($barcode))
						<input
							id="barcode"
							name="barcode"
							type="text"
							class="form-control"
							placeholder="Código de barras"
							readonly
						/>
						@else
						<!-- Si el código de barras está definido lo muestra en el value del input-->
						<input
							type="text"
							value="{{ $barcode }}"
							class="form-control"
							name="barcode"
							readonly
						/>
						@endif
					</div>
				</div>

				<div class="mt-3">
					<button type="submit" name="crear" class="btn btn-primary">
						Crear
					</button>
					<button type="reset" class="btn btn-success ms-2">
						Limpiar
					</button>
					<a
						href="index.php"
						class="btn btn-info ms-2 text-white text-decoration-none"
						>Volver</a
					>
					<a
						class="btn btn-secondary ms-2 text-white text-decoration-none"
						href="generarCode.php"
						><i class="fa fa-barcode"></i> Generar barcode</a
					>
				</div>
			</div>
		</form>
        <!-- Si existe un error lo muestra en una alerta -->
		@if (isset($error))
		<div class="alert alert-danger h-100 mt-3">
			<p>{{ $error }}</p>
		</div>
		@endif
	</div>
</div>
@endsection
