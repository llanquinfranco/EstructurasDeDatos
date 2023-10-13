package TestsMios;

import lineales.dinamicas.Lista;

public class testLista {

    public static void main(String[] args) {
        Lista listita = new Lista();
        System.out.println("Esta vacia??  " + listita.esVacia());
        
        for (int i = 1; i <= 10; i++) {
            listita.insertar(i, i);
        }
        
        System.out.println("CLONADA? "+listita.clone().toString());
        System.out.println("que longitttuud ttiene?: (tiene q decir 10) " + listita.longitud());
        System.out.println(listita.toString());
        listita.eliminar(3);
        System.out.println("se sale la 3ra??? " + listita.toString());
        System.out.println("que longitttuud ttiene?: " + listita.longitud());
        System.out.println("Recupera el elemento 2?:" + listita.recuperar(2));
        System.out.println("Donde esta el elemento 500?: "+listita.localizar(500));
        
        listita.vaciar();
        System.out.println("Esta vacia che???" +listita.toString());
    }
}
