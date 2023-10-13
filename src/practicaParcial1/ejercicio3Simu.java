package practicaParcial1;
import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class ejercicio3Simu {

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        Lista patron = new Lista();
        System.out.println(arbol.toString());
        
        // Arbol Giuli
        arbol.insertar(1, null, 'i');
        arbol.insertar(2, 1, 'i');
        arbol.insertar(4, 2, 'i');
        arbol.insertar(5, 2, 'd');
        arbol.insertar(6, 4, 'd');
        arbol.insertar(3, 1, 'd');
        arbol.insertar(7, 3, 'i');
        arbol.insertar(8, 3, 'd');
        arbol.insertar(9, 8, 'i');
        // Lista Patron
        patron.insertar(1, 1);
        patron.insertar(3, 2);
        patron.insertar(8, 3);
        patron.insertar(9, 4);
        
        System.out.println("Arbol original: " + arbol.toString());
        System.out.println(patron.toString());
        
        // 3e
        System.out.println("Un patron de la lista coincide con un camino desde la raiz hasta una hoja?: " + arbol.verificarPatron(patron));
        
        // 3f
        System.out.println("Frontera del arbol: " + arbol.frontera());
        
        // 3g
        ArbolBin arbolInvertido = arbol.clonarInvertido();
        System.out.println("Arbol clonado invertido: " + arbolInvertido.toString());
        
    }
    
}
