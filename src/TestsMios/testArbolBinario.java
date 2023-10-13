package TestsMios;

import jerarquicas.ArbolBin;

/**
 *
 * @author Fran
 */
public class testArbolBinario {

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        
        System.out.println(arbol.toString());
        
        // Arbol Giuli
        /*arbol.insertar(1, null, 'i');
        arbol.insertar(2, 1, 'i');
        arbol.insertar(4, 2, 'i');
        arbol.insertar(5, 2, 'd');
        arbol.insertar(6, 4, 'd');
        arbol.insertar(3, 1, 'd');
        arbol.insertar(7, 3, 'i');
        arbol.insertar(8, 3, 'd');
        arbol.insertar(9, 8, 'i');
        arbol.insertar(10, 9, 'i');
        */
        // Arbol Apunte
        /*
        arbol.insertar('A', null, 'I');
        arbol.insertar('B', 'A', 'I');
        arbol.insertar('C', 'A', 'D');
        arbol.insertar('D', 'B', 'I');
        arbol.insertar('E', 'C', 'I');
        arbol.insertar('F', 'C', 'D');
        arbol.insertar('G', 'E', 'I');
        arbol.insertar('H', 'E', 'D');
        */
        
        System.out.println("Preorden: " + arbol.listarPreorden());
      /*  System.out.println("Inorden: " + arbol.listarInorden());
        System.out.println("Posorden: " + arbol.listarPosorden());
        System.out.println("Por niveles: " + arbol.listarPorNiveles());
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Nivel de 1: " + arbol.nivel(1));
        System.out.println("Nivel de 3: " + arbol.nivel(3));
        System.out.println("Nivel de 8: " + arbol.nivel(8));
        System.out.println("Nivel de 9: " + arbol.nivel(9));
        */
        
        ArbolBin arbolClon = arbol.clone();
        System.out.println("Arbol Original: " + arbol.toString());
        arbolClon.insertar(10, 6, 'i');
        System.out.println("Arbol Clonado: " + arbolClon.toString());
        System.out.println("Arbol Original: " + arbol.toString());
        System.out.println("Frontera de Arbol Original: " + arbol.frontera());
        System.out.println("Frontera de Arbol Clonado: " + arbolClon.frontera());
        System.out.println("Ancestros de 6: " + arbol.obtenerAncestros(6));
        System.out.println("Descendientes de 2: " + arbol.obtenerDescendientes(2));
        
        System.out.println("Lista que Justifica la Altura:" + arbol.listaQueJustificaLaAltura());
        /*
        for(int i = 1; i < 10; i++){
            System.out.println("Nodo: " + i);
            System.out.println(arbol.padre(i));
            
        }
        */
        
    }
}
