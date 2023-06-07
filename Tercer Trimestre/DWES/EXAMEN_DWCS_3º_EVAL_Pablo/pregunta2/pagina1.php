<?php
session_start();

echo "HOLAAA" . "<br>";

echo "usuario: " . $_SESSION['nombre'];
echo "FIN";