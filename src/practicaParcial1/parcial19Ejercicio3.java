package practicaParcial1;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

/**
 *
 * @author Fran
 */
public class parcial19Ejercicio3 {

    public static void main(String[] args) {
        Cola q = new Cola();
        q.poner(0);
        q.poner(1);
        q.poner(2);
        q.poner(3);
        q.poner(4);
        q.poner(5);
        q.poner(6);
        q.poner(7);
        q.poner(8);
        q.poner(9);
        System.out.println("Cola q: " + q.toString());
        System.out.println("Lista generada con secuencias: " + generarSecuencia(q, 4).toString());
    }

    public static Lista generarSecuencia(Cola q, int i) {
        Lista listaGenerada = new Lista();
        Cola colaClonada = q.clone(); // para no perder info original
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        int pos = 1;
        while (!colaClonada.esVacia()) {
            if ((pos) % i == 0) {
                pilaAux.apilar(colaClonada.obtenerFrente());
                colaAux.poner(colaClonada.obtenerFrente());
                pos++;
                while (!pilaAux.esVacia()) {
                    listaGenerada.insertar(pilaAux.obtenerTope(), listaGenerada.longitud() + 1);
                    pilaAux.desapilar();
                }
                while (!colaAux.esVacia()) {
                    listaGenerada.insertar(colaAux.obtenerFrente(), listaGenerada.longitud() + 1);
                    colaAux.sacar();
                }
                listaGenerada.insertar('$', listaGenerada.longitud() + 1);
            } else {
                pilaAux.apilar(colaClonada.obtenerFrente());
                colaAux.poner(colaClonada.obtenerFrente());
                pos++;
            }
            colaClonada.sacar();
        }
        while (!pilaAux.esVacia()) {
            listaGenerada.insertar(pilaAux.obtenerTope(), listaGenerada.longitud() + 1);
            pilaAux.desapilar();
        }
        while (!colaAux.esVacia()) {
            listaGenerada.insertar(colaAux.obtenerFrente(), listaGenerada.longitud() + 1);
            colaAux.sacar();
        }
        return listaGenerada;
    }
}
