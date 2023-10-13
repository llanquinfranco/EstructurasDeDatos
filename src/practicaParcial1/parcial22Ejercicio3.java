package practicaParcial1;

import jerarquicas.ArbolBin;

/**
 *
 * @author Fran
 */
public class parcial22Ejercicio3 {

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        arbol.insertar('A', null, 'I');
        arbol.insertar('B', 'A', 'I');
        arbol.insertar('C', 'A', 'D');
        arbol.insertar('E', 'B', 'I');
        arbol.insertar('F', 'C', 'D');
        arbol.insertar('G', 'F', 'I');
        arbol.insertar('H', 'F', 'D');
        System.out.println("Arbol original: " + arbol.toString());
        arbol.completarHijos();
        System.out.println("Arbol modificado: " + arbol.toString());
    }
    
}
