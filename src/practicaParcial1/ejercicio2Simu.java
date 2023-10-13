package practicaParcial1;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

/**
 *
 * @author Fran
 */
public class ejercicio2Simu {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        
        // 2c
        /*
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        System.out.println("Cola c1: " + c1.toString());
        System.out.println("Cola generada: " + generar(c1).toString());
        */
        
        // 2d
        c1.poner('{');
        c1.poner(5);
        c1.poner('+');
        c1.poner('[');
        c1.poner('8');
        c1.poner('*');
        c1.poner('9');
        c1.poner('-');
        c1.poner('(');
        c1.poner(4);
        c1.poner('/');
        c1.poner(2);
        c1.poner(')');
        c1.poner('+');
        c1.poner(7);
        c1.poner(']');
        c1.poner('-');
        c1.poner(1);
        c1.poner('}');
        System.out.println("Esta balanceada?: " + verificarBalanceo(c1));
    }
    
    // 2c
    public static Cola generar(Cola c1) {
        Cola colaGenerada = new Cola();
        Cola colaClonada = c1.clone();
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        Lista listaAux = new Lista();
        colaClonada.poner('#');
        while(!colaClonada.esVacia()){
            if(!colaClonada.obtenerFrente().equals('#') ) {
                pilaAux.apilar(colaClonada.obtenerFrente());
                colaAux.poner(colaClonada.obtenerFrente());
                listaAux.insertar(colaClonada.obtenerFrente(), listaAux.longitud() + 1);
                colaClonada.sacar();
            } else {
                colaClonada.sacar();
                while(!colaAux.esVacia()) {
                    colaGenerada.poner(colaAux.obtenerFrente());
                    colaAux.sacar();
                }
                while(!pilaAux.esVacia()) {
                    colaGenerada.poner(pilaAux.obtenerTope());
                    pilaAux.desapilar();
                }
                while(!listaAux.esVacia()) {
                    colaGenerada.poner(listaAux.recuperar(1));
                    listaAux.eliminar(1);
                }
                if (!colaClonada.esVacia()) {
                    colaGenerada.poner('#');
                }
            }
        }
        return colaGenerada;
    }
    
    // 2d
    public static boolean verificarBalanceo(Cola q) {
        Lista abreParentesis = new Lista();
        Lista abreCorchetes = new Lista();
        Lista abreLlaves = new Lista();
        Lista cierraParentesis = new Lista();
        Lista cierraCorchetes = new Lista();
        Lista cierraLlaves = new Lista();
        int p1 = 1, p2 = 1, c1 = 1, c2 = 1, l1 = 1, l2 = 1;
        boolean verifica = false;
        while(!q.esVacia()){
            if(q.obtenerFrente().equals('(')) {
                abreParentesis.insertar(q.obtenerFrente(), p1);
                p1++;
            }
            if(q.obtenerFrente().equals(')')) {
                cierraParentesis.insertar(q.obtenerFrente(), p2);
                p2++;
            }
            if(q.obtenerFrente().equals('[')) {
                abreCorchetes.insertar(q.obtenerFrente(), c1);
                c1++;
            }
            if(q.obtenerFrente().equals(']')) {
                cierraCorchetes.insertar(q.obtenerFrente(), c2);
                c2++;
            }
            
            if(q.obtenerFrente().equals('{')) {
                abreLlaves.insertar(q.obtenerFrente(), l1);
                l1++;
            }
            if(q.obtenerFrente().equals('}')) {
                cierraLlaves.insertar(q.obtenerFrente(), l2);
                l2++;
            }
            q.sacar();
        }
        if((abreParentesis.longitud() == cierraParentesis.longitud()) && (abreCorchetes.longitud() == cierraCorchetes.longitud()) && (abreLlaves.longitud() == cierraLlaves.longitud())) {
            verifica = true;
        }
        return verifica;
    }
}