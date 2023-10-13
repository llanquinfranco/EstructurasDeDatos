package parcialRecu;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

/**
 *
 * @author Fran
 */
public class punto1 {

    public static void main(String[] args) {
        /* Implementar el metodo invertirConVocales(Cola q) donde q es una cola 
        de elementos tipo char de la forma a1#a2#a3#...#an donde cada ai es una 
        sucesion de elementos de tipo char. El metodo debe retornar una lista con
        la sucesion a1#a2#a3#...#an donde ai es invertida si tiene vocales, y sino
        no pasa nada.
        Ejemplo: para q = abcdef # abcd # qwrty # sj debe retornar la lista
        l = [fedcba # dcba # qwrty # sj] */
        Cola q = new Cola();
        q.poner('a');
        q.poner('b');
        q.poner('c');
        q.poner('d');
        q.poner('e');
        q.poner('f');
        q.poner('#');
        q.poner('a');
        q.poner('b');
        q.poner('c');
        q.poner('d');
        q.poner('#');
        q.poner('q');
        q.poner('w');
        q.poner('r');
        q.poner('t');
        q.poner('y');
        q.poner('#');
        q.poner('s');
        q.poner('j');
        System.out.println("Cola: " + q.toString());
        Lista listaNueva = invertirConVocales(q);
        System.out.println("Lista Generada: " + listaNueva.toString());
    }

    public static Lista invertirConVocales(Cola q) {
        Lista listaGenerada = new Lista();
        Cola colaClon = q.clone();
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        colaClon.poner('#');
        boolean tieneVocal;
        char elem;
        char aux;
        while (!colaClon.esVacia()) {
            tieneVocal = verifVocal(colaClon.clone());
            if (tieneVocal) {
                elem = (char) colaClon.obtenerFrente();
                while (elem != '#') {
                    aux = (char) colaClon.obtenerFrente();
                    pilaAux.apilar(aux);
                    colaClon.sacar();
                    elem = (char) colaClon.obtenerFrente();
                }
                colaClon.sacar();
                while (!pilaAux.esVacia()) {
                    elem = (char)pilaAux.obtenerTope();
                    listaGenerada.insertar(elem, listaGenerada.longitud() + 1);
                    pilaAux.desapilar();
                }
            } else {
                elem = (char) colaClon.obtenerFrente();
                while (elem != '#') {
                    aux = (char) colaClon.obtenerFrente();
                    colaAux.poner(aux);
                    colaClon.sacar();
                    elem = (char) colaClon.obtenerFrente();
                }
                colaClon.sacar();
                while (!colaAux.esVacia()) {
                    elem = (char) colaAux.obtenerFrente();
                    listaGenerada.insertar(elem, listaGenerada.longitud() + 1);
                    colaAux.sacar();
                }
            }
            if (!colaClon.esVacia()) {
                listaGenerada.insertar('#', listaGenerada.longitud() + 1);
            }
        }
        return listaGenerada;
    }

    public static boolean verifVocal(Cola colaClon) {
        boolean tieneVocal = false;
        Object aux = colaClon.obtenerFrente();
        while (!tieneVocal && !aux.equals('#')) {
            if (aux.equals('a') || aux.equals('e') || aux.equals('i')
                || aux.equals('o') || aux.equals('u') || aux.equals('A')
                || aux.equals('E') || aux.equals('I') || aux.equals('O') || aux.equals('U')) {
                tieneVocal = true;
            } else {
                colaClon.sacar();
                aux = colaClon.obtenerFrente();
            }
        }
        return tieneVocal;
    }

    /*
    public static Lista invertirConVocales(Cola q) {
        Lista listaGenerada = new Lista();
        Cola colaClon = q.clone();
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        boolean tieneVocal;
        while (!colaClon.esVacia()) {
            char elem = (char) colaClon.obtenerFrente();
            if (elem != '#') {
                colaAux.poner(colaClon.obtenerFrente());
                pilaAux.apilar(colaClon.obtenerFrente());
                colaClon.sacar();
            } else {
                colaClon.sacar();
                tieneVocal = verifVocal(colaClon.clone());
                if (tieneVocal) {
                    while (!pilaAux.esVacia()) {
                        listaGenerada.insertar(pilaAux.obtenerTope(), listaGenerada.longitud() + 1);
                        pilaAux.desapilar();
                    }
                    colaAux.vaciar();
                } else {
                    while (!colaAux.esVacia()) {
                        listaGenerada.insertar(colaAux.obtenerFrente(), listaGenerada.longitud() + 1);
                        colaAux.sacar();
                    }
                    pilaAux.vaciar();
                }
                
                if (!colaClon.esVacia()) {
                    listaGenerada.insertar('#', listaGenerada.longitud() + 1);
                }
            }
            System.out.println("xq no andaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(listaGenerada.toString());
        }
        return listaGenerada;
    }
     */
}
