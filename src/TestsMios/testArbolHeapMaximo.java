package testsMios;

import conjuntistas.HeapMax;

/**
 *
 * @author Fran
 */
public class testArbolHeapMaximo {

    public static void main(String[] args) {
        HeapMax arbol = new HeapMax();
        System.out.println("Arbol vacio?: " + arbol.toString());
        arbol.insertar(1);
        arbol.insertar(17);
        arbol.insertar(19);
        arbol.insertar(7);
        arbol.insertar(25);
        arbol.insertar(2);
        arbol.insertar(36);
        arbol.insertar(3);
        arbol.insertar(100);
        System.out.println("Arbol Heap Maximo: " + arbol.toString());
        System.out.println("Cima del Arbol: " + arbol.recuperarCima());
        arbol.eliminarCima();
        System.out.println("Arbol Heap Maximo: " + arbol.toString());
        System.out.println("Cima del Arbol: " + arbol.recuperarCima());
        HeapMax arbolClonado = arbol.clone();
        System.out.println("Arbol Clonado: " + arbolClonado.toString());
    }
    
}
