package practicaParcial2;

import conjuntistas.ArbolBB;

/**
 *
 * @author Fran
 */
public class testArbolBinarioBusqueda {

    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
        /*
        arbol.insertar(56);
        arbol.insertar(13);
        arbol.insertar(78);
        arbol.insertar(7);
        arbol.insertar(24);
        arbol.insertar(100);
        arbol.insertar(15);
        System.out.println(arbol.toString());
        arbol.eliminarMinimo();
        System.out.println(arbol.toString());
        System.out.println("Clonar invertido desde 13?: " + arbol.clonarParteInvertida(13));
        System.out.println("Elementos mayores o iguales a 20 (de mayor a menor): " + arbol.listarMayorIgual(20));
        System.out.println("Elementos menores a 56 (de menor a mayor): " + arbol.listarMenores(56));
         */
        // Recu 2022
        arbol.insertar(20);
        arbol.insertar(12);
        arbol.insertar(28);
        arbol.insertar(7);
        arbol.insertar(14);
        arbol.insertar(25);
        arbol.insertar(33);
        arbol.insertar(3);
        arbol.insertar(10);
        arbol.insertar(17);
        arbol.insertar(31);
        System.out.println("Listar mayores a 9 a partir de 12: " + arbol.listarMayoresQue(9, 12));
        
        
        
    }

}
