package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        // Crea un árbol binario vacío
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) { //para cuando esta vacio asi hace que eso sea la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre); //si el arbol no esta vacio(raiz no nula), busca el nodo padre
            if (nPadre != null) {  //si el nodo padre existe y el lugar no esta ocupado lo pone, si no, da error
                if ((lugar == 'I' || lugar == 'i') && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if ((lugar == 'D' || lugar == 'd') && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        // METODO PRIVADO que busca un elemento y devuelve el nodo que lo contiene. Si no se encuentra el buscado devueve null
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) { // Si el elemento buscado es n, lo devuelve
                resultado = n;
            } else { // Si no era, busca primero por el izquierdo
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) { // Si tampoco era, finalmente busca por el derecho
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en caso contrario.
        return this.raiz == null;
    }

    public Object padre(Object buscado) {
        // Dado un elemento devuelve el valor almacenado en su nodo padre (busca la primera aparición de elemento).
        Object elemento = null;
        if (this.raiz != null) {
            if (this.raiz.getElem() != buscado) {
                elemento = obtenerPadre(this.raiz, buscado);
            }
        }
        return elemento;
    }

    private Object obtenerPadre(NodoArbol n, Object buscado) {
        // METODO PRIVADO que busca un elemento y devuelve el nodo padre
        Object resultado = null;
        if (n != null) {
            if (n.getIzquierdo() != null && resultado == null) {
                if (n.getIzquierdo().getElem() == (buscado)) {
                    resultado = n.getElem();
                }
            }
            if (n.getDerecho() != null && resultado == null) {
                if (n.getDerecho().getElem() == (buscado)) {
                    resultado = n.getElem();
                }
            }
            if (resultado == null) {
                resultado = obtenerPadre(n.getDerecho(), buscado);
                if (resultado == null) {
                    resultado = obtenerPadre(n.getIzquierdo(), buscado);
                }
            }
        }
        return resultado;
    }

    public int altura() {
        /* Devuelve la altura del arbol, es decir la longitud del camino más largo desde
        la raiz hasta una hoja (Un arbol vacio tiene altura -1 y una hoja tiene altura 0) */
        int alturita = -1;
        if (this.raiz != null) {
            alturita = alturaAux(this.raiz);
        }
        return alturita;
    }

    private int alturaAux(NodoArbol nodo) {
        int alt = 0, altIzq = 0, altDer = 0;
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                altIzq = altIzq + alturaAux(nodo.getIzquierdo()) + 1;
            }
            if (nodo.getDerecho() != null) {
                altDer = altDer + alturaAux(nodo.getDerecho()) + 1;
            }
            if (altIzq > altDer) {
                alt = altIzq;
            } else {
                alt = altDer;
            }
        }
        return alt;
    }

    public int nivel(Object elemento) {
        // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1.
        int nivelElemento;
        nivelElemento = nivelAux(this.raiz, elemento, 0);
        return nivelElemento;
    }

    private int nivelAux(NodoArbol nodo, Object buscado, int profundidad) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem() != buscado) {
                nivel = nivelAux(nodo.getIzquierdo(), buscado, profundidad + 1);
                if (nivel == -1) {
                    nivel = nivelAux(nodo.getDerecho(), buscado, profundidad + 1);
                }
            } else {
                nivel = profundidad;
            }
        }
        return nivel;
    }

    public void vaciar() {
        // Quita todos los elementos de la estructura.
        this.raiz = null;
    }

    @Override
    public ArbolBin clone() {
        ArbolBin arbolClon = new ArbolBin();
        if (this.raiz != null) {
            arbolClon.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            cloneAux(arbolClon.raiz, this.raiz);
        }
        return arbolClon;
    }

    private void cloneAux(NodoArbol nodoClon, NodoArbol nodoOriginal) {
        if (nodoOriginal != null) {
            if (nodoOriginal.getIzquierdo() != null) {
                nodoClon.setIzquierdo(new NodoArbol(nodoOriginal.getIzquierdo().getElem(), null, null));
            }
            if (nodoOriginal.getDerecho() != null) {
                nodoClon.setDerecho(new NodoArbol(nodoOriginal.getDerecho().getElem(), null, null));
            }
            cloneAux(nodoClon.getIzquierdo(), nodoOriginal.getIzquierdo());
            cloneAux(nodoClon.getDerecho(), nodoOriginal.getDerecho());
        }
    }

    @Override
    public String toString() {
        if (this.raiz == null) {
            return "El arbol es vacio";
        } else {
            return stringAux(this.raiz);
        }
    }

    private String stringAux(NodoArbol nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = "\n NODO " + nodo.getElem() + " " + cadena;
            if (nodo.getIzquierdo() != null) {
                cadena = cadena + " HI: " + nodo.getIzquierdo().getElem() + " ";
            } else {
                cadena = cadena + " HI: - ";
            }
            if (nodo.getDerecho() != null) {
                cadena = cadena + " HD: " + nodo.getDerecho().getElem() + " ";
            } else {
                cadena = cadena + " HD: - ";
            }
            if (nodo.getIzquierdo() != null) {
                cadena = cadena + stringAux(nodo.getIzquierdo());
            }
            if (nodo.getDerecho() != null) {
                cadena = cadena + stringAux(nodo.getDerecho());
            }
        }
        return cadena;
    }

    public Lista listarPreorden() {
        // Devuelve una lista con los elementos del árbol binario en el recorrido en preorden
        Lista lista = new Lista();
        recorrerPreorden(this.raiz, lista);
        return lista;
    }

    private void recorrerPreorden(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            recorrerPreorden(nodo.getIzquierdo(), lista);
            recorrerPreorden(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPosorden() {
        // Devuelve una lista con los elementos del árbol binario en el recorrido en posorden
        Lista lista = new Lista();
        recorrerPosorden(this.raiz, lista);
        return lista;
    }

    private void recorrerPosorden(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            recorrerPosorden(nodo.getIzquierdo(), lista);
            recorrerPosorden(nodo.getDerecho(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        // Devuelve una lista con los elementos del árbol binario en el recorrido en inorden
        Lista lista = new Lista();
        recorrerInorden(this.raiz, lista);
        return lista;
    }

    private void recorrerInorden(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            recorrerInorden(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            recorrerInorden(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPorNiveles() {
        // Devuelve una lista con los elementos del árbol binario en el recorrido por niveles
        Cola colaNiveles = new Cola();
        Lista listaNiveles = new Lista();
        colaNiveles.poner(this.raiz);
        if (this.raiz != null) {
            while (!colaNiveles.esVacia()) {
                NodoArbol nodoActual = (NodoArbol) colaNiveles.obtenerFrente();
                colaNiveles.sacar();
                listaNiveles.insertar(nodoActual.getElem(), listaNiveles.longitud() + 1);
                if (nodoActual.getIzquierdo() != null) {
                    colaNiveles.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null) {
                    colaNiveles.poner(nodoActual.getDerecho());
                }
            }
        }
        return listaNiveles;
    }

    //--------------------- Ejercicios Adicionales -----------------------------
    public Lista frontera() {
        /* Devuelve una lista con la secuencia formada por los elementos almacenados
        en las hojas del árbol binario, tomadas de izquierda a derecha */
        Lista listaFrontera = new Lista();
        fronteraAux(this.raiz, listaFrontera);
        return listaFrontera;
    }

    private void fronteraAux(NodoArbol n, Lista listita) {
        if (n != null) {
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                listita.insertar(n.getElem(), listita.longitud() + 1);
            }
            if (n.getIzquierdo() != null) {
                fronteraAux(n.getIzquierdo(), listita);
            }
            if (n.getDerecho() != null) {
                fronteraAux(n.getDerecho(), listita);
            }
        }
    }

    public Lista obtenerAncestros(Object elemento) {
        Lista listaAncestros = new Lista();
        if (this.raiz != null) {
            obtenerAncestrosAux(this.raiz, listaAncestros, elemento);
        }
        return listaAncestros;
    }

    private boolean obtenerAncestrosAux(NodoArbol nodo, Lista unaLista, Object buscado) {
        boolean encontrado = false;
        if (nodo != null) {
            Object elemento = nodo.getElem();
            if (elemento.equals(buscado)) {
                encontrado = true;
            }
            if (!encontrado) {
                encontrado = obtenerAncestrosAux(nodo.getIzquierdo(), unaLista, buscado);
            }
            if (!encontrado) {
                encontrado = obtenerAncestrosAux(nodo.getDerecho(), unaLista, buscado);
            }
            if (encontrado && (!elemento.equals(buscado) || elemento.equals(this.raiz.getElem().toString()))) {
                unaLista.insertar(nodo.getElem(), unaLista.longitud() + 1);
            }
        }
        return encontrado;
    }

    public Lista obtenerDescendientes(Object elemento) {
        Lista listaDescendientes = new Lista();
        if (this.raiz != null) {
            obtenerDescendientesAux(this.raiz, listaDescendientes, elemento, false);
        }
        return listaDescendientes;
    }

    private void obtenerDescendientesAux(NodoArbol nodo, Lista unaLista, Object buscado, boolean encontrado) {
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                encontrado = true;
            }
            if (encontrado) {
                if (nodo.getIzquierdo() != null) {
                    unaLista.insertar(nodo.getIzquierdo().getElem(), unaLista.longitud() + 1);
                    obtenerDescendientesAux(nodo.getIzquierdo(), unaLista, buscado, encontrado);
                }
                if (nodo.getDerecho() != null) {
                    unaLista.insertar(nodo.getDerecho().getElem(), unaLista.longitud() + 1);
                    obtenerDescendientesAux(nodo.getDerecho(), unaLista, buscado, encontrado);
                }
            } else {
                obtenerDescendientesAux(nodo.getIzquierdo(), unaLista, buscado, encontrado);
                obtenerDescendientesAux(nodo.getDerecho(), unaLista, buscado, encontrado);
            }
        }
    }

    public boolean verificarPatron(Lista patron) {
        /* Recibe por parametro una lista patron y determina si coincide exactamente
        con al menos un camino del arbol que comience en la raíz y termine en una hoja */
        boolean existePatron = false;
        if (this.raiz != null) {
            existePatron = verificarPatronAux(patron, this.raiz, 1);
        }
        return existePatron;
    }

    private boolean verificarPatronAux(Lista patron, NodoArbol nodo, int pos) {
        boolean resultado = false;
        if (nodo != null && pos <= patron.longitud()) {
            if (patron.recuperar(pos).equals(nodo.getElem())) {
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null && pos == patron.longitud()) {
                    resultado = true;
                }
                if (!resultado && nodo.getIzquierdo() != null) {
                    resultado = verificarPatronAux(patron, nodo.getIzquierdo(), pos + 1);
                }
                if (!resultado && nodo.getDerecho() != null) {
                    resultado = verificarPatronAux(patron, nodo.getDerecho(), pos + 1);
                }
            }
        }
        return resultado;
    }

    public Lista listaQueJustificaLaAltura() {
        Lista actual = new Lista();
        Lista res = new Lista();
        res = listaQueJustificaLaAlturaAux(this.raiz, actual, res);
        return res;
    }

    public Lista listaQueJustificaLaAlturaAux(NodoArbol nodo, Lista actual, Lista res) {
        if (nodo != null) {
            actual.insertar(nodo.getElem(), actual.longitud() + 1);
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                if (actual.longitud() > res.longitud()) {
                    res = actual;
                }
            } else {
                if (nodo.getIzquierdo() != null) {
                    res = listaQueJustificaLaAlturaAux(nodo.getIzquierdo(), actual.clone(), res);
                }
                if (nodo.getDerecho() != null) {
                    res = listaQueJustificaLaAlturaAux(nodo.getDerecho(), actual.clone(), res);
                }
            }
        }
        return res;
    }

    //-----------------------------------------------------------------------------------------
    // 3g simu
    public ArbolBin clonarInvertido() {
        /* Devuelve un nuevo arbol, que es una copia del arbol original pero donde
        los hijos están cambiados de lugar. Atención: el metodo devuelve un nuevo
        arbol, sin modificar el arbol original */
        ArbolBin arbolInvertido = new ArbolBin();
        if (this.raiz != null) {
            arbolInvertido.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonarInvertidoAux(this.raiz, arbolInvertido.raiz);
        }
        return arbolInvertido;
    }

    private void clonarInvertidoAux(NodoArbol nodoOriginal, NodoArbol nodoClon) {
        if (nodoOriginal != null) {
            if (nodoOriginal.getIzquierdo() != null) {
                nodoClon.setDerecho(new NodoArbol(nodoOriginal.getIzquierdo().getElem(), null, null));
                clonarInvertidoAux(nodoOriginal.getIzquierdo(), nodoClon.getDerecho());
            }
            if (nodoOriginal.getDerecho() != null) {
                nodoClon.setIzquierdo(new NodoArbol(nodoOriginal.getDerecho().getElem(), null, null));
                clonarInvertidoAux(nodoOriginal.getDerecho(), nodoClon.getIzquierdo());
            }
        }
    }

    // 3 parcial 2022
    public void completarHijos() {
        if (this.raiz != null) {
            completarHijosAux(this.raiz);
        }
    }

    private void completarHijosAux(NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null && nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoArbol(nodo.getIzquierdo().getElem(), null, null));
            }
            if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                nodo.setIzquierdo(new NodoArbol(nodo.getDerecho().getElem(), null, null));
            }
            completarHijosAux(nodo.getIzquierdo());
            completarHijosAux(nodo.getDerecho());
        }
    }

    // No me acuerdo q parcial
    public boolean equals(ArbolBin otro) {
        boolean verifica;
        verifica = equalsAux(this.raiz, otro.raiz);
        return verifica;
    }

    public boolean equalsAux(NodoArbol nodo1, NodoArbol nodo2) {
        boolean esIgual = true;
        if ((nodo1 != null && nodo2 == null) || (nodo1 == null && nodo2 != null)) {
            esIgual = false;
        }
        if (nodo1 != null && nodo2 != null) {
            if (nodo1.getElem().equals(nodo2.getElem())) {
                esIgual = equalsAux(nodo1.getIzquierdo(), nodo2.getIzquierdo());
                if (esIgual) {
                    esIgual = equalsAux(nodo1.getDerecho(), nodo2.getDerecho());
                }
            } else {
                esIgual = false;
            }
        }
        return esIgual;
    }

    //Parcial 1 Rehecho
    public boolean menosDeCantApariciones(Object elem, int cant) {
        /* Devuelve verdadero si hay menos de cant apariciones de elem en el arbol,
        y falso en caso contrario */
        boolean resultado = false;
        if (this.raiz != null) {
            int apariciones = contar(this.raiz, elem, cant);
            System.out.println(apariciones);
            if (apariciones < cant) {
                resultado = true;
            }
        }
        return resultado;
    }

    private int contar(NodoArbol nodo, Object elem, int cant) {
        int apariciones = 0;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                apariciones = 1;
            }
            if(apariciones < cant) {
                apariciones = apariciones + contar(nodo.getIzquierdo(), elem, cant);
                apariciones = apariciones + contar(nodo.getDerecho(), elem, cant);
            }
        }
        return apariciones;

    }

}
