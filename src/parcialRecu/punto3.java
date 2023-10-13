package parcialRecu;

import jerarquicas.ArbolBin;

/**
 *
 * @author Fran
 */
public class punto3 {

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        /*
        arbol.insertar(14, null, 'i');
        arbol.insertar(4, 14, 'i');
        arbol.insertar(7, 4, 'i');
        arbol.insertar(9, 4, 'd');
        arbol.insertar(7, 9, 'i');
        arbol.insertar(5, 7, 'i');
        arbol.insertar(4, 5, 'i');
        arbol.insertar(5, 5, 'd');
        arbol.insertar(15, 14, 'd');
        arbol.insertar(5, 15, 'i');
        arbol.insertar(18, 15, 'd');
        arbol.insertar(16, 18, 'i');
        arbol.insertar(7, 16, 'd');
        arbol.insertar(15, 18, 'd');
        */
        arbol.insertar(7,null, 'i');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'D');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'i');
        arbol.insertar(7, 7, 'i');
        
        System.out.println("Arbol: " + arbol.toString());
        System.out.println("Apariciones: " + arbol.menosDeCantApariciones(7, 5));
    }
    
}
