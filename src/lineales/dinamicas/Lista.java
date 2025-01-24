package lineales.dinamicas;

import testsMios.Solicitud;

public class Lista {

    private Nodo cabecera;
    // private int longitud;

    public Lista() {
        this.cabecera = null;
        // longitud = 0;
    }
    //longitud puede estar como metodo o como atributo

    public boolean insertar(Object nuevoElemento, int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud() + 1) { //para que sea una posicion valida
            exito = false;
        } else {
            if (pos == 1) { // si la lista esta vacia, crea el primer nodo con el valor ingresado
                this.cabecera = new Nodo(nuevoElemento, this.cabecera);
            } else {                                        // para agregarlo a una lista cualq
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {                   //cuenta la longitud para que no se pase y lo inserte en el lugar antes de la posicion deseada
                    aux = aux.getEnlace();         // si lo quiere en la pos 5, itera hasta 4 y lo inserta en ese lugar
                    i++;
                }                                               //crea el nodo y lo enlaza con el siguiente, y despues hace el enganche con el anterior
                Nodo nuevo = new Nodo(nuevoElemento, aux.getEnlace());          //( q le pase el getEnlace de aux q era el anterior)
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos < 1 || pos > longitud()) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());

            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        // Devuelve el elemento en la posicion requerida por el usuario
        // Caso invalido retorna null
        Object elem;
        if (pos < 1 || pos > longitud()) {
            elem = null;
        } else { // Caso valido retorna elemento
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElem();
        }
        return elem;
    }

    public int localizar(Object elem) {
        int pos = -1, i = 1;
        boolean exito = false;

        Nodo aux = this.cabecera;
        while (i <= longitud() && aux != null && exito == false) {
            if (aux.getElem().equals(elem)) {
                exito = true;
                pos = i;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }

        return pos;
    }

    public int longitud() {
        int i = 0;
        if (cabecera != null) {
            i = 1;
            Nodo enlace = this.cabecera.getEnlace();  // si el enlace no es nulo, que lo cuente 
            while (enlace != null) {
                enlace = enlace.getEnlace();
                i++;
            }
        }
        return i;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        if (!esVacia()) {
            Nodo aux1 = this.cabecera;
            Nodo aux2 = new Nodo(aux1.getElem(), null);
            listaClon.cabecera = aux2;
            while (aux1.getEnlace() != null) {
                aux1 = aux1.getEnlace(); // itero el aux1 
                Nodo nuevo = new Nodo(aux1.getElem(), null); // creo el nodo con el elemento correspondiente de la original
                aux2.setEnlace(nuevo);  //engancho el aux con el nodo nuevo 
                aux2 = aux2.getEnlace(); // itero el aux2 
            }
        }
        return listaClon;
    }

    @Override
    public String toString() {
        String resultado = "";
        Nodo aux = this.cabecera;
        if (esVacia()) {
            resultado = "La lista esta vacia";
        } else {
            while (aux != null) {
                resultado = resultado + aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    resultado = resultado + ",";
                }
            }
            resultado = "[" + resultado + "]";
        }
        return resultado;
    }

    public boolean equals(Lista l) {
        boolean esIgual = true;
        Nodo aux1 = this.cabecera;
        Nodo aux2 = l.cabecera;
        if ((aux1 != null && aux2 == null) || (aux1 == null && aux2 != null)) {
            esIgual = false;
        }
        while (aux1 != null && esIgual) {
            if (aux1.getElem().equals(aux2.getElem())) {
                aux1 = aux1.getEnlace();
                aux2 = aux2.getEnlace();
            } else {
                esIgual = false;
            }
        }
        return esIgual;
    }
    
    public boolean eliminarSolicitud(Object elem) {
        boolean exito = false;
        Solicitud elemAux = (Solicitud) elem;
        if(this.cabecera != null) {
            Solicitud solicitud = (Solicitud) this.cabecera.getElem();
            if(solicitud.equals(elemAux)) {
                this.cabecera = this.cabecera.getEnlace();
                exito = true;
            } else {
                Nodo aux = this.cabecera;
                while (aux.getEnlace() != null && !exito) {
                    solicitud = (Solicitud) aux.getEnlace().getElem();
                    if(solicitud.equals(elemAux)) {
                        aux.setEnlace(aux.getEnlace().getEnlace());
                        exito = true;
                    } else {
                        aux = aux.getEnlace();
                    }
                }
            }
        }
        return exito;
    }

    //--------------------------------------------------------------------------
    // 1b parcial 2019
    public void agregarElem(Object nuevo, int x) {
        int pos = 1;
        this.cabecera = new Nodo(nuevo, this.cabecera);
        Nodo aux = this.cabecera;
        while (aux != null) {
            if (pos % (x + 1) == 0) {
                Nodo nuevoNodo = new Nodo(nuevo, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
                aux = aux.getEnlace();
                pos++;
            } else {
                aux = aux.getEnlace();
                pos++;
            }
        }
    }

    //--------------------------------------------------------------------------
    //                    Ejercicios Simulacro 1er Parcial
    // 1a
    public Lista obtenerMultiplos(int num) {
        /* obtenerMultiplos(int num) que recibe un número y devuelve una lista nueva
        que contiene todos los elementos de las posiciones múltiplos de num, en el
        mismo orden encontrado, haciendo un único recorrido de las estructuras original
        y copia; y sin usar otras operaciones del TDA. Ejemplo: si se invoca con
        la lista <A,B,C,D,E,F,G,H,I,J> y num=3, el método debe devolver la lista <C,F,I> */
        Lista listaNueva = new Lista();
        int i = 1;
        Nodo aux2 = null;
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            while (aux != null) {
                if (i % num == 0) {
                    if (listaNueva.cabecera == null) {
                        listaNueva.cabecera = new Nodo(aux.getElem(), null);
                        aux2 = listaNueva.cabecera;
                    } else {
                        aux2.setEnlace(new Nodo(aux.getElem(), null));
                        aux2 = aux2.getEnlace();
                    }
                }
                aux = aux.getEnlace();
                i++;
            }
        }
        return listaNueva;
    }

    /*
    public Lista obtenerMultiplos(int num) {
        Lista listaNueva = new Lista();
        int pos1 = 1, pos2 = 1;
        Nodo aux1 = this.cabecera;
        Nodo aux2 = null;
        if (this.cabecera != null) {
            while (aux1 != null) {
                if (pos1 % num == 0) {
                    if (pos2 == 1) {
                        listaNueva.cabecera = new Nodo(aux1.getElem(), null);
                        aux2 = listaNueva.cabecera;
                        aux1 = aux1.getEnlace();
                        pos1++;
                        pos2++;
                    } else {
                        Nodo nuevo = new Nodo(aux1.getElem(), null);
                        aux2.setEnlace(nuevo);  //engancho el aux con el nodo nuevo 
                        aux1 = aux1.getEnlace();
                        aux2 = aux2.getEnlace();
                        pos1++;
                        pos2++;
                    }
                } else {
                    aux1 = aux1.getEnlace();
                    pos1++;
                }
            }
        }
        return listaNueva;
    }
     */
    // 1b
    public void eliminarApariciones(Object x) {
        /* eliminarApariciones(TipoElemento x) que elimine todas las apariciones
        de elementos iguales a x, haciendo un único recorrido de la estructura y
        sin usar otras operaciones del TDA */
        if (this.cabecera != null) {
            while (this.cabecera.getElem().equals(x)) {
                this.cabecera = this.cabecera.getEnlace();
            }
            Nodo aux = this.cabecera;
            while (aux.getEnlace() != null) {
                if (aux.getEnlace().getElem().equals(x)) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                } else {
                    aux = aux.getEnlace();
                }
            }
        }

    }

    // Parcial 1 rehecho
    public void cambiarPosicion(int pos1, int pos2) {
        /* Elimina al elemento de pos1 y lo inserta en pos2. Por ejemplo, si lis
        es [6,2,7,1,3,5], pos1 = 3, pos2 = 5 la lista debe quedar [6,2,1,3,7,5] */
        if (pos1 > 0 && pos2 > 0 && pos1 <= this.longitud() && pos2 <= this.longitud()) {
            if (pos1 < pos2) {
                cambiarAux1(pos1, pos2);
            } else {
                cambiarAux2(pos1, pos2);
            }
        }
    }
    
    private void cambiarAux1(int pos1, int pos2) {
        // Caso en el que pos1 es MENOR a pos2 (llevar hacia adelante)
        int i = 1;
        Nodo aux = this.cabecera;
        Nodo anterior;
        Nodo aux1;
        Nodo aux2;
        if (pos1 == 1) {
            // Caso cabecera
            aux1 = this.cabecera;
            while(i < pos2) {
                aux = aux.getEnlace();
                i++;
            }
            aux2 = aux;
            // Hago los cambios
            this.cabecera = this.cabecera.getEnlace();
            aux1.setEnlace(aux2.getEnlace());
            aux2.setEnlace(aux1);
        } else {
            // Caso NO cabecera
            while(i < pos1 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            anterior = aux;
            aux1 = aux.getEnlace();
            while(i < pos2) {
                aux = aux.getEnlace();
                i++;
            }
            aux2 = aux;
            // Hago los cambios
            anterior.setEnlace(aux1.getEnlace());
            aux1.setEnlace(aux2.getEnlace());
            aux2.setEnlace(aux1);
        }
    }
    
    private void cambiarAux2(int pos1, int pos2) {
        // Caso en el que pos1 es MAYOR a pos2 (llevar hacia atras)
        int i = 1;
        Nodo aux = this.cabecera;
        Nodo anterior;
        Nodo aux1;
        Nodo aux2;
        if(pos2 == 1) {
            // Caso cabecera
            while(i < pos1 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            anterior = aux;
            aux1 = aux.getEnlace();
            // Hago los cambios
            anterior.setEnlace(aux1.getEnlace());
            aux1.setEnlace(this.cabecera);
            this.cabecera = aux1;
        } else {
            // Caso NO cabecera
            while(i < pos2 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            aux2 = aux;
            while(i < pos1 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            anterior = aux;
            aux1 = aux.getEnlace();
            // Hago los cambios
            anterior.setEnlace(aux1.getEnlace());
            aux1.setEnlace(aux2.getEnlace());
            aux2.setEnlace(aux1);
        }
    }
    
    /*
    private void cambiar1(int pos1, int pos2) {
        int i = 1;
        Nodo aux = this.cabecera;
        if (pos1 == 1) {
            Object elem = this.cabecera.getElem();
            while (i < pos2) {
                aux = aux.getEnlace();
                i++;
            }
            aux.setEnlace(new Nodo(elem, aux.getEnlace()));
            this.cabecera = this.cabecera.getEnlace();
        } else {
            while (i < pos1 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            Object elem = aux.getEnlace().getElem();
            Nodo aux2 = aux;
            while (i < pos2) {
                aux2 = aux2.getEnlace();
                i++;
            }
            aux.setEnlace(aux.getEnlace().getEnlace());
            aux2.setEnlace(new Nodo(elem, aux2.getEnlace()));
        }
    }

    private void cambiar2(int pos1, int pos2) {
        int i = 1;
        Nodo aux = this.cabecera;
        if (pos2 == 1) {
            while (i < pos1 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            Object elem = aux.getEnlace().getElem();
            aux.setEnlace(aux.getEnlace().getEnlace());
            this.cabecera = new Nodo(elem, this.cabecera);
        } else {
            while (i < pos2 - 1) {
                aux = aux.getEnlace();
                i++;
            }
            Nodo aux2 = aux;
            while (i < pos1 - 1) {
                aux2 = aux2.getEnlace();
                i++;
            }
            Object elem = aux2.getEnlace().getElem();
            aux.setEnlace(new Nodo(elem, aux.getEnlace()));
            aux2.setEnlace(aux2.getEnlace().getEnlace());
        }
    }
    */
    // Recu 2019
    public Lista intercalar(Lista l2) {
        /* Recibe una lista l2, y devuelve la lista original con los elementos de l2
        intercalados con los de la lista original. Por ej. si se llama con this [1,3,5]
        y l2 [2,4,6,7,8] debe devolver [1,2,3,4,5,6,7,8] */
        Lista nueva = new Lista();
        Nodo aux1 = null;
        Nodo aux2 = null;
        Nodo auxNueva = null;
        if (this.cabecera != null && l2.cabecera == null) {
            nueva.cabecera = new Nodo(this.cabecera.getElem(), null);
            aux1 = this.cabecera.getEnlace();
            auxNueva = nueva.cabecera;
        } else if (this.cabecera == null && l2.cabecera != null) {
            nueva.cabecera = new Nodo(l2.cabecera.getElem(), null);
            aux2 = l2.cabecera.getEnlace();
            auxNueva = nueva.cabecera;
        } else if (this.cabecera != null && l2.cabecera != null) {
            nueva.cabecera = new Nodo(this.cabecera.getElem(), null);
            nueva.cabecera.setEnlace(new Nodo(l2.cabecera.getElem(), null));
            aux1 = this.cabecera.getEnlace();
            aux2 = l2.cabecera.getEnlace();
            auxNueva = nueva.cabecera.getEnlace();
        }
        while (aux1 != null && aux2 != null) {
            auxNueva.setEnlace(new Nodo(aux1.getElem(), null));
            aux1 = aux1.getEnlace();
            auxNueva = auxNueva.getEnlace();
            auxNueva.setEnlace(new Nodo(aux2.getElem(), null));
            aux2 = aux2.getEnlace();
            auxNueva = auxNueva.getEnlace();
        }
        if (aux1 != null && aux2 == null) {
            while (aux1 != null) {
                auxNueva.setEnlace(new Nodo(aux1.getElem(), null));
                aux1 = aux1.getEnlace();
                auxNueva = auxNueva.getEnlace();
            }
        }
        if (aux1 == null && aux2 != null) {
            while (aux2 != null) {
                auxNueva.setEnlace(new Nodo(aux2.getElem(), null));
                aux2 = aux2.getEnlace();
                auxNueva = auxNueva.getEnlace();
            }
        }
        return nueva;
    }

    /*
    public Lista intercalar(Lista l2) {
        Lista nueva = new Lista();
        Nodo aux1 = null;
        Nodo aux2 = null;
        Nodo auxNueva = null;
        if (this.cabecera == null && l2.cabecera != null) {
            nueva.cabecera = new Nodo(l2.cabecera.getElem(), null);
            aux2 = l2.cabecera.getEnlace();
            auxNueva = nueva.cabecera;
            while (aux2 != null) {
                auxNueva.setEnlace(new Nodo(aux2.getElem(), null));
                aux2 = aux2.getEnlace();
                auxNueva = auxNueva.getEnlace();
            }
        } else if (this.cabecera != null && l2.cabecera == null) {
            nueva.cabecera = new Nodo(this.cabecera.getElem(), null);
            aux1 = this.cabecera.getEnlace();
            auxNueva = nueva.cabecera;
            while (aux1 != null) {
                auxNueva.setEnlace(new Nodo(aux1.getElem(), null));
                aux1 = aux1.getEnlace();
                auxNueva = auxNueva.getEnlace();
            }
        } else if (this.cabecera != null && l2.cabecera != null) {
            nueva.cabecera = new Nodo(this.cabecera.getElem(), null);
            nueva.cabecera.setEnlace(new Nodo(l2.cabecera.getElem(), null));
            aux1 = this.cabecera.getEnlace();
            aux2 = l2.cabecera.getEnlace();
            auxNueva = nueva.cabecera.getEnlace();
            while (aux1 != null && aux2 != null) {
                auxNueva.setEnlace(new Nodo(aux1.getElem(), null));
                aux1 = aux1.getEnlace();
                auxNueva = auxNueva.getEnlace();
                auxNueva.setEnlace(new Nodo(aux2.getElem(), null));
                aux2 = aux2.getEnlace();
                auxNueva = auxNueva.getEnlace();
            }
            if (aux1 != null && aux2 != null) {
                while (aux1 != null) {
                    auxNueva.setEnlace(new Nodo(aux1.getElem(), null));
                    aux1 = aux1.getEnlace();
                    auxNueva = auxNueva.getEnlace();
                }

            }
            if (aux1 == null && aux2 != null) {
                while (aux2 != null) {
                    auxNueva.setEnlace(new Nodo(aux2.getElem(), null));
                    aux2 = aux2.getEnlace();
                    auxNueva = auxNueva.getEnlace();
                }
            }
        }
        return nueva;
    }
     */
}
