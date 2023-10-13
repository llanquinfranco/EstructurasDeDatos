package TestsMios;

import especiales.MapeoAMuchos;

/**
 *
 * @author Fran
 */
public class testMapeoAMuchos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numDoc = 43759307;
        String tipoDoc = "DNI";
        String clave = tipoDoc + numDoc;
        System.out.println(clave);
        
        MapeoAMuchos mapa = new MapeoAMuchos();
        Solicitud solicitud = new Solicitud(8300, 8316, "Hoy", 
                "DNI", 43759307, 10,  10, 
                "Aca", "Alla", "SI");
        
        mapa.asociar("Fran", solicitud);
        
        
        System.out.println(mapa.toString());
        
        
        Solicitud otrasolicitud = new Solicitud(8300, 8316, null, 
                "DNI", 43759307, 0,  0, 
                null, null, null);
        
        mapa.desasociar("Fran", otrasolicitud);
        
        System.out.println(mapa.toString());
    }
    
}
