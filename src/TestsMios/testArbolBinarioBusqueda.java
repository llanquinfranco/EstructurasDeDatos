package TestsMios;

import conjuntistas.ArbolBB;

/**
 *
 * @author Fran
 */
public class testArbolBinarioBusqueda {

    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
        arbol.insertar(45);
        arbol.insertar(65);
        arbol.insertar(34);
        arbol.insertar(55);
        arbol.insertar(73);
        arbol.insertar(13);
        arbol.insertar(96);
        System.out.println(arbol.toString());
        System.out.println("55 pertenece al arbol BB?: " + arbol.pertenece(55));
        System.out.println("Listado de menor a mayor: " + arbol.listar());
        System.out.println("Elemento mas pequeño del arbol: " + arbol.minimoElem());
        System.out.println("Elemento mas grande del arbol: " + arbol.maximoElem());
        System.out.println("Listar desde 10 a 55: " + arbol.listarRango(10, 55));
        arbol.eliminar(65);
        System.out.println(arbol.toString());
    }
}