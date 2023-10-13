package TestsMios;
import lineales.estaticas.Cola;

/**
 *
 * @author Fran
 */
public class testColaEstatica {
    public static void main(String[] args) {
        Cola unaCola= new Cola();
        for(int i = 0; i < 9; i++){
            unaCola.poner(i);
        }
        
        System.out.println("Cola original: \n" + unaCola.toString());
        System.out.println("Frente de la Cola: \n" + unaCola.obtenerFrente());
        unaCola.sacar();
        System.out.println("Cola con el frente fuera: \n" + unaCola.toString());
        System.out.println("Frente de la Cola: \n" + unaCola.obtenerFrente());
        System.out.println("");
        System.out.println("La Cola está vacía?: " + unaCola.esVacia());
        System.out.println("");
        System.out.println("Cola clonada: \n" + unaCola.clone().toString());
        System.out.println("Vaciamos la cola.");
        unaCola.vaciar();
        System.out.println("La Cola está vacía?: "+ unaCola.esVacia());
        System.out.println("Cola vaciada: "+ unaCola.toString());
    }
}
