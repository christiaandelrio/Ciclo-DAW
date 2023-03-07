//DEFINICION DEL OBJETO EDIFICIO
class Edificio {

    constructor(nombreEdificio,calle, numero, cp, numplantas, puertas){
        this.nombreEdificio = nombreEdificio;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.plantas = [];  //Creo un array que contendrá las plantas con sus puertas

        resultados.innerHTML +="<li>Construido nuevo edificio en calle: "+ this.calle +" nº: "+ this.numero+ " , CP: "+ this.cp+".</li><br>";
    }


    agregarPlantasYPuertas(plantas, puertas){

        let plantasNuevas = plantas;
        let puertasNuevas = puertas;
        let ultimaPlanta = this.plantas.length;

        if(this.plantas.length==0){
            ultimaPlanta = 1;
        }else{
            ultimaPlanta = this.plantas.length;
        }

        for(let i=ultimaPlanta;i<ultimaPlanta+plantasNuevas;i++){  //Así comienza a agregar desde la última planta
            this.plantas[i]= [];

            for(let j=1;j<puertas+1;j++){
                this.plantas[i][j]="";
            }
        }

        resultados.innerHTML +="<li>Agregamos "+plantas+" plantas y "+puertas+" puertas al edificio "+this.nombreEdificio+"</li><br>";
    }
    
    agregarPropietario(nombre,planta,puerta){

        this.plantas[planta][puerta] = nombre;
        
        resultados.innerHTML +="<li>"+this.plantas[planta][puerta]+" es ahora el propietario de la puerta "+puerta+" en la planta "+planta+"</li><br>";
    }

    modificarNumero(numero){
        this.numero = numero;
    }

    modificarCalle(calle){
        this.calle = calle;
    }

    modificarCodigoPostal(codigo){
        this.cp = codigo;
    }

    imprimeCalle(){
        return this.calle;
    }

    imprimeNumero(){
        return this.numero;
    } 

    imprimeCodigoPostal(){
        return this.cp;
    } 

    imprimePlantas(){
        resultados.innerHTML +="<p>Listado de propietarios del edificio "+this.calle+" nº: " +this.numero+"</p><br>";

        for (let i=1;i<this.plantas.length;i++){
           for(let j=1;j<this.plantas[i].length;j++){
                resultados.innerHTML +="<li>Propietario del piso "+j+" de la planta "+i+" : "+this.plantas[i][j]+"</li><br>";
           }
        }
    }
}

const resultados = document.getElementById('resultados');

//Resultados

edificioA = new Edificio("A","Garcia Prieto",58,15706)
edificioB = new Edificio("B","Camino Caneiro",29,32004)
edificioC = new Edificio("C","San Clemente","s/n",15705)


resultados.innerHTML +="<li>El código postal del edificio A es:"+edificioA.imprimeCodigoPostal()+".</li><br>";
resultados.innerHTML +="<li>La calle del edificio C es: "+edificioC.imprimeCalle()+".</li><br>";
resultados.innerHTML +="<li>El edificio B está situado en la calle "+edificioB.imprimeCalle()+" número "+edificioB.imprimeNumero()+"</li><br>";


edificioA.agregarPlantasYPuertas(2,3);

edificioA.agregarPropietario("Jose Antonio Lopez",1,1);
edificioA.agregarPropietario("Luisa Martinez",1,2);
edificioA.agregarPropietario("Marta Castellón",1,3);
edificioA.agregarPropietario("Antonio Pereira",2,2);


edificioA.agregarPlantasYPuertas(1,3);

edificioA.imprimePlantas();

edificioA.agregarPropietario("Pedro Meijide",3,2);

edificioA.imprimePlantas();
