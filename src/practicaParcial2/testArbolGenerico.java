package practicaParcial2;
import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class testArbolGenerico {

    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        /*
        arbol.insertar(20, null);
        arbol.insertar(13, 20);
        arbol.insertar(54, 20);
        arbol.insertar(15, 13);
        arbol.insertar(12, 13);
        arbol.insertar(11, 54);
        arbol.insertar(27, 54);
        arbol.insertar(4, 54);
        arbol.insertar(17, 27);
        
        System.out.println("Arbol: " + arbol.toString());
        Lista lista = new Lista();
        lista.insertar(20, 1);
        lista.insertar(13, 2);
        lista.insertar(12, 3);
        lista.insertar(45, 4);
        System.out.println("Se verifica?: " + arbol.verificarCamino(lista));
        System.out.println("Listar entre nivel 1 y 2: " + arbol.listarEntreNiveles(1, 2).toString());
        System.out.println("Arbol: " + arbol.toString());
        arbol.eliminar(15);
        System.out.println("Arbol: " + arbol.toString());
        System.out.println("Lista en preorden desde raiz hasta nivel 1 " + arbol.listarHastaNivel(1));
        */
        
        // Recu 2022
        arbol.insertar('A', null);
        arbol.insertar('H', 'A');
        arbol.insertar('B', 'A');
        arbol.insertar('Z', 'A');
        arbol.insertar('D', 'H');
        arbol.insertar('M', 'H');
        arbol.insertar('Q', 'H');
        arbol.insertar('P', 'D');
        arbol.insertar('B', 'D');
        arbol.insertar('L', 'Q');
        arbol.insertar('F', 'Z');
        arbol.insertar('C', 'Z');
        arbol.insertar('J', 'Z');
        arbol.insertar('W', 'F');
        arbol.insertar('H', 'F');
        arbol.insertar('V', 'J');
        arbol.insertar('M', 'J');
        System.out.println("Arbol Recu 2022: " + arbol.toString());
        arbol.insertarEnPosicion('R', 'D', 3);
        System.out.println("Arbol Recu 2022 : " + arbol.toString());
    }
    
}
