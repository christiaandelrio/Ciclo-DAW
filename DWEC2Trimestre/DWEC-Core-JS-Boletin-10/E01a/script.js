function factorial1(numero) {
    let factorial = 1n;

    for (let i = numero; i > 1n; i--) {
        factorial *= i;
    }
    return factorial;
}

function factorial2(numero) {
    if (numero === 0n || numero === 1n) {
        return 1n;
    } else {
        return numero * factorial2(numero - 1n);
    }
}


// Factorial de 5
let fac1        = document.getElementById('fac1_5');
let tiempo_fac1 = document.getElementById('t_fac1_5');

console.time('fac1_5');
let tinicio   = performance.now();
let  resultado = factorial1(5n);
let tfinal    = performance.now();
console.timeEnd('fac1_5');


fac1.textContent = resultado;
tiempo_fac1.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;


let fac2        = document.getElementById('fac2_5');
let tiempo_fac2 = document.getElementById('t_fac2_5');

console.time('fac2_5');
tinicio   = performance.now();
resultado = factorial2(5n);
tfinal    = performance.now();
console.timeEnd('fac2_5');


fac2.textContent = resultado;
tiempo_fac2.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;

// Factorial de 7
fac1        = document.getElementById('fac1_7');
tiempo_fac1 = document.getElementById('t_fac1_7');

console.time('fac1_7');
tinicio   = performance.now();
resultado = factorial1(7n);
tfinal    = performance.now();
console.timeEnd('fac1_7');


fac1.textContent = resultado;
tiempo_fac1.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;


fac2        = document.getElementById('fac2_7');
tiempo_fac2 = document.getElementById('t_fac2_7');

console.time('fac2_7');
tinicio   = performance.now();
resultado = factorial2(7n);
tfinal    = performance.now();
console.timeEnd('fac2_7');


fac2.textContent = resultado;
tiempo_fac2.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;

// Factorial de 10
fac1        = document.getElementById('fac1_10');
tiempo_fac1 = document.getElementById('t_fac1_10');

console.time('fac1_10');
tinicio   = performance.now();
resultado = factorial1(10n);
tfinal    = performance.now();
console.timeEnd('fac1_10');


fac1.textContent = resultado;
tiempo_fac1.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;


fac2        = document.getElementById('fac2_10');
tiempo_fac2 = document.getElementById('t_fac2_10');

console.time('fac2_10');
tinicio   = performance.now();
resultado = factorial2(10n);
tfinal    = performance.now();
console.timeEnd('fac2_10');


fac2.textContent = resultado;
tiempo_fac2.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;


// Factorial de 20
fac1        = document.getElementById('fac1_20');
tiempo_fac1 = document.getElementById('t_fac1_20');

console.time('fac1_20');
tinicio   = performance.now();
resultado = factorial1(20n);
tfinal    = performance.now();
console.timeEnd('fac1_20');


fac1.textContent = resultado;
tiempo_fac1.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;


fac2        = document.getElementById('fac2_20');
tiempo_fac2 = document.getElementById('t_fac2_20');

console.time('fac2_20');
tinicio   = performance.now();
resultado = factorial2(20n);
tfinal    = performance.now();
console.timeEnd('fac2_20');

fac2.textContent = resultado;
tiempo_fac2.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;



// Factorial de 50
fac1        = document.getElementById('fac1_50');
tiempo_fac1 = document.getElementById('t_fac1_50');

console.time('fac1_50');
tinicio   = performance.now();
resultado = factorial1(50n);
tfinal    = performance.now();
console.timeEnd('fac1_50');

fac1.textContent = resultado;
tiempo_fac1.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;


fac2        = document.getElementById('fac2_50');
tiempo_fac2 = document.getElementById('t_fac2_50');

console.time('fac2_50');
tinicio   = performance.now();
resultado = factorial2(50n);
tfinal    = performance.now();
console.timeEnd('fac2_50');

fac2.textContent = resultado;
tiempo_fac2.textContent = `tF = ${tfinal} tI= ${tinicio} -> ${tfinal - tinicio}`;

/*
// Factorial de 100
fac1        = document.getElementById('fac1_100');
tiempo_fac1 = document.getElementById('t_fac1_100');

console.time('fac1_100');
tinicio   = performance.now();
resultado = factorial1(100n);
tfinal    = performance.now();
console.timeEnd('fac1_100');

fac1.textContent = resultado;
tiempo_fac1.textContent = `${tfinal - tinicio}`;


fac2        = document.getElementById('fac2_100');
tiempo_fac2 = document.getElementById('t_fac2_100');

console.time('fac2_100');
tinicio   = performance.now();
resultado = factorial2(100n);
tfinal    = performance.now();
console.timeEnd('fac2_100');

fac2.textContent = resultado;
tiempo_fac2.textContent = `${tfinal - tinicio}`;

*/