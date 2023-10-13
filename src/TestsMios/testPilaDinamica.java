package TestsMios;
import lineales.dinamicas.Pila;

/**
 *
 * @author Fran
 */
public class testPilaDinamica {
    // EN PILA DINAMICA, EL ENLACE ES EL ENGANCHE CON EL NODO DE ABAJO

    public static void main(String[] args) {
        Pila pilaxd = new Pila();
        pilaxd.apilar(5);
        pilaxd.apilar(10);
        pilaxd.apilar(15);
        pilaxd.apilar(20);
        pilaxd.apilar(25);

        System.out.println("Pila original: \n" + pilaxd.toString());
        pilaxd.desapilar();
        System.out.println("Pila desapilada: \n" + pilaxd.toString());
        System.out.println("Tope de la pila: \n" + pilaxd.obtenerTope());
        System.out.println("");
        System.out.println("La pila está vacía?: \n" + pilaxd.esVacia());
        System.out.println("");
        //pilaxd.vaciar();
        //System.out.println("La pila está vacía? "+pilaxd.esVacia());
        System.out.println("Pila clonada: \n" + pilaxd.clone());
    }
    
}
