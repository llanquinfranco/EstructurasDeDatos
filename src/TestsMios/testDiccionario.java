package testsMios;

import especiales.Diccionario;

/**
 *
 * @author Fran
 */
public class testDiccionario {

    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();
        
        diccionario.insertar("FAI-3199", "Llanquin, Franco");
        diccionario.insertar("FAI-3297", "Herrera, Jere");
        diccionario.insertar("FAI-3200", "Membrillo");
        diccionario.insertar("FAI-3700", "QCY");
        diccionario.insertar("FAI-3701", "No se que otro mas");
        
        System.out.println(diccionario.toString());
        
        System.out.println(diccionario.listarClaves().toString());
        System.out.println(diccionario.listarDatos().toString());
        
        //NO DEBE HABER SET CLAVE
        
        
    }
    
}
