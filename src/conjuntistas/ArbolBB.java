package conjuntistas;

import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        /* Recibe un elemento y lo agrega en el arbol de manera ordenada. Si el 
        elemento ya se encuentra en el árbol no se realiza la inserción. Devuelve 
        verdadero si el elemento se agrega a la estructura y falso en caso contrario */
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elemento) {
        // Precondicion: nodo no es nulo
        boolean exito = true;
        if (elemento.compareTo(nodo.getElem()) == 0) {
            // Elemento repetido
            exito = false;
        } else if (elemento.compareTo(nodo.getElem()) < 0) {
            // elemento es menor a nodo.getElem()
            // Si tiene HI baja a la izquierda, sino lo setea
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elemento);
            } else {
                nodo.setIzquierdo(new NodoABB(elemento, null, null));
            }
        } else if (elemento.compareTo(nodo.getElem()) > 0) {
            // elemento es mayor a nodo.getElem()
            // Si tiene HD baja a la derecha, sino lo setea
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), elemento);
            } else {
                nodo.setDerecho(new NodoABB(elemento, null, null));
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        /* Recibe el elemento que se desea eliminar y se procede a removerlo del
        arbol. Si no se encuentra, no se puede realizar la eliminación. Devuelve
        verdadero si el elemento se elimina de la estructura y falso en caso contrario */
        boolean resultado = false;
        if (this.raiz != null) {
            resultado = eliminarAux(this.raiz, null, elemento);
        }
        return resultado;
    }

    private boolean eliminarAux(NodoABB nodo, NodoABB padre, Comparable elemento) {
        boolean exito = false;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElem()) == 0) {
                /*  Si es hoja: eliminar segun el caso 1
                    Si tiene un hijo: eliminar segun el caso 2
                    Si tiene ambos hijos: eliminar segun el caso 3
                 */
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    caso1(nodo, padre);
                } else if ((nodo.getIzquierdo() != null && nodo.getDerecho() == null) || (nodo.getIzquierdo() == null && nodo.getDerecho() != null)) {
                    caso2(nodo, padre);
                } else if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                    caso3(nodo, padre);
                }
                exito = true;
            } else if (elemento.compareTo(nodo.getElem()) < 0) {
                exito = eliminarAux(nodo.getIzquierdo(), nodo, elemento);
            } else if (elemento.compareTo(nodo.getElem()) > 0) {
                exito = eliminarAux(nodo.getDerecho(), nodo, elemento);
            }
        }
        return exito;
    }

    private void caso1(NodoABB nodo, NodoABB padre) {
        //  El nodo a eliminar es una hoja
        if (padre != null) {
            if (nodo.getElem().compareTo(padre.getElem()) < 0) {
                // Caso en el que nodo es HI de su padre
                padre.setIzquierdo(null);
            } else if (nodo.getElem().compareTo(padre.getElem()) > 0) {
                // Caso en el que nodo es HD de su padre
                padre.setDerecho(null);
            }
        } else {
            // Caso raiz
            this.raiz = null;
        }
    }

    private void caso2(NodoABB nodo, NodoABB padre) {
        // El nodo a eliminar tiene un solo hijo
        if (padre != null) {
            if (nodo.getElem().compareTo(padre.getElem()) < 0) {
                // Caso en el que nodo es HI de su padre
                if (nodo.getIzquierdo() != null) {
                    // Caso en el que el unico hijo de nodo es HI
                    padre.setIzquierdo(nodo.getIzquierdo());
                } else {
                    // Caso en el que el unico hijo de nodo es HD
                    padre.setIzquierdo(nodo.getDerecho());
                }
            } else if (nodo.getElem().compareTo(padre.getElem()) > 0) {
                // Caso en el que nodo es HD de su padre
                if (nodo.getIzquierdo() != null) {
                    // Caso en el que el unico hijo de nodo es HI
                    padre.setDerecho(nodo.getIzquierdo());
                } else {
                    // Caso en el que el unico hijo de nodo es HD
                    padre.setDerecho(nodo.getDerecho());
                }
            }
        } else {
            // Caso raiz
            if (nodo.getIzquierdo() != null) {
                // Caso en el que el unico hijo de la raiz es HI
                this.raiz = this.raiz.getIzquierdo();
            } else {
                // Caso en el que el unico hijo de nodo es HD
                this.raiz = this.raiz.getDerecho();
            }
        }
    }

    private void caso3(NodoABB nodo, NodoABB padre) {
        // El nodo a eliminar tiene ambos hijos
        if (padre != null) {
            // Caso NO raiz
            if (nodo.getIzquierdo().getDerecho() != null) {
                // Caso en el que el candidato es el nieto/bisnieto
                NodoABB nodoAux = nodo.getIzquierdo();
                NodoABB padreAux = null;
                while (nodoAux.getDerecho() != null) {
                    padreAux = nodoAux;
                    nodoAux = nodoAux.getDerecho();
                }
                nodo.setElem(nodoAux.getElem());
                if (nodoAux.getIzquierdo() != null) {
                    padreAux.setDerecho(nodoAux.getIzquierdo());
                } else {
                    padreAux.setDerecho(null);
                }
            } else {
                // Caso en el que el candidato es el hijo
                nodo.setElem(nodo.getIzquierdo().getElem());
                if (nodo.getIzquierdo().getIzquierdo() != null) {
                    nodo.setIzquierdo(nodo.getIzquierdo().getIzquierdo());
                } else {
                    nodo.setIzquierdo(null);
                }
            }
        } else {
            // Caso raiz
            if (nodo.getIzquierdo().getDerecho() != null) {
                // Caso en el que el candidato es el nieto/bisnieto
                NodoABB nodoAux = nodo.getIzquierdo();
                NodoABB padreAux = null;
                while (nodoAux.getDerecho() != null) {
                    padreAux = nodoAux;
                    nodoAux = nodoAux.getDerecho();
                }
                this.raiz.setElem(nodoAux.getElem());
                if (nodoAux.getIzquierdo() != null) {
                    padreAux.setDerecho(nodoAux.getIzquierdo());
                } else {
                    padreAux.setDerecho(null);
                }
            } else {
                // Caso en el que el candidato es el hijo
                this.raiz.setElem(nodo.getIzquierdo().getElem());
                if (nodo.getIzquierdo().getIzquierdo() != null) {
                    this.raiz.setIzquierdo(nodo.getIzquierdo().getIzquierdo());
                } else {
                    this.raiz.setIzquierdo(null);
                }
            }
        }
    }

    public boolean pertenece(Comparable elemento) {
        // Devuelve verdadero si el elemento recibido está en el arbol y falso en caso contrario
        boolean resultado = false;
        if (this.raiz != null) {
            resultado = perteneceAux(this.raiz, elemento);
        }
        return resultado;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable buscado) {
        boolean exito = false;
        if (nodo != null) {
            if (buscado.compareTo(nodo.getElem()) == 0) {
                exito = true;
            } else if (buscado.compareTo(nodo.getElem()) < 0) {
                exito = perteneceAux(nodo.getIzquierdo(), buscado);
            } else if (buscado.compareTo(nodo.getElem()) > 0) {
                exito = perteneceAux(nodo.getDerecho(), buscado);
            }
        }
        return exito;
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un elemento en el arbol y verdadero en caso contrario
        return this.raiz == null;
    }

    public Lista listar() {
        /* Recorre el arbol completo y devuelve una lista ordenada con los
        elementos que se encuentran almacenados en el */
        Lista lista = new Lista();
        listarAux(this.raiz, lista);
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        // Recorrido inorden
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
        /* Recorre parte del arbol (sólo lo necesario) y devuelve una lista ordenada
        con los elementos que se encuentran en el intervalo [elemMinimo, elemMaximo] */
        Lista lista = new Lista();
        listarRangoAux(this.raiz, lista, elemMinimo, elemMaximo);
        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Lista lista, Comparable minimo, Comparable maximo) {
        if (nodo != null) {
            if (minimo.compareTo(nodo.getElem()) <= 0) {
                listarRangoAux(nodo.getIzquierdo(), lista, minimo, maximo);
            }
            if (minimo.compareTo(nodo.getElem()) <= 0 && maximo.compareTo(nodo.getElem()) >= 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if (maximo.compareTo(nodo.getElem()) >= 0) {
                listarRangoAux(nodo.getDerecho(), lista, minimo, maximo);
            }
        }
    }

    public Comparable minimoElem() {
        // Recorre la rama correspondiente y devuelve el elemento mas pequeño almacenado en el arbol
        Comparable elemMinimo = null;
        if (this.raiz != null) {
            elemMinimo = minimoElemAux(this.raiz);
        }
        return elemMinimo;
    }

    private Comparable minimoElemAux(NodoABB nodo) {
        Comparable elemento = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() == null) {
                elemento = nodo.getElem();
            } else {
                elemento = minimoElemAux(nodo.getIzquierdo());
            }
        }
        return elemento;
    }

    public Comparable maximoElem() {
        // Recorre la rama correspondiente y devuelve el elemento mas grande almacenado en el arbol
        Comparable elemMaximo = null;
        if (this.raiz != null) {
            elemMaximo = maximoElemAux(this.raiz);
        }
        return elemMaximo;
    }

    private Comparable maximoElemAux(NodoABB nodo) {
        Comparable elemento = null;
        if (nodo != null) {
            if (nodo.getDerecho() == null) {
                elemento = nodo.getElem();
            } else {
                elemento = maximoElemAux(nodo.getDerecho());
            }
        }
        return elemento;
    }

    @Override
    public ArbolBB clone() {
        ArbolBB arbolClon = new ArbolBB();
        if (this.raiz != null) {
            arbolClon.raiz = new NodoABB(this.raiz.getElem(), null, null);
            cloneAux(arbolClon.raiz, this.raiz);
        }
        return arbolClon;
    }

    private void cloneAux(NodoABB nodoClon, NodoABB nodoOriginal) {
        if (nodoOriginal != null) {
            if (nodoOriginal.getIzquierdo() != null) {
                nodoClon.setIzquierdo(new NodoABB(nodoOriginal.getIzquierdo().getElem(), null, null));
            }
            if (nodoOriginal.getDerecho() != null) {
                nodoClon.setDerecho(new NodoABB(nodoOriginal.getDerecho().getElem(), null, null));
            }
            cloneAux(nodoClon.getIzquierdo(), nodoOriginal.getIzquierdo());
            cloneAux(nodoClon.getDerecho(), nodoOriginal.getDerecho());
        }
    }

    public void vaciar() {
        this.raiz = null;
    }

    @Override
    public String toString() {
        if (this.raiz == null) {
            return "El arbol es vacio";
        } else {
            return stringAux(this.raiz);
        }
    }

    private String stringAux(NodoABB nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = "\n NODO " + nodo.getElem() + " " + cadena;
            if (nodo.getIzquierdo() != null) {
                cadena = cadena + " HI: (menor): " + nodo.getIzquierdo().getElem() + " ";
            } else {
                cadena = cadena + " HI: (menor): - ";
            }
            if (nodo.getDerecho() != null) {
                cadena = cadena + " HD: (mayor): " + nodo.getDerecho().getElem() + " ";
            } else {
                cadena = cadena + " HD: (mayor): - ";
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

    //------------------------- Simu 2do Parcial -------------------------------
    public void eliminarMinimo() {
        // Elimine el elemento más pequeño del arbol en un solo recorrido
        if (this.raiz != null) {
            eliminarMinimoAux(this.raiz, null);
        }
    }

    private void eliminarMinimoAux(NodoABB nodo, NodoABB padre) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                // Busco el minimo
                eliminarMinimoAux(nodo.getIzquierdo(), nodo);
            } else {
                // Ya lo encontre, realizo eliminacion
                if (this.raiz.getElem().compareTo(nodo.getElem()) == 0) {
                    // El minimo es la raiz
                    this.raiz = nodo.getDerecho();
                } else {
                    // El minimo NO es la raiz
                    if (this.raiz.getElem().compareTo(padre.getElem()) == 0) {
                        // Caso HI de raiz
                        if (nodo.getDerecho() == null) {
                            // Nodo a eliminar NO tiene hijo derecho y es hoja
                            this.raiz.setIzquierdo(null);
                        } else {
                            // Nodo a eliminar SI tiene hijo derecho
                            this.raiz.setIzquierdo(nodo.getDerecho());
                        }
                    } else {
                        // Caso HI de nodo NO raiz
                        if (nodo.getDerecho() == null) {
                            // Nodo a eliminar NO tiene hijo derecho y es hoja
                            padre.setIzquierdo(null);
                        } else {
                            // Nodo a eliminar SI tiene hijo derecho
                            padre.setIzquierdo(nodo.getDerecho());
                        }
                    }
                }
            }
        }
    }

    public ArbolBB clonarParteInvertida(Comparable elem) {
        /* Devuelve un nuevo arbol que es una copia del subarbol original, 
        cuya raíz es el elemento dado y cada hijo está cambiado de lugar */
        ArbolBB arbolClonado = new ArbolBB();
        NodoABB raizDada = buscarRaizNueva(this.raiz, elem);
        if (raizDada != null) {
            arbolClonado.raiz = (new NodoABB(raizDada.getElem(), null, null));
            clonInvertido(raizDada, arbolClonado.raiz);
        }
        return arbolClonado;
    }

    private void clonInvertido(NodoABB nodoOriginal, NodoABB nodoClon) {
        if (nodoOriginal != null) {
            if (nodoOriginal.getIzquierdo() != null) {
                nodoClon.setDerecho(new NodoABB(nodoOriginal.getIzquierdo().getElem(), null, null));
                clonInvertido(nodoOriginal.getIzquierdo(), nodoClon.getDerecho());
            }
            if (nodoOriginal.getDerecho() != null) {
                nodoClon.setIzquierdo(new NodoABB(nodoOriginal.getDerecho().getElem(), null, null));
                clonInvertido(nodoOriginal.getDerecho(), nodoClon.getIzquierdo());
            }
        }
    }

    private NodoABB buscarRaizNueva(NodoABB nodo, Comparable elem) {
        NodoABB nodoBuscado = null;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                nodoBuscado = nodo;
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                nodoBuscado = buscarRaizNueva(nodo.getIzquierdo(), elem);
            } else if (elem.compareTo(nodo.getElem()) > 0) {
                nodoBuscado = buscarRaizNueva(nodo.getDerecho(), elem);
            }
        }
        return nodoBuscado;
    }
    
    // Averiguar como recorrer lo minimo indispensable del arbol
    public Lista listarMayorIgual(Comparable elem) {
        /* Dado un elemento, devuelve una lista con los elementos mayores o
        iguales que elem, ordenados de mayor a menor */
        Lista lista = new Lista();
        listarMayorIgualAux(this.raiz, elem, lista);
        return lista;
    }
    
    private void listarMayorIgualAux(NodoABB nodo, Comparable elem, Lista lista) {
        if(nodo != null) {
            // Recorrido inorden 'invertido' (listado de mayor a menor)
            listarMayorIgualAux(nodo.getDerecho(), elem, lista);
            if(nodo.getElem().compareTo(elem) >= 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            listarMayorIgualAux(nodo.getIzquierdo(), elem, lista);
        }
    }
    
    // Averiguar como recorrer lo minimo indispensable del arbol
    public Lista listarMenores(Comparable elem) {
        /* Dado un elemento, devuelve una lista con los elementos estrictamente
        menores que elem, ordenados de menor a mayor */
        Lista lista = new Lista();
        listarMenoresAux(this.raiz, elem, lista);
        return lista;
    }
    
    private void listarMenoresAux(NodoABB nodo, Comparable elem, Lista lista) {
        if(nodo != null) {
            // Recorrido inorden (listado de menor a mayor)
            listarMenoresAux(nodo.getIzquierdo(), elem, lista);
            if(nodo.getElem().compareTo(elem) < 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            listarMenoresAux(nodo.getDerecho(), elem, lista);
            
        }
    }
    
    // Recu 2022
    public Lista listarMayoresQue(Comparable valor, Comparable elem) {
        /* Devuelve una lista con los elementos mayores que valor, del subarbol 
        con raiz elem. Si no existe elem en el arbol, devuelve la lista vacia */
        Lista lista = new Lista();
            NodoABB raizNueva = buscarRaiz(this.raiz, elem);
            llenarLista(raizNueva, valor, lista);
        return lista;
    }
    
    private void llenarLista(NodoABB nodo, Comparable valor, Lista lista) {
        if(nodo != null) {
            llenarLista(nodo.getIzquierdo(), valor, lista);
            if(nodo.getElem().compareTo(valor) > 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            llenarLista(nodo.getDerecho(), valor, lista);
        }
    }
    
    private NodoABB buscarRaiz(NodoABB nodo, Comparable elem) {
        NodoABB retorno = null;
        if(nodo != null) {
            if(elem.compareTo(nodo.getElem()) == 0) {
                retorno = nodo;
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                retorno = buscarRaiz(nodo.getIzquierdo(), elem);
            } else if (elem.compareTo(nodo.getElem()) > 0) {
                retorno = buscarRaiz(nodo.getDerecho(), elem);
            }
        }
        return retorno;
    }
}