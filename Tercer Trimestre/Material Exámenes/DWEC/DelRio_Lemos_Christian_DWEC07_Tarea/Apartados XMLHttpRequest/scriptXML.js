let request = new XMLHttpRequest();
        request.open('GET', 'http://restapi.adequateshop.com/api/Traveler?page=1', true);
        request.onload = function() {
            if (request.status >= 200 && request.status < 400) {
                let travelers = request.responseXML.documentElement.getElementsByTagName("Travelerinformation");
                let contenedorResultados = document.getElementById('traveler-info');

                let ulResultados = document.createElement('ul');
                contenedorResultados.appendChild(ulResultados);

                for(let i=0;i<travelers.length;i++){
                    let traveler = travelers[i];

                    let liResultados = document.createElement('li');
                    let id = traveler.getElementsByTagName("id")[0].textContent;
                    let name = traveler.getElementsByTagName("name")[0].textContent;
                    let email = traveler.getElementsByTagName("email")[0].textContent;
                    let adderes = traveler.getElementsByTagName("adderes")[0].textContent;
                    let createdat = traveler.getElementsByTagName("createdat")[0].textContent;

                    liResultados.textContent = "ID: " + id + " | Name: " + name + " | Email: " + email + " | Address: " + adderes + " | Created At: " + createdat;
                    ulResultados.appendChild(liResultados);
                }
            } else {
                console.error('Ha ocurrido un error al cargar los datos.');
            }
        };
        request.onerror = function() {
            console.error('Ha ocurrido un error al conectarse a la API.');
        };
        request.send();