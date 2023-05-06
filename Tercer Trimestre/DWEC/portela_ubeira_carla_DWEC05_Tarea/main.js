//Inicializamos el contador de intentos
let contador=0; 

//Definimos la función que se iniciará cada vez que se cargue la ventana
const iniciar = () =>{
    const formulario = document.getElementById("formulario");
    //Cada vez que se carga la página se inicializa la cookie, para ello tienen que coincidir todos los atributos y el nombre de la cookie
    document.cookie = `intentos=${contador}; max-age=0; path=/`;
    //Almacenar en una cookie el número de intentos de envío del formulario que se van produciendo
    document.cookie = `intentos=${contador}; max-age=3600; path=/`;
    //Para actualizar el valor actual de la cookie, usamos la funcion leerCookie
    let intentoActual=leerCookie("intentos"); 
    //Mostramos en el span de intentos el número de intentos actual guardado en la cookie
    document.getElementById("intentos").value =`${intentoActual}`;

    //Al cargase la página se pone el foco en el primer input
    document.getElementById("nombre").focus(); 
    document.getElementById("nombre").addEventListener('blur',convertirMayusculas, false);//El evento se produce cuando se cambia de campo
    document.getElementById("apellidos").addEventListener('blur',convertirMayusculas, false);
    document.getElementById("nombre").addEventListener('blur',comprobarNombre, true);
    document.getElementById("apellidos").addEventListener('blur',comprobarApellidos, true);
    document.getElementById("edad").addEventListener('blur',comprobarEdad, true);
    document.getElementById("nif").addEventListener('blur',comprobarNif, true);
    document.getElementById("email").addEventListener('blur',comprobarEmail, true);
    document.getElementById("provincia").addEventListener('blur', comprobarProvincia, true);
    document.getElementById("fecha").addEventListener('blur', comprobarFecha, true);
    document.getElementById("telefono").addEventListener('blur', comprobarTelefono, true);
    document.getElementById("hora").addEventListener('blur', comprobarHora, true);
    document.getElementById("borrar").addEventListener('click', borrar, false); //El evento se produce cuando se clica en el botón
    document.getElementById("enviar").addEventListener('click', validarEnvio, false);//El evento se produce cuando se clica en el botón
}

//Cada vez que los campos NOMBRE y APELLIDOS pierdan el foco, el contenido que se haya escrito en esos campos se convertirá a mayúsculas
const convertirMayusculas = () =>{
    let nombre=document.getElementById("nombre").value;
    document.getElementById("nombre").value=nombre.toUpperCase();
    let apellidos=document.getElementById("apellidos").value;
    document.getElementById("apellidos").value=apellidos.toUpperCase();
}

//Realizar una función que valide los campos de texto NOMBRE y APELLIDOS.
const comprobarNombre = () => {
    document.getElementById("nombre").style.color="";
    document.getElementById("errores").value="";
    let nombre=document.getElementById("nombre").value;
    let patronNombre=/[A-Za-zÑñÁáÉéÍíÓóÚú]{3,}/;
        if(!nombre.match(patronNombre) || nombre===""){
            document.getElementById("nombre").style.color="red"; 
            document.getElementById("errores").style.color="red";
            //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en los campos correspondientes.
            document.getElementById("errores").value="Por favor, introduzca un nombre válido";
            document.getElementById("nombre").focus();
        }
}
const comprobarApellidos= () => {
    document.getElementById("apellidos").style.color="";
    document.getElementById("errores").value="";
    let apellidos=document.getElementById("apellidos").value;
    let patronApellidos=/[A-Za-zÑñÁáÉéÍíÓóÚú-]{3,}\s*[A-Za-zÑñÁáÉéÍíÓóÚú-]*\s*[A-Za-zÑñÁáÉéÍíÓóÚú-]*/ //Por si ponen entre uno y tres apellidos, separados o no de espacios, sin permitir dígitos
        if(!apellidos.match(patronApellidos) || apellidos===""){
            //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo EDAD.
            document.getElementById("apellidos").style.color="red";
            document.getElementById("errores").style.color="red";
            document.getElementById("errores").value="Por favor, introduzca unos apellidos válidos";
            document.getElementById("apellidos").focus();
        }
}

