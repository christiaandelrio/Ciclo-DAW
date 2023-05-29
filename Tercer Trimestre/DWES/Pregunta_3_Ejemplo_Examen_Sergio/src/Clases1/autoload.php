<?php


 function autoload_12a6e6384174af1c1ed025da980aea9a($class)
{
    $classes = array(
        'Clases\Clases1\ClasesOperacionesServiceCustom2' => __DIR__ .'/ClasesOperacionesServiceCustom2.php'
    );
    if (!empty($classes[$class])) {
        include $classes[$class];
    };
}

spl_autoload_register('autoload_12a6e6384174af1c1ed025da980aea9a');

// Do nothing. The rest is just leftovers from the code generation.
{
}
