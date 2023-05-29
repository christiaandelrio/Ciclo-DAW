<?php


 function autoload_3094128f773ec93ac6a6c7b01dbbc13a($class)
{
    $classes = array(
        'Clases\Clases1\ClasesOperacionesService' => __DIR__ .'/ClasesOperacionesService.php'
    );
    if (!empty($classes[$class])) {
        include $classes[$class];
    };
}

spl_autoload_register('autoload_3094128f773ec93ac6a6c7b01dbbc13a');

// Do nothing. The rest is just leftovers from the code generation.
{
}
