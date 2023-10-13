package lineales.estaticas;

/**
 *
 * @author Fran
 */
public class Pila {

    private final int tamanio = 10;
    private int tope;
    private final Object[] arreglo;

    public Pila() {
        this.arreglo = new Object[tamanio];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito;
        if (this.tope + 1 >= this.tamanio) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito;
        if (this.tope < 0) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object elemTope;
        if (this.tope >= 0) {
            elemTope = this.arreglo[tope];
        } else {
            elemTope = null;
        }
        return elemTope;
    }

    public boolean esVacia() {
        boolean noTieneElem;
        noTieneElem = this.tope < 0;
        return noTieneElem;
    }

    public void vaciar() {
        while (this.tope >= 0) {
            this.arreglo[tope] = null;
            this.tope--;
        }
    }

    @Override
    public Pila clone() {
        Pila nuevaPila = new Pila();
        for (int i = 0; i <= this.tope; i++) {
            nuevaPila.arreglo[i] = this.arreglo[i];
        }
        nuevaPila.tope = this.tope;

        return nuevaPila;
    }

    @Override
    public String toString() {
        String cadena = "[";
        for (int i = 0; i <= tope; i++) {
            cadena = cadena + this.arreglo[i];
            if(i < tope){
                cadena = cadena + ",";
            }
        }
        return cadena + "]";
    }
}
// "Pos " + i + ": " + 