//Validar la EDAD que contenga solamente valores numéricos y que esté en el rango de 0 a 105. 
const comprobarEdad = () => {
    document.getElementById("edad").style.color="";
    document.getElementById("errores").value="";
    let edad=document.getElementById("edad").value;
    let patron=/^[1-9]{1}[0-9]{0,1}[0-5]{0,1}$/;//Tiene que empezar por un numero entre 0 y 9, puede estar seguido o no de otro número de entre 0 y 9 y puede o no estar seguido por otro número de entre 0 a 5
    if(!edad.match(patron) || edad==="" || (Number(edad)<0) || (Number(edad))>105) {
        //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo EDAD.
        document.getElementById("edad").style.color="red";
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="Por favor, introduzca una edad válida";
        document.getElementById("edad").focus();
    }
}

//Validar el NIF. Utilizar una expresión regular que permita solamente 8 números un guión y una letra. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo NIF. No es necesario validar que la letra sea correcta. Explicar las partes de la expresión regular mediante comentarios.
const comprobarNif = () => {
    document.getElementById("nif").style.color="";
    document.getElementById("errores").value="";
    let nif= document.getElementById("nif").value;
    let patron=/^[0-9]{8}[-][A-Z]$/;//Tiene que empezar por 8 digitos entre el 0 y el 9, seguido de un guión y finalizar en una letra mayúscula de la A a la Z.
    if(!nif.match(patron) || nif===""){
        document.getElementById("nif").style.color="red";
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="El formato del NIF debe ser 12345678-A";
        document.getElementById("nif").focus();
    }
}

//Validar el E-MAIL. Utilizar una expresión regular que nos permita comprobar que el e-mail sigue un formato correcto. 
const comprobarEmail = () => {
    document.getElementById("email").style.color="";
    document.getElementById("errores").value="";
    let email=document.getElementById("email").value;
    let patron= /^[^@]+[@][^@]+\.[a-zA-Z]{2,}$/;//Que comience por un caracter o más diferente a @, seguido de @, seguido de uno más caracteres diferentes a arroba, seguido de punto y que finaliza con al menos dos letras de la a a la z (tanto maýusculas como minúsculas)
    if(!email.match(patron) || email===""){
        //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo E-MAIL. Explicar las partes de la expresión regular mediante comentarios.
        document.getElementById("email").style.color="red";
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="El formato del email debe ser correo@ejemplo.com";
        document.getElementById("email").focus();
    }
}

//Validar que se haya seleccionado alguna de las PROVINCIAS.
const comprobarProvincia = () => {
    document.getElementById("errores").value="";
    let provincia=document.getElementById("provincia").selectedIndex;
    if(provincia==""){
        // Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo PROVINCIA.
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="Por favor elija una provincia";
        document.getElementById("provincia").focus();
    }
}

//Validar el campo FECHA utilizando una expresión regular. Debe cumplir alguno de los siguientes formatos: dd/mm/aaaa o dd-mm-aaaa. No se pide validar que sea una fecha de calendario correcta. 
const comprobarFecha = () => {
    document.getElementById("fecha").style.color="";
    document.getElementById("errores").value="";
    let fecha=document.getElementById("fecha").value;
    let patron1=/^\d{2}[/]\d{2}[/]\d{4}$/; //Tiene que empezar por dos dígitos, separados por una barra de dos dígitos, separados por una barra y finalizando con 4 dígitos
    let patron2=/^\d{2}[-]\d{2}[-]\d{4}$/; //Lo mismo que la anterior pero en lugar de separarse por una barra, esta separado por guiones
        if(!fecha.match(patron1) && (!fecha.match(patron2) || fecha==="")){
            //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo FECHA.
            document.getElementById("fecha").style.color="red";
            document.getElementById("errores").style.color="red";
            document.getElementById("errores").value="El formato de la fecha debe ser dd/mm/aaaa ó dd-mm-aaaa";
            document.getElementById("fecha").focus();
        } 
}

