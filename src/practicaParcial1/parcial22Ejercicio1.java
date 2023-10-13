package practicaParcial1;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Fran
 */
public class parcial22Ejercicio1 {

    public static void main(String[] args) {
        Cola colaElementos = new Cola();
        colaElementos.poner('A');
        colaElementos.poner('B');
        colaElementos.poner('C');
        colaElementos.poner('B');
        colaElementos.poner('A');
        colaElementos.poner('$');
        colaElementos.poner('C');
        colaElementos.poner('D');
        colaElementos.poner('D');
        colaElementos.poner('E');
        colaElementos.poner('$');
        colaElementos.poner('A');
        colaElementos.poner('F');
        colaElementos.poner('C');
        colaElementos.poner('C');
        colaElementos.poner('F');
        colaElementos.poner('A');
        System.out.println(colaElementos.toString());
        System.out.println("Cantidad de secuencias capicuas: " + cuentaSecuencias(colaElementos));
    }
    
    public static int cuentaSecuencias(Cola colaElementos) {
        Cola colaClonada = colaElementos.clone(); //para no perder info original
        Lista listaAux = new Lista();
        int j, cantCapicuas = 0;
        boolean seCumple;
        colaClonada.poner('$'); //para q tome la ultima sucesion
        while(!colaClonada.esVacia()) {
            if(!colaClonada.obtenerFrente().equals('$')) {
                listaAux.insertar(colaClonada.obtenerFrente(), listaAux.longitud() + 1);
                colaClonada.sacar();
            } else {
                colaClonada.sacar();
                seCumple = true;
                while(seCumple && !listaAux.esVacia()) {
                    j = listaAux.longitud();
                    if(listaAux.recuperar(1).equals(listaAux.recuperar(j))){
                        seCumple = true;
                        listaAux.eliminar(j);
                        listaAux.eliminar(1);
                    } else {
                        seCumple = false;
                        listaAux.vaciar();
                    }
                }
                if(seCumple) {
                    cantCapicuas++;
                }
            }
        }
        return cantCapicuas;
    }
    
}
