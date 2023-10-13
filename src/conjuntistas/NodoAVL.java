package conjuntistas;

/**
 *
 * @author Fran
 */
public class NodoAVL {
    
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    
    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;
    }
    
    public Comparable getElem() {
        return elem;
    }
    
    public void setElem(Comparable elem) {
        this.elem = elem;
    }
    
    public int getAltura() {
        return altura;
    }
    
    public void recalcularAltura() {
        this.altura = alturaAux(this); 
    }
    
    private int alturaAux(NodoAVL nodo) {
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
    
    public NodoAVL getIzquierdo() {
        return izquierdo;
    }
    
    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public NodoAVL getDerecho() {
        return derecho;
    }
    
    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }
}