//Validar el campo TELEFONO utilizando una expresión regular. Debe permitir 9 dígitos obligatorios.
const comprobarTelefono = () => {
    document.getElementById("telefono").style.color="";
    document.getElementById("errores").value="";
    let tel=document.getElementById("telefono").value;
    let patron=/^\d{9}$/; //Sólo puede estar compuesto de 9 dígitos del 0-9
    if(!tel.match(patron) || tel===""){
        //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo TELEFONO.
        document.getElementById("telefono").style.color="red";
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="El formato del teléfono debe ser 012345678";
        document.getElementById("telefono").focus();
    }
}

//Validar el campo HORA utilizando una expresión regular. Debe seguir el patrón de hh:mm. 
const comprobarHora = () => {
    document.getElementById("hora").style.color="";
    document.getElementById("errores").value="";
    let hora=document.getElementById("hora").value;
    let patron= /^[0-2]\d:[0-5]\d$/;// Sólo puede comenzar por un dígito entre 0-2, seguidos de un dígito entre 0-9, seguido de :, seguido de un dígito de 0-5 y finalizando un dígito de 0-9
    let arrayHora=hora.split(':'); //Divido la cadena en un array cuyo separador es ":", y que contrendrá sólo dos elementos.
    let horas=Number(arrayHora[0]); //Conversión de la cadena a número para validación, que correspondería a las horas
    let minutos=Number(arrayHora[1]);//Conversión de la cadena a número para validación, que correspondería a los minutos
    if(!hora.match(patron) || hora===""){
        //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo HORA.
        document.getElementById("hora").style.color="red";
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="El formato de la hora debe ser 00:00";
        document.getElementById("hora").focus();
    } else if(horas<0 || horas >23 || minutos<0 || minutos>59){
        //Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo HORA.
        document.getElementById("hora").style.color="red";
        document.getElementById("errores").style.color="red";
        document.getElementById("errores").value="Introduzca una hora válida";
        document.getElementById("hora").focus();
    }
}

//Si se clica en el botón de borrar, se borrarán todos los campos del formulario
const borrar = () => {
    document.getElementById("errores").value="";
    document.getElementById("nombre").style.color="";
    document.getElementById("nombre").value="";
    document.getElementById("apellidos").style.color="";
    document.getElementById("apellidos").value="";
    document.getElementById("edad").style.color="";
    document.getElementById("edad").value="";
    document.getElementById("nif").style.color="";
    document.getElementById("nif").value="";
    document.getElementById("email").style.color="";
    document.getElementById("email").value="";
    document.getElementById("provincia").style.color="";
    document.getElementById("provincia").value="";
    document.getElementById("fecha").style.color="";
    document.getElementById("fecha").value="";
    document.getElementById("telefono").style.color="";
    document.getElementById("telefono").value="";
    document.getElementById("hora").style.color="";
    document.getElementById("hora").value="";
    document.getElementById("nombre").focus();
}

//Si se clica en botón de enviar
function validarEnvio (evento) {
    evento.preventDefault(); //Para evitar que si no se cumplen las validaciones y se cancela el evento, se vaya a la página de destino
    contador++;
    //Actualizamos la cookie sobreescribiéndola (Cada vez que acualizamos una cookie, se le añade al string existente, otro string separa por ";")
    document.cookie= `intentos=${contador}; max-age=3600; path=/`;
    //Para actualizar el intento actual, separamos los strings por el separador ";" y los guardamos en un array
    let intentoActual=leerCookie("intentos"); 
    //Mostramos en el span de intentos el número de intentos actual guardado en la cookie
    document.getElementById("intentos").value =`${intentoActual}`;
    //Pedir confirmación de envío del formulario. Si se confirma el envío realizará el envío de los datos; en otro caso cancelará el envío.
    let validar=confirm("Se van a enviar los datos ¿Está de acuerdo?");
    if(!validar){
        alert("No se han enviado los datos")
    } else {
    //Se envia el formulario
    formulario.submit();
    alert("Se han enviado los datos");
    }
}

//Función para mostrar el valor de la última cookie
function leerCookie(name) {

    var nameEQ = name + "="; 
    var ca = document.cookie.split(';');
  
    for(var i=0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') c = c.substring(1,c.length);
      if (c.indexOf(nameEQ) == 0) {
        return decodeURIComponent( c.substring(nameEQ.length,c.length) );
      }
    }
    return null;
}    

//Cada vez que se cargué la ventana, se llamará a la función iniciar
window.addEventListener('load', iniciar, false);



