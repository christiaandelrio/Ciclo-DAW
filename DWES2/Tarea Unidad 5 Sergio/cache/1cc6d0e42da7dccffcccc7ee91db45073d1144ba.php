<!-- Importamos la plantilla común -->

<!-- Título de la página -->
<?php $__env->startSection('titulo'); ?>
<?php echo e($titulo); ?>

<?php $__env->stopSection(); ?>
<!-- Encabezado -->
<?php $__env->startSection('encabezado'); ?>
<?php echo e($encabezado); ?>

<?php $__env->stopSection(); ?>

<!-- Contenido específico de la vista de jugadores -->
<?php $__env->startSection('contenido'); ?>
<div class="container-fluid">
	<!-- Si existe un mensaje en la sesión lo mostramos en una alerta -->
	<?php if(isset($mensaje)): ?>
	<div class="alert alert-success">
		<p><?php echo e($mensaje); ?></p>
	</div>
	<?php endif; ?>

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
				<?php $__currentLoopData = $jugadores; $__env->addLoop($__currentLoopData); foreach($__currentLoopData as $jugador): $__env->incrementLoopIndices(); $loop = $__env->getLastLoop(); ?>
				<tr class="text-center">
					<th scope="row"><?php echo e($jugador->apellidos.", ".$jugador->nombre); ?></th>
					<td><?php echo e($jugador->posicion); ?></td>
					<?php if(isset($jugador->dorsal)): ?>
					<td><?php echo e($jugador->dorsal); ?></td>
					<?php else: ?>
					<td>Sin Asignar</td>
					<?php endif; ?>
					<td class="d-flex justify-content-center">
						<?php echo $d->getBarcodeHTML($jugador->barcode,
						'EAN13',2,33, 'white') ?>
					</td>
				</tr>
				<?php endforeach; $__env->popLoop(); $loop = $__env->getLastLoop(); ?>
			</tbody>
		</table>
	</div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('plantillas.plantilla1', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>