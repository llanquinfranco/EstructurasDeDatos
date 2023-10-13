/**
 *
 * @author Giuli Vicentino
 */
package tests.lineales;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;
import lineales.dinamicas.Cola;
import java.util.Scanner;

public class PruebaLista {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lista lista1 = new Lista();
        Lista lista2 = new Lista();
        /* for (int i = 1; i <= 5; i++) {
            lista1.insertar(i, i);
        }*/
        lista1.insertar(9, 1);
        lista1.insertar(6, 2);
        lista1.insertar(5, 3);
        lista1.insertar(0, 4);
        lista1.insertar(9, 5);
        lista1.insertar(6, 6);
        lista1.insertar(5, 7);
        lista1.insertar(0, 8);
        lista1.insertar(5, 9);
        lista1.insertar(6, 10);
        lista1.insertar(9, 11);

        for (int i = 1; i <= 5; i++) {
            lista2.insertar(5 + i, i);
        }
        System.out.println("Lista 1: " + lista1.toString());
        System.out.println("Lista 2: " + lista2.toString());
        int opcion = 0;
        do {
            System.out.println("Ingrese la opcion correspondiente a la operacion que desee realizar");
            System.out.println("(1) concatenar ,  (2) comprobar , (3) invertir y (0) si desea terminar el programa");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Lista concatenada: " + concatenar(lista1, lista2).toString());
                    break;
                case 2:
                    if (comprobar(lista1)) {
                        System.out.println("Verifica");
                    } else {
                        System.out.println("NO verifica");
                    }
                    ;
                    break;
                case 3:
                    System.out.println("Lista original: " + lista2.toString());
                    System.out.println("Lista invertida: " + invertir(lista2).toString());
                    ;
                    break;
                case 0:
                    System.out.println("Programa finalizado");
            }
        } while (opcion != 0);

    }

    /*  concatenar: recibe dos listas L1 y L2 y devuelve una lista nueva con los elementos de L1 y L2
        concatenados. Ej: si L1=[2,4,6] y L2=[5,1,6,7] debe devolver [2,4,6,5,1,6,7]
     */
    public static Lista concatenar(Lista lista1, Lista lista2) {
        Lista conca = new Lista();
        int i = 1, j = 1, longi = lista1.longitud() + lista2.longitud();

        while (i <= lista1.longitud() || j <= lista2.longitud()) {
            Object aux1, aux2;
            aux1 = lista1.recuperar(i);
            aux2 = lista2.recuperar(j);
            if (i <= longi - lista2.longitud()) { //longitud de la primera lista
                conca.insertar(aux1, i);
                i++;
            } else { //para la longitud de lista2
                conca.insertar(aux2, i);
                i++;
                j++;
            }
        }

        return conca;
    }

    /*
    comprobar: recibe una lista L1 cargada con dígitos (números enteros de 0 a 9) y verica si los
elementos que contiene tienen la forma cadena0cadena0cadena* (donde cadena* es cadena invertida).
Ej: si L1=[9,6,5,0,9,6,5,0,5,6,9], cadena=965, luego cadena*=569, entonces la lista L1 cumple con la
condición deseada.
Atención: la longitud de cada cadena no se conoce de antemano, hay que identicarla por la primera
posición de 0 en la lista.
Nota: Utilizar una Pila y una Cola como estructuras auxiliares.
     */
    public static boolean comprobar(Lista lista1) {
        boolean verifica;
        Cola cola1 = new Cola();
        Pila pila1 = new Pila();
        Lista lista2 = new Lista();
        int i = 1, longitudL1 = lista1.longitud();
        //conseguis la cadena
        while (i < lista1.localizar(0)) { // ver si hay algo mas eficiente que hasta longitud
            cola1.poner(lista1.recuperar(i)); //creas la cola con los elementos de la cadena
            pila1.apilar(lista1.recuperar(i)); //creas la cola que queda invertida xq los elementos se agregan al principio en pila
            i++;
        }
        System.out.println("Cola: " + cola1.toString());
        System.out.println("Pila: " + pila1.toString());

        listaCondicion(cola1, pila1, lista2, longitudL1);
        //COMPROBAR
        if (equals(lista1, lista2) && lista1.longitud() == lista2.longitud()) {
            verifica = true;
        } else {
            verifica = false;
        }

        return verifica;
    }

    public static void listaCondicion(Cola cola1, Pila pila1, Lista lista2, int longitudL1) {
        int i = 1;
        Cola cola1Clon = new Cola();
        cola1Clon = cola1.clone();
        while (!cola1.esVacia()) {
            lista2.insertar(cola1.obtenerFrente(), i);
            cola1.sacar();
            i++;
        }
        lista2.insertar(0, i);
        i++;
        while (!cola1Clon.esVacia()) {
            lista2.insertar(cola1Clon.obtenerFrente(), i);
            cola1Clon.sacar();
            i++;
        }
        lista2.insertar(0, i);
        i++;

        while (!pila1.esVacia()) {
            lista2.insertar(pila1.obtenerTope(), i);
            pila1.desapilar();
            i++;
        }
        System.out.println(lista2.toString());

    }

    public static boolean equals(Lista lista1, Lista lista2) {
        boolean esIgual = true;
        int i = 1;
        while (esIgual && i <= lista1.longitud()) {
            if (lista1.recuperar(i) == lista2.recuperar(i)) {
                i++;
            } else {
                esIgual = false;
            }
        }
        return esIgual;
    }

    /* invertir : recibe una lista L y devuelve una lista nueva con los elementos
    de L invertidos. Ej: si L1=[2,4,6] debe devolver [6,4,2] */
    public static Lista invertir(Lista lista1) {
        Lista listaInvertida = new Lista();
        int i = 1, j = lista1.longitud();
        while (i <= lista1.longitud()) {
            Object aux = lista1.recuperar(j);
            listaInvertida.insertar(aux, i);
            i++;
            j--;
        }
        return listaInvertida;
    }

}
