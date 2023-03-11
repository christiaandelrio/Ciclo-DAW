<!-- Esta vista se será la empleada para crear los datos de ejemplo "instalacion.php" -->

<!-- Cargamos la plantilla común a todas las vistas-->

<!-- Título de la página -->
<?php $__env->startSection('titulo'); ?>
<?php echo e($titulo); ?>

<?php $__env->stopSection(); ?>
<!-- Encabezado -->
<?php $__env->startSection('encabezado'); ?>
<?php echo e($encabezado); ?>

<?php $__env->stopSection(); ?>

<!-- Contenido específico de la vista de instalación -->
<?php $__env->startSection('contenido'); ?>

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
<?php $__env->stopSection(); ?>

<?php echo $__env->make('plantillas.plantilla1', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>