package practicaParcial1;

import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class ejercicio1Simu {

    
    public static void main(String[] args) {
        Lista lista = new Lista();
        /*
        lista.insertar('A', 1);
        lista.insertar('B', 2);
        lista.insertar('C', 3);
        lista.insertar('D', 4);
        lista.insertar('E', 5);
        lista.insertar('F', 6);
        lista.insertar('G', 7);
        lista.insertar('H', 8);
        lista.insertar('I', 9);
        lista.insertar('J', 10);
        */
        
        lista.insertar('f', 1);
        lista.insertar('r', 2);
        lista.insertar('a', 3);
        lista.insertar('a', 4);
        lista.insertar('n', 5);
        lista.insertar('c', 6);
        lista.insertar('o', 7);
        lista.insertar('a', 8);
        lista.insertar('a', 9);
        lista.insertar('!', 9);
        
        System.out.println("Lista original: " + lista.toString());
        //System.out.println("Obtener multiplos de 3: " + lista.obtenerMultiplos(3));
        lista.eliminarApariciones('a');
        System.out.println("Eliminar ocurrencias de 'a': " + lista.toString());
        
        
        
    }
}
