let request = new XMLHttpRequest();
request.open('GET', 'https://pokeapi.co/api/v2/pokemon/gengar', true);
request.onload = function() {
    if (request.status >= 200 && request.status < 400) {
        let json = JSON.parse(request.responseText); //Guardamos los datos
        let name = json.name.toUpperCase(); //Almacenamos el nombre
        
        let liName = document.createElement('li');//Creamos el li 
        liName.textContent = "Nombre: " + name;//Añadimos el nombre
        document.getElementById('pokemon-info').appendChild(liName);

        //Mostrar los tipos
        let types = json.types;
        let ulTypes = document.createElement('ul');
        ulTypes.textContent = 'Tipos: '
        document.getElementById('pokemon-info').appendChild(ulTypes);

        for(let i=0;i<types.length;i++){
            let liType = document.createElement('li');
            liType.textContent = types[i].type.name.toUpperCase();
            ulTypes.appendChild(liType);
        }

        //Mostrar las habilidades
        let abilities = json.abilities;
        let ulAbilities = document.createElement('ul');
        ulAbilities.textContent = 'Habilidades: ';
        document.getElementById('pokemon-info').appendChild(ulAbilities);

        for(let i=0;i<abilities.length;i++){
            let liAbility = document.createElement('li');
            liAbility.textContent = abilities[i].ability.name.toUpperCase();
            ulAbilities.appendChild(liAbility);
        }

    } else {
        console.error('Ha ocurrido un error al cargar los datos.');
    }
};
request.onerror = function() {
    console.error('Ha ocurrido un error al conectarse a la API.');
};
request.send();