package testsMios;
import conjuntistas.ArbolAVL;

/**
 *
 * @author Fran
 */
public class testArbolAVL {

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        /*
        arbol.insertar(1);
        arbol.insertar(2);
        arbol.insertar(3);
        arbol.insertar(4);
        arbol.insertar(5);
        arbol.insertar(6);
        
        arbol.insertar(10);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(11);
        arbol.insertar(12);
        arbol.eliminar(5);
        */
        
        ArbolAVL pino = new ArbolAVL();
        pino.insertar(20);
        pino.insertar(12);
        pino.insertar(28);
        pino.insertar(7);
        pino.insertar(14);
        pino.insertar(25);
        pino.insertar(33);
        pino.insertar(31);
        pino.insertar(3);
        pino.insertar(10);
        pino.insertar(17);
        
        pino.eliminar(7);
        
        System.out.println(pino.toString());
    }
    
}
