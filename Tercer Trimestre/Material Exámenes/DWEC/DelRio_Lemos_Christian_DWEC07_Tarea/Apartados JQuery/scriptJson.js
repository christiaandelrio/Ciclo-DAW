$(document).ready(function(){
    $.ajax({
        type: "GET",//utiliza get
        url: "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0",//url de la api
        dataType: "json",//tipo de datos de la respuesta
        success: (respuesta)=>{
            $(respuesta.results).each(function(){
                let nombre = this.name;
                $("#resultados").append(nombre + "<br>");
            })
        },
        
        error: function(){
            console.error("Error al cargar la respuesta.");
        }
    });


});