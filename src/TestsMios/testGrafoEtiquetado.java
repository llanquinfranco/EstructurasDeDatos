package testsMios;

import grafos.GrafoEtiquetado;

/**
 *
 * @author Fran
 */
public class testGrafoEtiquetado {

    public static void main(String[] args) {
        /*
        GrafoEtiquetadoNoDir grafo = new GrafoEtiquetadoNoDir();
        grafo.insertarVertice('E');
        grafo.insertarVertice('D');
        grafo.insertarVertice('C');
        grafo.insertarVertice('B');
        grafo.insertarVertice('A');
        grafo.insertarArco('A', 'B', 6);
        grafo.insertarArco('A', 'C', 1);
        grafo.insertarArco('B', 'D', 1);
        grafo.insertarArco('B', 'C', 2);
        grafo.insertarArco('C', 'D', 2);
        grafo.insertarArco('D', 'E', 2);
        grafo.insertarArco('A', 'E', 2);
        System.out.println(grafo.toString());
        System.out.println(grafo.caminoMasCorto('A', 'E'));
        System.out.println(grafo.caminoMasLargo('A', 'E'));
        */
        
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice('E');
        grafo.insertarVertice('D');
        grafo.insertarVertice('C');
        grafo.insertarVertice('B');
        grafo.insertarVertice('A');
        grafo.insertarArco('A', 'B', 6);
        grafo.insertarArco('A', 'D', 1);
        grafo.insertarArco('B', 'E', 2);
        grafo.insertarArco('D', 'B', 2);
        grafo.insertarArco('D', 'E', 1);
        grafo.insertarArco('B', 'C', 5);
        grafo.insertarArco('E', 'C', 5);
        //System.out.println(grafo.toString());
        //grafo.eliminarArco('A', 'B');
        System.out.println(grafo.toString());
        grafo.eliminarVertice('E');
        System.out.println(grafo.toString());
        //System.out.println(grafo.listarEnProfundidad());
        //System.out.println(grafo.listarEnAnchura());
        ////////System.out.println(grafo.toString());
        
    }
    
}
