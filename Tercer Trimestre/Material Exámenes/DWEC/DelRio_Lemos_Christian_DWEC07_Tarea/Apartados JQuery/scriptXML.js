$.ajax({
    type: "GET",
    url: "http://restapi.adequateshop.com/api/Traveler?page=1",
    dataType: "xml",
    success: (respuesta)=>{
        // Encontrar todos los elementos Travelerinformation en el documento XML
        $(respuesta).find('Travelerinformation').each(function(){
            // Obtener los datos del viajero
            var id = $(this).find('id').text();
            var name = $(this).find('name').text();
            var email = $(this).find('email').text();
            var adderes = $(this).find('adderes').text();
            var createdat = $(this).find('createdat').text();
            // Crear una nueva fila de la tabla y agregar los datos del viajero
            var row = "<tr><td>"+id+"</td><td>"+name+"</td><td>"+email+"</td><td>"+adderes+"</td><td>"+createdat+"</td></tr>";
            // Agregar la nueva fila a la tabla
            $("#travelerTable").append(row);
        });
    },
    error: function(){
        alert("Error al cargar el archivo XML.");
    }
});