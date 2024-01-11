
/**
 * Aplicación ejemplo de tipos de variables
 
 *
 * @author FMA
 */
public class ejemplovariables {
    final double PI =3.1415926536;  // PI es una constante
    int x;                          // x es una variable miembro
                                    // de clase ejemplovariables

    int obtenerX(int x) {               // x es un parámetro
            int valorantiguo = this.x;  // valorantiguo es una variabe local
            return valorantiguo;
    }

    // el método main comienza la ejecución de la aplicación
    public static void main(String[] args) {
        // aquí iría el código de nuestra aplicación

        
    } // fin del método main

} // fin de la clase ejemplovariables

