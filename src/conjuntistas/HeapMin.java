package conjuntistas;

/**
 *
 * @author Fran
 */
public class HeapMin {

    private Comparable[] heap;
    private int ultimo = 0;
    private int TAMANIO = 20;

    public HeapMin() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable elemento) {
        /* Recibe un elemento y lo inserta en el árbol. Si la operación termina 
        con éxito, devuelve verdadero y falso en caso contrario. Nota: Los 
        arboles heap aceptan elementos repetidos */
        boolean exito = false;
        if (this.ultimo + 1 == this.TAMANIO) {
            exito = false;
        } else {
            this.heap[this.ultimo + 1] = elemento;
            this.ultimo++;
            hacerSubir(this.ultimo);
        }
        return exito;
    }

    private void hacerSubir(int posHijo) {
        int posPadre;
        Comparable aux = this.heap[posHijo];
        boolean salir = false;
        while (!salir) {
            posPadre = posHijo / 2;
            if (posPadre >= 1) {
                // compara al hijo con el padre
                if (this.heap[posPadre].compareTo(aux) < 0) {
                    // si el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                } else {
                    // si el hijo es menor que el padre, los intercambia
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = aux;
                    posHijo = posPadre;
                }
            } else {
                // aux es raiz, esta bien ubicado
                salir = true;
            }
        }
    }

    public boolean eliminarCima() {
        /* Elimina el elemento de la raiz (o cima del monticulo). Si la operacion
        termina con exito devuelve verdadero y falso si el arbol estaba vacio */
        boolean exito;
        if (this.ultimo == 0) {
            exito = false; //Arbol vacio
        } else {
            // Saco la raiz y en su lugar, pongo la ultima hoja del arbol
            this.heap[1] = this.heap[ultimo];
            this.heap[ultimo] = null;
            this.ultimo--;
            // Hacemos cumplir la propiedad de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posHijo;
        Comparable aux = this.heap[posPadre];
        boolean salir = false;
        while (!salir) {
            posHijo = posPadre * 2;
            if (posHijo <= this.ultimo) {
                // aux tiene al menos un hijo (izq) y lo considera menor
                if (posHijo < this.ultimo) {
                    // hijoMenor tiene hermano derecho
                    if (this.heap[posHijo + 1].compareTo(this.heap[posHijo]) < 0) {
                        // el hijo derecho es el menor de los dos
                        posHijo++;
                    }
                }
                // compara al hijo menor con el padre
                if (this.heap[posHijo].compareTo(aux) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = aux;
                    posPadre = posHijo;
                } else {
                    // el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // aux es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public Object recuperarCima() {
        // Devuelve el elemento que esta en la raiz. (El arbol no esta vacio)
        Object elemento;
        if (this.ultimo == 0) {
            elemento = null;
        } else {
            elemento = this.heap[1];
        }
        return elemento;
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    @Override
    public HeapMin clone() {
        HeapMin heapMinClon = new HeapMin();
        int i = 0;
        while(i <= this.ultimo) {
            heapMinClon.heap[i] = this.heap[i];
            i++;
        }
        heapMinClon.ultimo = this.ultimo;
        return heapMinClon;
    }
    
    public void vaciar() {
        while (this.ultimo >= 0) {
            this.heap[this.ultimo] = null;
            this.ultimo--;
        }
    }

    @Override
    public String toString() {
        int i = 1;
        String cadena = "";
        if (this.ultimo == 0) {
            cadena = "El arbol esta vacio";
        } else {
            while (i <= this.ultimo) {
                cadena = cadena + this.heap[i];
                i++;
                if (i <= this.ultimo) {
                    cadena = cadena + ",";
                }
            }
            cadena = "[" + cadena + "]";
            for (i = 1; i <= this.ultimo; i++) {
                cadena = cadena + "\n NODO " + this.heap[i] + " ";
                // Hijo Izquierdo
                if (this.heap[i * 2] != null) {
                    cadena = cadena + " HI: " + this.heap[i * 2] + " ";
                } else {
                    cadena = cadena + " HI: - ";
                }
                // Hijo Derecho
                if (this.heap[(i * 2) + 1] != null) {
                    cadena = cadena + " HD: " + this.heap[(i * 2) + 1] + " ";
                } else {
                    cadena = cadena + " HD: - ";
                }
            }
        }
        return cadena;
    }
}