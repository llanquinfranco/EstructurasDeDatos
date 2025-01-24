package testsMios;
import lineales.estaticas.Pila;

/**
 *
 * @author Fran
 */
public class testPilaEstatica {

    public static void main(String[] args) {
        Pila pila1 = new Pila();
        pila1.apilar(5);
        pila1.apilar(10);
        pila1.apilar(15);
        pila1.apilar(20);
        pila1.apilar(25);

        System.out.println("Pila original: \n" + pila1.toString());
        pila1.desapilar();
        System.out.println("Pila desapilada: \n" + pila1.toString());
        System.out.println("Tope de la pila: \n" + pila1.obtenerTope());
        System.out.println("");
        System.out.println("La pila está vacía?: \n" + pila1.esVacia());
        System.out.println("");
        //pilaxd.vaciar();
        //System.out.println("La pila está vacía? "+pilaxd.esVacia());
        System.out.println("Pila clonada: \n" + pila1.clone().toString());
    }
    
}

/*
        System.out.println(pila1.toString());
        System.out.println(pila1.esVacia());
        
        pila1.vaciar();
        System.out.println(pila1.esVacia());
        System.out.println(pila1.obtenerTope());
        System.out.println(pila1.toString());

*/
