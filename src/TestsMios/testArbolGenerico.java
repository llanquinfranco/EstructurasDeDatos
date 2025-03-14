package testsMios;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class testArbolGenerico {

    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        System.out.println(arbol.toString());
        // Arbol del apunte
        arbol.insertar('A', null);
        arbol.insertar('B', 'A');
        arbol.insertar('C', 'A');
        arbol.insertar('D', 'A');
        arbol.insertar('E', 'B');
        arbol.insertar('F', 'B');
        arbol.insertar('G', 'B');
        arbol.insertar('H', 'D');
        //arbol.insertar('I', 'H');
        System.out.println("Arbol Original: " + arbol.toString());
        
        System.out.println("H pertenece al arbol?: " + arbol.pertenece('H'));
        System.out.println("Padre de H: " + arbol.padre('H'));
        System.out.println("Altura del arbol: " + arbol.altura());
        System.out.println("Nivel de G: " + arbol.nivel('G'));
        System.out.println("Ancestros de H: " + arbol.ancestros('H'));
        
        ArbolGen arbolClon = arbol.clone();
        arbolClon.insertar('I', 'H');
        System.out.println("Arbol Original: \n" + arbol.toString());
        System.out.println("Arbol Clonado: \n" + arbolClon.toString());
        
        System.out.println("Listado en Preorden: " + arbol.listarPreorden());
        System.out.println("Listado en Inorden: " + arbol.listarInorden());
        System.out.println("Listado en Posorden: " + arbol.listarPosorden());
        System.out.println("Listado por Niveles: " + arbol.listarPorNiveles());
        
        // TPO 2
        System.out.println("Son iguales?: " + arbol.equals(arbolClon));
       
        Lista listita = new Lista();
        listita.insertar('E', 1);
        listita.insertar('G', 2);
        listita.insertar('H', 3);
        listita.insertar('F', 4);
        listita.insertar('C', 5);
        System.out.println("La lista es Frontera?: " + arbol.sonFrontera(listita));
        
        // Ejercicios adicionales
        Lista listaPatron = new Lista();
        listaPatron.insertar('A', 1);
        listaPatron.insertar('D', 2);
        listaPatron.insertar('H', 3);
        System.out.println("La lista " + listaPatron.toString() + " coincide con un camino de la raiz a una hoja?: " + arbol.verificarPatron(listaPatron));
        System.out.println("Hojas del arbol: " + arbol.frontera());
        System.out.println("Lista que justifica la altura: " + arbol.listaQueJustificaLaAltura());
    }
    
}
