package practicaParcial1;

import lineales.dinamicas.Pila;

/**
 *
 * @author Fran
 */
public class parcial22Ejercicio2 {

    public static void main(String[] args) {
        // Hacer equals (Pila p) en el TDA
        Pila pilita = new Pila();
        Pila p = new Pila();
        
        pilita.apilar(2);
        pilita.apilar(1);
        pilita.apilar(4);
        pilita.apilar(3);
        pilita.apilar(5);
        
        p.apilar(2);
        p.apilar(1);
        p.apilar(4);
        p.apilar(3);
        
        System.out.println("Pila pilita: " + pilita.toString());
        System.out.println("Pila p: " + p.toString());
        System.out.println("Son iguales?: " + pilita.equals(p));
        
    }
    
}
