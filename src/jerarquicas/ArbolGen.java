package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        // Crea un árbol generico vacio
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        /* Dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo como hijo de la 
        primer aparicion de elemPadre. Para que la operacion termine con exito debe existir un nodo
        en el arbol con elemento = elemPadre. No se establece ninguna preferencia respecto a la 
        posicion del hijo respecto a sus posibles hermanos. Esta operacion devuelve
        verdadero cuando se pudo agregar elemNuevo a la estructura y falso en caso contrario */
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    NodoGen aux = nodoPadre.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null) {
                        aux = aux.getHermanoDerecho();
                    }
                    aux.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
        NodoGen resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                resultado = nodo;
            } else {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null && resultado == null) {
                    resultado = obtenerNodo(aux, buscado);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    public boolean pertenece(Object buscado) {
        // Devuelve verdadero si el elemento pasado por parametro esta en el arbol, y sino, falso
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, buscado);
        }
        return exito;
    }

    private boolean perteneceAux(NodoGen nodo, Object elemento) {
        boolean existe = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                existe = true;
            } else {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null && !existe) {
                    existe = perteneceAux(aux, elemento);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return existe;
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en caso contrario
        return this.raiz == null;
    }

    public Object padre(Object buscado) {
        /* Dado un elemento devuelve el valor almacenado en su nodo padre (busca
        la primera aparición de elemento) */
        Object elemento = null;
        if (this.raiz != null) {
            if (!this.raiz.getElem().equals(elemento)) {
                elemento = obtenerPadre(this.raiz, buscado);
            }
        }
        return elemento;
    }

    private Object obtenerPadre(NodoGen nodo, Object buscado) {
        Object resultado = null;
        if (nodo != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null && resultado == null) {
                if (aux.getElem().equals(buscado)) {
                    resultado = nodo.getElem();
                } else {
                    resultado = obtenerPadre(aux, buscado);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    public int altura() {
        /* Devuelve la altura del arbol, es decir la longitud del camino más largo desde la raíz
        hasta una hoja (Nota: un arbol vacío tiene altura -1 y una hoja tiene altura 0) */
        int alturita = -1;
        if (this.raiz != null) {
            alturita = alturaAux(this.raiz);
        }
        return alturita;
    }

    public int alturaAux(NodoGen nodo) {
        int alt = -1, alturitaAux = -1;
        if (nodo != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null) {
                alturitaAux = alturaAux(aux);
                if (alturitaAux > alt) {
                    alt = alturitaAux;
                }
                aux = aux.getHermanoDerecho();
            }
            alt++;
        }
        return alt;
    }

    public int nivel(Object elemento) {
        // Devuelve el nivel de un elemento en el arbol. Si el elemento no existe, devuelve -1
        int nivelElemento = -1;
        if (this.raiz != null) {
            nivelElemento = nivelAux(this.raiz, elemento, 0);
        }
        return nivelElemento;
    }

    private int nivelAux(NodoGen nodo, Object buscado, int profundidad) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem() != buscado) {
                profundidad++;
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null && nivel == -1) {
                    nivel = nivelAux(aux, buscado, profundidad);
                    aux = aux.getHermanoDerecho();
                }
            } else {
                nivel = profundidad;
            }
        }
        return nivel;
    }

    public Lista ancestros(Object elemento) {
        /* Si el elemento se encuentra en el arbol, devuelve una lista con el camino
        desde la raiz hasta dicho elemento (es decir, con los ancestros del elemento).
        Si el elemento no esta en el arbol devuelve la lista vacia */
        Lista listaAncestros = new Lista();
        if (this.raiz != null) {
            ancestrosAux(this.raiz, listaAncestros, elemento);
        }
        return listaAncestros;
    }

    private boolean ancestrosAux(NodoGen nodo, Lista lista, Object elemento) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                encontrado = true;
            }
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null && !encontrado) {
                encontrado = ancestrosAux(aux, lista, elemento);
                aux = aux.getHermanoDerecho();
            }
            if (encontrado && (!nodo.getElem().equals(elemento))) {
                lista.insertar(nodo.getElem(), 1);
            }
        }
        return encontrado;
    }

    @Override
    public ArbolGen clone() {
        /* Genera y devuelve un arbol generico que es equivalente (igual estructura
        y contenido de los nodos) que el arbol original */
        ArbolGen arbolClon = new ArbolGen();
        if (this.raiz != null) {
            arbolClon.raiz = new NodoGen(this.raiz.getElem(), null, null);
            cloneAux(this.raiz, arbolClon.raiz);
        }
        return arbolClon;
    }

    private void cloneAux(NodoGen nodoOriginal, NodoGen nodoClon) {
        if (nodoOriginal != null) {
            if (nodoOriginal.getHijoIzquierdo() != null) {
                nodoClon.setHijoIzquierdo(new NodoGen(nodoOriginal.getHijoIzquierdo().getElem(), null, null));
                NodoGen aux1 = nodoOriginal.getHijoIzquierdo();
                NodoGen aux2 = nodoClon.getHijoIzquierdo();
                while (aux1.getHermanoDerecho() != null) {
                    aux2.setHermanoDerecho(new NodoGen(aux1.getHermanoDerecho().getElem(), null, null));
                    aux1 = aux1.getHermanoDerecho();
                    aux2 = aux2.getHermanoDerecho();
                }
            }
            cloneAux(nodoOriginal.getHijoIzquierdo(), nodoClon.getHijoIzquierdo());
            cloneAux(nodoOriginal.getHermanoDerecho(), nodoClon.getHermanoDerecho());
        }
    }

    public void vaciar() {
        // Quita todos los elementos de la estructura
        this.raiz = null;
    }

    public Lista listarPreorden() {
        // Devuelve una lista con los elementos del arbol en el recorrido en preorden
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    listarPreordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarInorden() {
        // Devuelve una lista con los elementos del arbol en el recorrido en inorden
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenAux(nodo.getHijoIzquierdo(), lista);
            }
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        // Devuelve una lista con los elementos del arbol en el recorrido en posorden
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    listarPosordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        // Devuelve una lista con los elementos del árbol binario en el recorrido por niveles
        Cola colaNiveles = new Cola();
        Lista listaNiveles = new Lista();
        colaNiveles.poner(this.raiz);
        if (this.raiz != null) {
            while (!colaNiveles.esVacia()) {
                NodoGen nodo = (NodoGen) colaNiveles.obtenerFrente();
                colaNiveles.sacar();
                listaNiveles.insertar(nodo.getElem(), listaNiveles.longitud() + 1);
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null) {
                        colaNiveles.poner(hijo);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return listaNiveles;
    }

    @Override
    public String toString() {
        /* Genera y devuelve una cadena de caracteres que indica cual es la raiz 
        del arbol y quienes son los hijos de cada nodo */
        String resultado;
        if (this.raiz != null) {
            resultado = "\n" + toStringAux(this.raiz);
        } else {
            resultado = "El arbol esta vacio";
        }
        return resultado;
    }

    private String toStringAux(NodoGen nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = cadena + nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                if (hijo.getHermanoDerecho() != null) {
                    cadena = cadena + hijo.getElem().toString() + ", ";
                } else {
                    cadena = cadena + hijo.getElem().toString();
                }
                hijo = hijo.getHermanoDerecho();
            }
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena = cadena + "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return cadena;
    }

    //---------------------------- TPO 2 ---------------------------------------
    public boolean equals(ArbolGen unArbol) {
        boolean verifica;
        verifica = equalsAux(this.raiz, unArbol.raiz);
        return verifica;
    }

    private boolean equalsAux(NodoGen nodo1, NodoGen nodo2) {
        boolean esIgual = true;
        if ((nodo1 != null && nodo2 == null) || (nodo1 == null && nodo2 != null)) {
            esIgual = false;
        }
        if (nodo1 != null && nodo2 != null) {
            if (nodo1.getElem().equals(nodo2.getElem())) {
                esIgual = equalsAux(nodo1.getHijoIzquierdo(), nodo2.getHijoIzquierdo());
                if (esIgual) {
                    esIgual = equalsAux(nodo1.getHermanoDerecho(), nodo2.getHermanoDerecho());
                }
            } else {
                esIgual = false;
            }
        }
        return esIgual;
    }

    public boolean sonFrontera(Lista unaLista) {
        boolean verifica;
        verifica = sonFronteraAux(this.raiz, unaLista.clone());
        return verifica;
    }

    public boolean sonFronteraAux(NodoGen nodo, Lista lista) {
        boolean retorno = true;
        if (nodo != null && retorno) {
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && retorno) {
                    retorno = sonFronteraAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            } else {
                if (lista.localizar(nodo.getElem()) > 0) {
                    retorno = true;
                } else {
                    retorno = false;
                }
            }
        }
        return retorno;
    }

    //--------------------- Ejercicios Adicionales -----------------------------
    public boolean verificarPatron(Lista lisPatron) {
        boolean existePatron = false;
        if (this.raiz != null) {
            existePatron = verificarPatronAux(this.raiz, lisPatron, 1);
        }
        return existePatron;
    }

    public boolean verificarPatronAux(NodoGen nodo, Lista lista, int i) {
        boolean resultado = false;
        if (nodo != null && i <= lista.longitud()) {
            if (lista.recuperar(i).equals(nodo.getElem())) {
                if (nodo.getHijoIzquierdo() == null && i == lista.longitud()) {
                    resultado = true;
                }
                if (!resultado && nodo.getHijoIzquierdo() != null) {
                    i++;
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (aux != null && !resultado) {
                        resultado = verificarPatronAux(aux, lista, i);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return resultado;
    }

    public Lista frontera() {
        Lista listaHojas = new Lista();
        fronteraAux(this.raiz, listaHojas);
        return listaHojas;
    }

    private void fronteraAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            } else {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null) {
                    fronteraAux(aux, lista);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listaQueJustificaLaAltura() {
        Lista actual = new Lista();
        Lista res = new Lista();
        res = listaQueJustificaLaAlturaAux(this.raiz, actual, res);
        return res;
    }

    public Lista listaQueJustificaLaAlturaAux(NodoGen nodo, Lista actual, Lista res) {
        if (nodo != null) {
            actual.insertar(nodo.getElem(), actual.longitud() + 1);
            if (nodo.getHijoIzquierdo() == null) {
                if (actual.longitud() > res.longitud()) {
                    res = actual;
                }
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (aux != null) {
                        res = listaQueJustificaLaAlturaAux(aux, actual.clone(), res);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return res;
    }

    //------------------------- Simu 2do Parcial -------------------------------
    public boolean verificarCamino(Lista lista) {
        /* Dada una lista, verifica si la misma corresponde a un camino desde
        la raiz hasta algun elemento del arbol */
        boolean seVerifica = false;
        if (this.raiz != null) {
            seVerifica = verificarCaminoAux(this.raiz, lista, 1);
        }
        return seVerifica;
    }

    private boolean verificarCaminoAux(NodoGen nodo, Lista lista, int pos) {
        boolean exito = false;
        if (nodo != null && pos <= lista.longitud()) {
            if (nodo.getElem().equals(lista.recuperar(pos))) {
                if (pos == lista.longitud()) {
                    exito = true;
                }
                if (!exito && nodo.getHijoIzquierdo() != null) {
                    pos++;
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (aux != null && !exito) {
                        exito = verificarCaminoAux(aux, lista, pos);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return exito;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        /* Recibe como parametro dos elementos niv1 y niv2 y devuelve una lista
        con los elementos del arbol que estan entre los niveles niv1 y niv2 inclusive. */
        Lista lista = new Lista();
        listarEntreNivelesAux(this.raiz, niv1, niv2, 0, lista);
        return lista;
    }

    private void listarEntreNivelesAux(NodoGen nodo, int niv1, int niv2, int i, Lista lista) {
        if (nodo != null && i <= niv2) {
            if (nodo.getHijoIzquierdo() != null) {
                listarEntreNivelesAux(nodo.getHijoIzquierdo(), niv1, niv2, i + 1, lista);
            }
            if (i >= niv1) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarEntreNivelesAux(hijo, niv1, niv2, i + 1, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public void eliminar(Object elem) {
        // Permite quitar un elemento del arbol, junto a todos sus descendientes
        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elem)) {
                // El nodo a eliminar es la raiz
                this.raiz = null;
            } else {
                // Sino, lo buscamos y lo eliminamos
                eliminarAux(this.raiz, null, elem);
            }
        }
    }

    private void eliminarAux(NodoGen nodo, NodoGen anterior, Object elem) {
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                // Caso en el que el nodo a eliminar es HD de su anterior
                anterior.setHermanoDerecho(nodo.getHermanoDerecho());
            } else if (nodo.getHijoIzquierdo().getElem().equals(elem)) {
                // Caso en el que el nodo a eliminar es HEI de su anterior
                nodo.setHijoIzquierdo(nodo.getHijoIzquierdo().getHermanoDerecho());
            } else {
                // Busco el nodo
                if (nodo.getHijoIzquierdo().getHermanoDerecho() != null && nodo.getHijoIzquierdo() != null) {
                    NodoGen aux = nodo.getHijoIzquierdo().getHermanoDerecho();
                    NodoGen anteriorAux = nodo.getHijoIzquierdo();
                    while (aux != null) {
                        eliminarAux(aux, anteriorAux, elem);
                        aux = aux.getHermanoDerecho();
                        anteriorAux = anteriorAux.getHermanoDerecho();
                    }
                }
            }
        }
    }

    public Lista listarHastaNivel(int niv) {
        Lista lista = new Lista();
        listarHastaNivelAux(this.raiz, 0, niv, lista);
        return lista;
    }

    private void listarHastaNivelAux(NodoGen nodo, int i, int niv, Lista lista) {
        if (nodo != null && i <= niv) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null) {
                    listarHastaNivelAux(aux, i + 1, niv, lista);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
    }

    // Recu 2022
    public boolean insertarEnPosicion(Object elem, Object padre, int pos) {
        /* Inserta elem como hijo de padre en la posicion pos dentro de la lista de hermanos
        Si el valor de pos es invalido, debera insertar elem como ultimo hermano derecho */
        boolean exito = true;
        int i = 1;
        if (this.raiz != null) {
            NodoGen nodoPadre = buscarNodo(this.raiz, padre);
            if (nodoPadre != null) {
                if (nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(new NodoGen(elem, null, null));
                } else if (pos == 1) {
                    nodoPadre.setHijoIzquierdo(new NodoGen(elem, null, nodoPadre.getHijoIzquierdo()));
                } else {
                    NodoGen aux = nodoPadre.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null && i < pos - 1) {
                        aux = aux.getHermanoDerecho();
                        i++;
                    }
                    aux.setHermanoDerecho(new NodoGen(elem, null, aux.getHermanoDerecho()));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen buscarNodo(NodoGen nodo, Object elemPadre) {
        NodoGen retorno = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elemPadre)) {
                retorno = nodo;
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (aux != null && retorno == null) {
                        retorno = buscarNodo(aux, elemPadre);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return retorno;
    }
}
