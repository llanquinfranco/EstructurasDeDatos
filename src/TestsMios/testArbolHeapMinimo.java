package TestsMios;

import conjuntistas.HeapMin;

/**
 *
 * @author Fran
 */
public class testArbolHeapMinimo {

    public static void main(String[] args) {
        HeapMin arbol = new HeapMin();
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
        System.out.println("Arbol Heap Minimo: " + arbol.toString());
        System.out.println("Cima del Arbol: " + arbol.recuperarCima());
        arbol.eliminarCima();
        System.out.println("Arbol Heap Minimo: " + arbol.toString());
        System.out.println("Cima del Arbol: " + arbol.recuperarCima());
        HeapMin arbolClonado = arbol.clone();
        System.out.println("Arbol clonado: " + arbolClonado.toString());
    }
}
