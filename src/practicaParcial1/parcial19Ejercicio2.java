package practicaParcial1;

import jerarquicas.ArbolBin;

/**
 *
 * @author Fran
 */
public class parcial19Ejercicio2 {

    public static void main(String[] args) {
        ArbolBin arbol1 = new ArbolBin();
        ArbolBin arbol2 = new ArbolBin();
        
        // Arbol Giuli
        arbol1.insertar(1, null, 'i');
        arbol1.insertar(2, 1, 'i');
        arbol1.insertar(4, 2, 'i');
        arbol1.insertar(5, 2, 'd');
        arbol1.insertar(6, 4, 'd');
        arbol1.insertar(3, 1, 'd');
        arbol1.insertar(7, 3, 'i');
        arbol1.insertar(8, 3, 'd');
        arbol1.insertar(9, 8, 'i');
        
        arbol2.insertar(1, null, 'i');
        arbol2.insertar(2, 1, 'i');
        arbol2.insertar(4, 2, 'i');
        arbol2.insertar(5, 2, 'd');
        arbol2.insertar(55, 4, 'd');
        arbol2.insertar(3, 1, 'd');
        arbol2.insertar(7, 3, 'i');
        arbol2.insertar(8, 3, 'd');
        arbol2.insertar(9, 8, 'i');
        
        System.out.println("Arbol 1: " + arbol1.toString());
        System.out.println("Arbol 2: " + arbol2.toString());
        
        System.out.println("Son iguales?: " + arbol1.equals(arbol2));
        
        
    }
    
}
