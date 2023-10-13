package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

/**
 *
 * @author Fran
 */
public class PruebaCola {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        System.out.println("Cola c1: " + c1.toString());
        System.out.println("Cola Generada: " + generarOtraCola(c1).toString());
    }
    
    public static Cola generarOtraCola(Cola c1) {
        Cola colaClonada = c1.clone(); // para no perder la info original
        Cola colaGenerada = new Cola();
        Pila pilaAux = new Pila(); // para la secuencia invertida
        colaClonada.poner('#'); // para que tome la ultima sucesion
        while(!colaClonada.esVacia()) {
            if(!colaClonada.obtenerFrente().equals('#')) {
                colaGenerada.poner(colaClonada.obtenerFrente());
                pilaAux.apilar(colaClonada.obtenerFrente());
                colaClonada.sacar();
            } else {
                colaClonada.sacar();
                while(!pilaAux.esVacia()) {
                    colaGenerada.poner(pilaAux.obtenerTope());
                    pilaAux.desapilar();
                }
                if(!colaClonada.esVacia()) {
                    colaGenerada.poner('#');
                }
            }
        }
        return colaGenerada;
    } 
}
