package parcialRecu;

import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class punto2 {

    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertar(6, 1);
        lista.insertar(2, 2);
        lista.insertar(7, 3);
        lista.insertar(1, 4);
        lista.insertar(3, 5);
        lista.insertar(5, 6);
        System.out.println("Lista: " + lista.toString());
        lista.cambiarPosicion(5, 1);
        System.out.println("Lista: " + lista.toString());
    }
    
}
