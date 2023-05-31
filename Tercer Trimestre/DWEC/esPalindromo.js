function esPalindromo(palabra) {
  // Eliminar los espacios en blanco y convertir a minúsculas
  palabra = palabra.replace(/\s/g, '').toLowerCase();

  // Verificar si la palabra es un palíndromo
  const longitud = palabra.length;
  for (let i = 0; i < longitud / 2; i++) {
    if (palabra[i] !== palabra[longitud - 1 - i]) {
      return false; // No es un palíndromo
    }
  }
  return true; // Es un palíndromo
}

// Ejemplo de uso
const palabra1 = 'Anita lava la tina';
console.log(esPalindromo(palabra1)); // Imprime true

const palabra2 = 'Hola mundo';
console.log(esPalindromo(palabra2)); // Imprime false
