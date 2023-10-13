package parcialRecu;

import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class recu19Ejercicio1 {

    public static void main(String[] args) {
        Lista listita = new Lista();
        Lista l2 = new Lista();
        
        listita.insertar(1, 1);
        listita.insertar(3, 2);
        listita.insertar(5, 3);
        
        
        l2.insertar(2, 1);
        l2.insertar(4, 2);
        l2.insertar(6, 3);
        l2.insertar(7, 4);
        l2.insertar(8, 5);
        
        
        System.out.println("listita: " + listita.toString());
        System.out.println("l2: " + l2.toString());
        
        System.out.println("Lista intercalada: " + listita.intercalar(l2));
    }
    
}
