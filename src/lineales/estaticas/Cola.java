package lineales.estaticas;

/**
 *
 * @author Fran
 */
public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        this.arreglo = new Object[Cola.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object elemento) {
        boolean exito;
        if (estaLlena()) {
            exito = false;
        } else {
            this.arreglo[this.fin] = elemento;
            this.fin = (this.fin + 1) % TAMANIO;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito;
        if (esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elemFrente;
        if (esVacia()) {
            elemFrente = null;
        } else {
            elemFrente = this.arreglo[this.frente];
        }
        return elemFrente;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        while (!esVacia()) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }
    }

    public Cola clone() {
        Cola colaClon = new Cola();
        if (!esVacia()) {
            int i = this.frente;
            while (i != this.fin) {
                colaClon.arreglo[i] = this.arreglo[i];
                i = (i + 1) % 10;
            }
        }
        colaClon.frente = this.frente;
        colaClon.fin = this.fin;
        return colaClon;
    }

    @Override
    public String toString() {
        int i = this.frente;
        String cadena = "";
        while (i != fin) {
            cadena = cadena + this.arreglo[i];
            i = (i + 1) % 10;
            if (i != fin) {
                cadena = cadena + ",";
            }
        }
        cadena = "[" + cadena + "]";
        return cadena;
    }

    private boolean estaLlena() {
        return (this.fin + 1) % TAMANIO == frente;
    }

}
