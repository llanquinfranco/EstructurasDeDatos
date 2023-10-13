package lineales.dinamicas;

/**
 *
 * @author Fran
 */
public class Pila {
    
    private Nodo tope;

    public Pila () {
        this.tope = null;
    }

    public boolean apilar (Object elemento) {
        Nodo nuevoNodo = new Nodo (elemento, tope);
        this.tope = nuevoNodo;
        return true;
    }
    
    public boolean desapilar () {
        boolean exito;
        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }
    
    public Object obtenerTope(){
        Object elementoTope;
        if(this.tope == null){
            elementoTope = null;
        } else {
            elementoTope = this.tope.getElem();
        }
        return elementoTope;
    }
    
    public boolean esVacia () {
        return this.tope == null;
    }
    
    public void vaciar () {
        this.tope = null;
    }
    
    @Override
    public String toString () {
        String cadena = "";
        Nodo aux = this.tope;
        if(this.tope == null) {
            cadena = "La pila está vacía";
        } else {
            while (aux != null) {
                cadena = aux.getElem().toString() + cadena;
                aux = aux.getEnlace();
                if(aux != null){
                    cadena = "," + cadena;
                }
            }
        }
        return "[" + cadena + "]";
    }
    
    @Override
    public Pila clone () {
        Pila pilaClon = new Pila ();
        cloneAux(pilaClon, this.tope);
        return pilaClon;
    }
    
    private void cloneAux (Pila pilaClon, Nodo nodo) {
        if (nodo != null) {
            cloneAux(pilaClon, nodo.getEnlace());
            pilaClon.tope = new Nodo(nodo.getElem(), pilaClon.tope);
        }
    }
    
    public boolean equals(Pila p) {
        boolean esIgual = true;
        Nodo aux1 = this.tope;
        Nodo aux2 = p.tope;
        if((aux1 == null && aux2 != null) || (aux1 != null && aux2 == null)) {
            esIgual = false;
        }
        while(aux1 != null && esIgual){
            if(aux1.getElem().equals(aux2.getElem())) {
                aux1 = aux1.getEnlace();
                aux2 = aux2.getEnlace();
            } else {
                esIgual = false;
            }
        }
        return esIgual;
    }
}
