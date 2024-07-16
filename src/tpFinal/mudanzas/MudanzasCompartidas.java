package tpFinal.mudanzas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import tpFinal.estructuras.Diccionario;
import tpFinal.estructuras.GrafoEtiquetado;
import tpFinal.estructuras.Lista;
import tpFinal.estructuras.MapeoAMuchos;

/**
 *
 * @author Fran
 */
public class MudanzasCompartidas {

    private static final Diccionario ciudades = new Diccionario();
    private static final MapeoAMuchos solicitudes = new MapeoAMuchos();
    private static final GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
    private static final HashMap<String, Cliente> clientes = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);
    private static FileWriter logWriter;

    public static void main(String[] args) {
        // CARGAMOS LA INFORMACION
        boolean exito = cargarDatos();
        if (exito) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Bienvenido");
            System.out.println("Se ha cargado la informacion de 30 Ciudades, 20 Clientes, 40 Rutas entre esas Ciudades, y 20 Pedidos");
            // DESPUES DE CARGAR LA INFO, VAMOS AL MENU DE OPCIONES
            menu();
        }

    }

    public static boolean cargarDatos() {
        String archivo = "C:\\Users\\Asus\\Documents\\Facultad\\2do Año\\11 - Estructuras de Datos (2023)\\TPO Final\\DatosACargar.txt";
        String[] datos;
        boolean exito = true;
        inicializarLog();
        try {
            // LEEMOS LA INFO DESDE UN .TXT Y LA CARGAMOS A SU ESTRUCTURA CORRESPONDIENTE
            FileReader lector = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(lector);
            String linea;
            while ((linea = buffer.readLine()) != null) {
                datos = linea.split(";");
                switch (datos[0]) {
                    case "C":
                        cargarCiudad(Integer.parseInt(datos[1]), datos[2], datos[3]);
                        ;
                        break;
                    case "P":
                        cargarCliente(datos[1], Integer.parseInt(datos[2]), datos[3], datos[4], datos[5], datos[6]);
                        ;
                        break;
                    case "R":
                        cargarMapa(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Double.parseDouble(datos[3]));
                        ;
                        break;
                    case "S":
                        cargarSolicitud(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), datos[3], datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), datos[8], datos[9], datos[10]);
                }
            }
            buffer.close();
            // EN CASO DE QUE OCURRA UN ERROR MOSTRAMOS LA EXCEPCION Y NO SE EJECUTA EL MENU PRINCIPAL
        } catch (FileNotFoundException ex) {
            exito = false;
            System.err.println(ex.getMessage() + "El archivo al que intenta acceder no existe o la ruta es incorrecta");
        } catch (IOException ex) {
            exito = false;
            System.err.println(ex.getMessage() + "Error leyendo el archivo.");
        }
        return exito;
    }

    public static void cargarCiudad(int codigo, String nombre, String provincia) {
        // METODO QUE CARGA UNA CIUDAD AL AVL DICCIONARIO, Y AL GRAFO PARA EL MAPA DE RUTAS
        Ciudad ciudad = new Ciudad(codigo, nombre, provincia);
        boolean exito = ciudades.insertar(codigo, ciudad) && mapaRutas.insertarVertice(codigo);
        if (exito) {
            escribirEnLog("Se cargo la " + ciudad.toString());
        } else {
            escribirEnLog("No se pudo cargar la " + ciudad.toString());
        }
    }

    public static void cargarCliente(String tipoDoc, int numDoc, String apellido, String nombre, String telefono, String email) {
        // METODO QUE CARGA UN CLIENTE AL HASH MAP
        Cliente cliente = new Cliente(tipoDoc, numDoc, apellido, nombre, telefono, email);
        String clave = tipoDoc + numDoc;
        if (clientes.containsKey(clave)) {
            escribirEnLog("No se pudo cargar el " + cliente.toString());
        } else {
            clientes.put(clave, cliente);
            escribirEnLog("Se cargo el " + cliente.toString());
        }
    }

    public static void cargarMapa(int codigoOrigen, int codigoDestino, double etiqueta) {
        // METODO QUE CARGA UNA RUTA AL GRAFO ENTRE 2 CIUDADES EXISTENTES
        boolean exito = mapaRutas.existeVertice(codigoOrigen) && mapaRutas.existeVertice(codigoDestino);
        if (exito) {
            boolean yaExiste = mapaRutas.existeArco(codigoOrigen, codigoDestino);
            if (yaExiste) {
                escribirEnLog("No se pudo cargar la Ruta entre " + codigoOrigen + " y " + codigoDestino + ", ya que ya existe una ruta entre ambas");
            } else {
                mapaRutas.insertarArco(codigoOrigen, codigoDestino, etiqueta);
                escribirEnLog("Se cargo la Ruta de " + codigoOrigen + " a " + codigoDestino + " con una distancia de " + etiqueta + " kilometros");
            }
        } else {
            escribirEnLog("No se pudo cargar la Ruta entre " + codigoOrigen + " y " + codigoDestino + ", ya que una de las ciudades no esta en el sistema");
        }
    }

    public static void cargarSolicitud(int origen, int destino, String fechaSolicitud, String tipoDocumento, int numeroDocumento, int cantMetrosCubicos,
            int cantBultos, String domicilioRetiro, String domicilioEntrega, String estaPago) {
        // METODO QUE CARGA UN PEDIDO AL AVL MAPEO A MUCHOS ENTRE 2 CIUDADES EXISTENTES
        Solicitud solicitud = new Solicitud(origen, destino, fechaSolicitud, tipoDocumento,
                numeroDocumento, cantMetrosCubicos, cantBultos, domicilioRetiro, domicilioEntrega, estaPago);
        boolean exito = mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino);
        if (exito) {
            solicitudes.asociar(origen + "" + destino, solicitud);
            escribirEnLog("Se cargo el " + solicitud.toString());
        } else {
            escribirEnLog("Una de las ciudades no se encuentra en el sistema. Error al solicitar un pedido");
        }
    }

    public static void menu() {
        // MENU PRINCIPAL
        int opcion = 0;
        while (opcion != 10) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("MENU DE OPCIONES - MUDANZAS COMPARTIDAS");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Realizar ABM de Ciudades");
            System.out.println("2. Realizar ABM de la Red de Rutas");
            System.out.println("3. Realizar ABM de Clientes");
            System.out.println("4. Realizar ABM de Pedidos");
            System.out.println("5. Realizar consultas sobre Clientes");
            System.out.println("6. Realizar consultas sobre Ciudades");
            System.out.println("7. Realizar consultas sobre Viajes (Dada una Ciudad A y una Ciudad B)");
            System.out.println("8. Verificar Viajes");
            System.out.println("9. Mostrar el sistema");
            System.out.println("10. Finalizar el programa");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    ABMciudades();
                    break;
                case 2:
                    ABMrutas();
                    break;
                case 3:
                    ABMclientes();
                    break;
                case 4:
                    ABMpedidos();
                    break;
                case 5:
                    consultasClientes();
                    break;
                case 6:
                    consultasCiudades();
                    break;
                case 7:
                    consultasViajes();
                    break;
                case 8:
                    verificarViajes();
                    break;
                case 9:
                    mostrarSistema();
                    break;
                case 10:
                    finalizarLog();
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
                    break;
            }
        }
    }

    public static void ABMciudades() {
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 1. Realizar ABM de Ciudades");
        while (opcion != 4) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Insertar una nueva Ciudad");
            System.out.println("2. Eliminar una Ciudad existente");
            System.out.println("3. Modificar una Ciudad existente");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    insertarCiudad();
                    break;
                case 2:
                    eliminarCiudad();
                    break;
                case 3:
                    modificarCiudad();
                    break;
                case 4:
                    break; // SE CORTA EL BUCLE    
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void insertarCiudad() {
        // METODO QUE INSERTA UNA CIUDAD AL SISTEMA
        int codigo;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dar de alta una nueva Ciudad al Sistema");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese un codigo postal");
        codigo = sc.nextInt();
        // SI YA EXISTE UNA CIUDAD CON EL CP INGRESADO, NO SE PUEDE INSERTAR
        if (ciudades.existeClave(codigo)) {
            System.out.println("La ciudad que quiere ingresar ya existe. ERROR");
        } else {
            String nombre, provincia;
            System.out.println("Ingrese el nombre de la ciudad");
            nombre = sc.next();
            System.out.println("Ingrese el nombre de la provincia");
            provincia = sc.next();
            cargarCiudad(codigo, nombre, provincia);
        }
    }

    public static void eliminarCiudad() {
        // METODO QUE ELIMINA UNA CIUDAD DEL SISTEMA
        int codigo, i = 1;
        Lista lista;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dar de baja una Ciudad del sistema");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad a eliminar");
        codigo = sc.nextInt();
        // PARA ELIMINARLA, LA CIUDAD DEBE EXISTIR
        if (ciudades.existeClave(codigo)) {
            boolean seElimino = ciudades.eliminar(codigo) && mapaRutas.eliminarVertice(codigo);
            if (seElimino) {
                // ELIMINO LA CIUDAD EN LOS PEDIDOS
                lista = solicitudes.obtenerConjuntoDominio();
                while (i <= lista.longitud()) {
                    String dominio = (String) lista.recuperar(i);
                    if (dominio.contains(Integer.toString(codigo))) {
                        solicitudes.eliminar(dominio);
                    }
                    i++;
                }
                escribirEnLog("La Ciudad con codigo postal " + codigo + " se elimino del sistema");
            } else {
                escribirEnLog("NO se pudo eliminar la Ciudad con codigo postal " + codigo);
            }
        } else {
            System.out.println("La Ciudad con codigo postal " + codigo + " no existe. ERROR");
        }
    }

    public static void modificarCiudad() {
        // METODO QUE MODIFICA ATRIBUTOS DE UNA CIUDAD DEL SISTEMA
        // NO SE PUEDEN MODIFICAR LAS CLAVES
        int codigo;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Modificar una Ciudad del sistema");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad a modificar");
        codigo = sc.nextInt();
        // SI LA CIUDAD EXISTE, MODIFICAMOS EL ATRIBUTO QUE ELIJA EL USUARIO
        if (ciudades.existeClave(codigo)) {
            Ciudad ciudad = (Ciudad) ciudades.obtenerDato(codigo);
            int opcion = 0;
            String cadena;
            while (opcion != 3) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Ingrese una opcion");
                System.out.println("1. Modificar el nombre de la Ciudad con codigo postal " + codigo);
                System.out.println("2. Modificar la provincia de la Ciudad con codigo postal " + codigo);
                System.out.println("3. Volver al menu de ABMciudades");
                System.out.println("---------------------------------------------------------------------");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre de la Ciudad con codigo postal " + codigo);
                        cadena = sc.nextLine();
                        ciudad.setNombre(cadena);
                        escribirEnLog("La Ciudad con codigo postal " + codigo + " ahora se llama " + cadena);
                        break;
                    case 2:
                        System.out.println("Ingrese la nueva provincia de la Ciudad con codigo postal " + codigo);
                        cadena = sc.nextLine();
                        ciudad.setProvincia(cadena);
                        escribirEnLog("La Ciudad con codigo postal " + codigo + " ahora pertenece a la provincia de " + cadena);
                        break;
                    case 3:
                        break; // SE CORTA EL BUCLE
                    default:
                        System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
                }
            }
        } else {
            System.out.println("La Ciudad con codigo postal " + codigo + " no existe. ERROR");
        }
    }

    public static void ABMrutas() {
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 2. Realizar ABM de la Red de Rutas");
        while (opcion != 4) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Insertar una nueva Ruta");
            System.out.println("2. Eliminar una Ruta existente");
            System.out.println("3. Modificar una Ruta existente");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    insertarRuta();
                    break;
                case 2:
                    eliminarRuta();
                    break;
                case 3:
                    System.out.println("No es posible modificar la distancia de una Ciudad a otra");
                    System.out.println("Elimine una Ruta existente o Inserte una nueva Ruta");
                    ;
                    break;
                case 4:
                    break; // SE CORTA EL BUCLE    
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void insertarRuta() {
        // METODO QUE INSERTA UNA RUTA AL SISTEMA
        int origen, destino;
        double distancia;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Agregar una nueva Ruta entre dos Ciudades");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la primera Ciudad");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la segunda Ciudad");
        destino = sc.nextInt();
        // LAS DOS CIUDADES DEBEN EXISTIR PARA CREAR UNA RUTA ENTRE ELLAS
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            // ADEMAS, NO DEBE EXISTIR UNA RUTA ENTRE ELLAS
            if (mapaRutas.existeArco(origen, destino)) {
                System.out.println("Ya existe una Ruta que conecta las dos Ciudades ingresadas. ERROR");
            } else {
                System.out.println("Ingrese la distancia entre las dos Ciudades");
                distancia = sc.nextDouble();
                if (distancia <= 0) {
                    System.out.println("Ingrese una distancia valida");
                } else {
                    cargarMapa(origen, destino, distancia);
                }
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void eliminarRuta() {
        // METODO QUE ELIMINA UNA RUTA DEL SISTEMA
        int origen, destino;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Eliminar una Ruta existente entre dos Ciudades");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la primera Ciudad");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la segunda Ciudad");
        destino = sc.nextInt();
        // LAS DOS CIUDADES DEBEN EXISTIR PARA BORRAR LA RUTA ENTRE ELLAS
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            // ADEMAS, DEBE EXISTIR UNA RUTA ENTRE ELLAS
            if (mapaRutas.existeArco(origen, destino)) {
                if (mapaRutas.eliminarArco(origen, destino)) {
                    escribirEnLog("La Ruta que conecta a " + origen + " y " + destino + " fue eliminada con exito");
                } else {
                    escribirEnLog("La Ruta que conecta a " + origen + " y " + destino + " no se pudo eliminar");
                }
            } else {
                System.out.println("No existe ninguna Ruta que conecta estas dos Ciudades. ERROR");
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void ABMclientes() {
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 3. Realizar ABM de Clientes");
        while (opcion != 4) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Insertar un nuevo Cliente");
            System.out.println("2. Eliminar un Cliente existente");
            System.out.println("3. Modificar un Cliente existente");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    insertarCliente();
                    break;
                case 2:
                    eliminarCliente();
                    break;
                case 3:
                    modificarCliente();
                    break;
                case 4:
                    break; // SE CORTA EL BUCLE    
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void insertarCliente() {
        // METODO QUE INSERTA UN CLIENTE AL SISTEMA
        int numDoc;
        String tipoDoc;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dar de alta un nuevo Cliente al Sistema");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el tipo de documento del nuevo Cliente");
        tipoDoc = sc.next();
        System.out.println("Ingrese el numero de documento del nuevo Cliente");
        numDoc = sc.nextInt();
        // PARA INSERTARLO, NO DEBE EXISTIR UN CLIENTE CON LA MISMA CLAVE
        String clave = tipoDoc + numDoc;
        if (clientes.containsKey(clave)) {
            System.out.println("El Cliente con clave " + clave + " ya existe. ERROR");
        } else {
            sc.nextLine();
            String apellido, nombre, telefono, email;
            System.out.println("Ingrese el apellido del nuevo Cliente");
            apellido = sc.nextLine();
            System.out.println("Ingrese el nombre del nuevo Cliente");
            nombre = sc.nextLine();
            System.out.println("Ingrese el numero de telefono del nuevo Cliente");
            telefono = sc.nextLine();
            System.out.println("Ingrese la direccion de email del nuevo Cliente");
            email = sc.nextLine();
            cargarCliente(tipoDoc, numDoc, apellido, nombre, telefono, email);
        }
    }

    public static void eliminarCliente() {
        // METODO QUE ELIMINA UN CLIENTE DEL SISTEMA
        int numDoc;
        String tipoDoc;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dar de baja un Cliente del sistema");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el tipo de documento del Cliente a eliminar");
        tipoDoc = sc.next();
        System.out.println("Ingrese el numero de documento del Cliente a eliminar");
        numDoc = sc.nextInt();
        // PARA ELIMINARLO, EL CLIENTE DEBE EXISTIR
        String clave = tipoDoc + numDoc;
        if (clientes.containsKey(clave)) {
            boolean seElimino = clientes.remove(clave) != null;
            if (seElimino) {
                escribirEnLog("El Cliente con clave " + clave + " se elimino con exito del sistema");
            } else {
                escribirEnLog("NO se pudo eliminar al Cliente con clave " + clave);
            }
        } else {
            System.out.println("El Cliente con clave " + clave + " no existe. ERROR");
        }
    }

    public static void modificarCliente() {
        // METODO QUE MODIFICA ATRIBUTOS DE UN CLIENTE DEL SISTEMA
        // NO SE PUEDEN MODIFICAR LAS CLAVES
        int numDoc;
        String tipoDoc;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Modificar un Cliente del sistema");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el tipo de documento del Cliente a modificar");
        tipoDoc = sc.next();
        System.out.println("Ingrese un numero de documento del Cliente a modificar");
        numDoc = sc.nextInt();
        // SI EL CLIENTE EXISTE, MODIFICAMOS EL ATRIBUTO QUE EL USUARIO ELIJA
        String clave = tipoDoc + numDoc;
        if (clientes.containsKey(clave)) {
            Cliente cliente = (Cliente) clientes.get(clave);
            int opcion = 0;
            String cadena;
            while (opcion != 5) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Ingrese una opcion");
                System.out.println("1. Modificar el apellido del Cliente con numero de documento " + numDoc);
                System.out.println("2. Modificar el nombre del Cliente con numero de documento " + numDoc);
                System.out.println("3. Modificar el numero de telefono del Cliente con numero de documento " + numDoc);
                System.out.println("4. Modificar la direccion de email del Cliente con numero de documento " + numDoc);
                System.out.println("5. Volver al menu de ABMclientes");
                System.out.println("---------------------------------------------------------------------");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo apellido del Cliente con clave " + clave);
                        cadena = sc.nextLine();
                        cliente.setApellido(cadena);
                        escribirEnLog("El Cliente con clave " + clave + " ahora se apellida " + cadena);
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo nombre del Cliente con clave " + clave);
                        cadena = sc.nextLine();
                        cliente.setNombre(cadena);
                        escribirEnLog("El Cliente con clave " + clave + " ahora se llama " + cadena);
                        break;
                    case 3:
                        System.out.println("Ingrese el nuevo numero de telefono del Cliente con clave " + clave);
                        cadena = sc.nextLine();
                        cliente.setTelefono(cadena);
                        escribirEnLog("El Cliente con clave " + clave + " cambio de numero de telefono a " + cadena);
                        break;
                    case 4:
                        System.out.println("Ingrese la nueva direccion de email del Cliente con clave " + clave);
                        cadena = sc.nextLine();
                        cliente.setEmail(cadena);
                        escribirEnLog("El Cliente con clave " + clave + " cambio su direccion de email a " + cadena);
                        break;
                    case 5:
                        break; // SE CORTA EL BUCLE
                    default:
                        System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
                }
            }
        } else {
            System.out.println("El Cliente con clave " + clave + " no existe. ERROR");
        }
    }

    public static void ABMpedidos() {
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 4. Realizar ABM de Pedidos");
        while (opcion != 4) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Agregar un nuevo Pedido");
            System.out.println("2. Eliminar un Pedido existente");
            System.out.println("3. Modificar un Pedido existente");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    insertarPedido();
                    break;
                case 2:
                    eliminarPedido();
                    break;
                case 3:
                    modificarPedido();
                    break;
                case 4:
                    break; // SE CORTA EL BUCLE    
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void insertarPedido() {
        // METODO QUE INSERTA UN PEDIDO AL SISTEMA
        int origen, destino;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Agregar un nuevo Pedido entre Ciudades");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad origen");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la Ciudad destino");
        destino = sc.nextInt();
        // PARA INSERTARLO, LAS CIUDADES DEBEN EXISTIR
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            System.out.println("Ingrese el tipo de documento del Cliente");
            String tipoDoc = sc.next();
            System.out.println("Ingrese el numero de documento del Cliente");
            int numDoc = sc.nextInt();
            // ADEMAS, EL CLIENTE DEBE EXISTIR Y NO DEBE REALIZAR UN PEDIDO IGUAL
            String clave = tipoDoc + numDoc;
            if (clientes.containsKey(clave)) {
                System.out.println("Ingrese la fecha que solicito el Pedido");
                String fechaSolicitud = sc.next();
                if (solicitudes.obtenerPedido(origen + "" + destino, new Solicitud(origen, destino,
                        fechaSolicitud, tipoDoc, numDoc, 0, 0,
                        null, null, null)) != null) {
                    System.out.println("Ya existe un pedido en camino entre las Ciudades ingresadas, realizado por " + clave + " en esa fecha. ERROR");
                } else {
                    System.out.println("Ingrese la cantidad de metros cubicos que ocupa el Pedido");
                    int cantMetrosCubicos = sc.nextInt();
                    System.out.println("Ingrese la cantidad de bultos que ocupa el Pedido");
                    int cantBultos = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese el domicilio de retiro");
                    String domicilioRetiro = sc.nextLine();
                    System.out.println("Ingrese el domicilio de entrega");
                    String domicilioEntrega = sc.nextLine();
                    System.out.println("El envio esta pago? (T/F)");
                    String estaPago = sc.nextLine();
                    cargarSolicitud(origen, destino, fechaSolicitud, tipoDoc, numDoc, cantMetrosCubicos, cantBultos, domicilioRetiro, domicilioEntrega, estaPago);
                }
            } else {
                System.out.println("El Cliente con clave " + clave + " no existe. ERROR");
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void eliminarPedido() {
        // METODO QUE ELIMINA UN PEDIDO DEL SISTEMA
        int origen, destino, numDoc;
        String tipoDoc;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Eliminar un Pedido existente entre Ciudades");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad origen");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la Ciudad destino");
        destino = sc.nextInt();
        // PARA ELIMINARLO LAS CIUDADES DEBEN EXISTIR
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            System.out.println("Ingrese el tipo de documento del Cliente que quiere cancelar su Pedido");
            tipoDoc = sc.next();
            System.out.println("Ingrese el numero de documento del Cliente que quiere cancelar el Pedido");
            numDoc = sc.nextInt();
            // ADEMAS, EL CLIENTE Y EL PEDIDO DEBEN EXISTIR
            String clave = tipoDoc + numDoc;
            if (clientes.containsKey(clave)) {
                // ADEMAS, EL PEDIDO DEBE EXISTIR
                System.out.println("Ingrese la fecha que solicito el Pedido");
                String fechaSolicitud = sc.next();
                if (solicitudes.desasociar(origen + "" + destino, new Solicitud(origen, destino,
                        fechaSolicitud, tipoDoc, numDoc, 0, 0,
                        null, null, null))) {
                    escribirEnLog("Se cancelo el Pedido de " + clave + " entre " + origen + " y " + destino + " realizado el " + fechaSolicitud);
                } else {
                    System.out.println("El Pedido que quiere eliminar no existe");
                }
            } else {
                System.out.println("El Cliente con clave " + clave + " no existe. ERROR");
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void modificarPedido() {
        // METODO QUE MODIFICA ATRIBUTOS DE UN PEDIDO DEL SISTEMA
        // NO SE PUEDEN MODIFICAR LAS CLAVES
        int origen, destino, numDoc;
        String tipoDoc;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Modificar un Pedido existente entre Ciudades");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad origen");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la Ciudad destino");
        destino = sc.nextInt();
        // SI EL PEDIDO EXISTE, MODIFICAMOS EL ATRIBUTO QUE EL USUARIO ELIJA
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            System.out.println("Ingrese el tipo de documento del Cliente que quiere modificar su Pedido");
            tipoDoc = sc.next();
            System.out.println("Ingrese el numero de documento del Cliente que quiere modificar su Pedido");
            numDoc = sc.nextInt();
            String clave = tipoDoc + numDoc;
            if (clientes.containsKey(clave)) {
                System.out.println("Ingrese la fecha que solicito el Pedido");
                String fechaSolicitud = sc.next();
                Solicitud solicitud = (Solicitud) solicitudes.obtenerPedido(origen + "" + destino, new Solicitud(origen, destino,
                        fechaSolicitud, tipoDoc, numDoc, 0, 0,
                        null, null, null));
                if (solicitud != null) {
                    int opcion = 0;
                    String cadena;
                    int cantidad;
                    while (opcion != 6) {
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("Ingrese una opcion");
                        System.out.println("1. Modificar la cantidad de metros cubicos que ocupa el Pedido");
                        System.out.println("2. Modificar la cantidad de bultos que compone el Pedido");
                        System.out.println("3. Cambiar la direccion de retiro del Pedido");
                        System.out.println("4. Cambiar la direccion de entrega del Pedido");
                        System.out.println("5. Cambiar la condicion de pago en la que se encuentra el Pedido");
                        System.out.println("6. Volver al menu de ABMpedidos");
                        System.out.println("---------------------------------------------------------------------");
                        opcion = sc.nextInt();
                        sc.nextLine();
                        switch (opcion) {
                            case 1:
                                System.out.println("Ingrese la cantidad de metros cubicos que ahora ocupa el Pedido");
                                cantidad = sc.nextInt();
                                escribirEnLog("El Pedido " + solicitud.toString() + " ahora ocupa " + cantidad + " metros cubicos");
                                solicitud.setCantMetrosCubicos(cantidad);
                                break;
                            case 2:
                                System.out.println("Ingrese la cantidad de bultos que ahora compone el Pedido");
                                cantidad = sc.nextInt();
                                escribirEnLog("El Pedido " + solicitud.toString() + " ahora consta de " + cantidad + " bultos");
                                solicitud.setCantBultos(cantidad);
                                break;
                            case 3:
                                System.out.println("Ingrese la nueva direccion de retiro");
                                cadena = sc.nextLine();
                                escribirEnLog("El Pedido " + solicitud.toString() + " ahora se retirara en " + cadena);
                                solicitud.setDomicilioRetiro(cadena);
                                break;
                            case 4:
                                System.out.println("Ingrese la nueva direccion de entrega");
                                cadena = sc.nextLine();
                                escribirEnLog("El Pedido " + solicitud.toString() + " ahora se entregara en " + cadena);
                                solicitud.setDomicilioEntrega(cadena);
                                break;
                            case 5:
                                System.out.println("Ingrese la nueva condicion en la que se encuentra el Pedido (T/F)");
                                cadena = sc.nextLine();
                                escribirEnLog("El Pedido " + solicitud.toString() + " ahora se encuentra pagado?: " + cadena);
                                solicitud.setEstaPago(cadena);
                                break;
                            case 6:
                                break; // SE CORTA EL BUCLE
                            default:
                                System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
                        }
                    }
                } else {
                    System.out.println("El Pedido que quiere modificar no existe");
                }
            } else {
                System.out.println("El Cliente con clave " + clave + " no existe. ERROR");
            }
        } else {
            System.out.println("Una de las ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void consultasClientes() {
        // METODO QUE REALIZA CONSULTAS SOBRE LOS CLIENTES
        // SOLO HAY UNA OPCION, PARA REALIZARLA, EL CLIENTE DEBE EXISTIR
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 5. Realizar consultas sobre Clientes");
        while (opcion != 2) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Dada una clave de un Cliente (tipoDoc+numDoc), mostrar toda la información del mismo");
            System.out.println("2. Volver al menu principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    int numDoc;
                    String tipoDoc;
                    System.out.println("Ingrese un tipo de documento");
                    tipoDoc = sc.next();
                    System.out.println("Ingrese un numero de documento");
                    numDoc = sc.nextInt();
                    String clave = tipoDoc + numDoc;
                    if (clientes.containsKey(clave)) {
                        System.out.println("Informacion del Cliente solicitado: " + clientes.get(clave).toString());
                    } else {
                        System.out.println("No existe ningun Cliente con la clave " + clave);
                    }
                    break;
                case 2:
                    break;  // SE CORTA EL BUCLE
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void consultasCiudades() {
        // METODO QUE REALIZA CONSULTAS SOBRE LAS CIUDADES
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 6. Realizar consultas sobre Ciudades");
        while (opcion != 3) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Dado un codigo postal de una Ciudad, mostrar toda su informacion");
            System.out.println("2. Dado un prefijo, devolver todas las Ciudades cuyo código postal comienza con dicho prefijo");
            System.out.println("3. Volver al menu principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    mostrarInfoCiudad();
                    break;
                case 2:
                    prefijoCiudad();
                    break;
                case 3:
                    break; // SE CORTA EL BUCLE
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void mostrarInfoCiudad() {
        int codigo;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dado un codigo postal de una Ciudad, mostrar toda su informacion");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese un codigo postal");
        codigo = sc.nextInt();
        System.out.println("---------------------------------------------------------------------");
        // PARA REALIZAR LA CONSULTA, LA CIUDAD DEBE EXISTIR
        if (ciudades.existeClave(codigo)) {
            System.out.println("Informacion de la Ciudad solicitada: " + ciudades.obtenerDato(codigo).toString());
        } else {
            System.out.println("No existe ninguna Ciudad con el codigo postal " + codigo);
        }
    }

    public static void prefijoCiudad() {
        /*  SI EL PREFIJO ES 83 DEBERIA CONSIDERAR LISTAR TODAS LAS CIUDADES
            CUYO CODIGO POSTAL ESTE EN EL RANGO 8300 HASTA 8399 */
        // SI NO EXISTEN CIUDADES CON ESE PREFIJO, DEVUELVE LA LISTA VACIA Y ENVIAMOS UN MENSAJE POR PANTALLA
        int prefijo;
        Lista listaCiudades;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dado un prefijo, devolver todas las Ciudades cuyo codigo postal comienza con dicho prefijo");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese un prefijo");
        prefijo = sc.nextInt();
        listaCiudades = ciudades.listarRango(prefijo * 100, (prefijo * 100) + 99);
        System.out.println("---------------------------------------------------------------------");
        if (listaCiudades.esVacia()) {
            System.out.println("No existen Ciudades que el prefijo de su codigo postal sea " + prefijo);
        } else {
            System.out.println("Ciudades con prefijo " + prefijo + " en su codigo postal: " + listaCiudades.toString());
        }
    }

    public static void consultasViajes() {
        // METODO QUE REALIZA CONSULTAS SOBRE VIAJES
        int opcion = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 7. Realizar consultas sobre Viajes (Dada una Ciudad A y una Ciudad B)");
        while (opcion != 3) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Obtener el camino que llegue de A a B que pase por menos Ciudades");
            System.out.println("2. Obtener el camino que llegue de A a B de menor distancia en kilometros");
            System.out.println("3. Volver al Menu Principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    caminoMenosCiudades();
                    break;
                case 2:
                    caminoMenorDistancia();
                    break;
                case 3:
                    break; // SE CORTA EL BUCLE    
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void caminoMenosCiudades() {
        Lista camino;
        int origen, destino;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Obtener el camino que llegue de A a B que pase por menos Ciudades");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad A");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la Ciudad B");
        destino = sc.nextInt();
        System.out.println("---------------------------------------------------------------------");
        // PARA REALIZAR LA CONSULTA, LAS CIUDADES DEBEN EXISTIR Y DEBE HABER UN CAMINO ENTRE ELLAS
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            if (mapaRutas.existeCamino(origen, destino)) {
                camino = mapaRutas.caminoMasCorto(origen, destino);
                System.out.println("El camino entre " + origen + "y" + destino + " que pasa por menos Ciudades es " + camino.toString());
            } else {
                System.out.println("No existe un camino entre las Ciudades " + origen + "y" + destino);
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void caminoMenorDistancia() {
        Lista camino;
        int origen, destino;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Obtener el camino que llegue de A a B de menor distancia en kilometros");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad A");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la Ciudad B");
        destino = sc.nextInt();
        System.out.println("---------------------------------------------------------------------");
        // PARA REALIZAR LA CONSULTA, LAS CIUDADES DEBEN EXISTIR Y DEBE HABER UN CAMINO ENTRE ELLAS
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            if (mapaRutas.existeCamino(origen, destino)) {
                camino = mapaRutas.caminoMasRapido(origen, destino);
                System.out.println("El camino entre " + origen + "y" + destino + " de menor distancia en kilometros es " + camino.toString());
            } else {
                System.out.println("No existe un camino entre " + origen + "y" + destino);
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void verificarViajes() {
        // METODO QUE VERIFICA VIAJES
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 8. Verificar Viajes");
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Dada una Ciudad A y una ciudad B mostrar todos los pedidos de A a B y calcular cuanto espacio total hace falta en el camion");
            System.out.println("2. Dada una lista de Ciudades y una cantidad de metros cubicos que corresponden a capacidad del camion, verificar si es un “camino perfecto”");
            System.out.println("3. Volver al Menu Principal");
            System.out.println("---------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    mostrarTodosLosPedidos();
                    break;
                case 2:
                    caminoPerfecto();
                    break;
                case 3:
                    break; // SE CORTA EL BUCLE    
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void mostrarTodosLosPedidos() {
        int origen, destino, i, espacio = 0;
        Lista listaPedidos;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dada una Ciudad A y una Ciudad B, mostrar todos los pedidos de A a B y calcular cuanto espacio total hace falta en el camion");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese el codigo postal de la Ciudad A");
        origen = sc.nextInt();
        System.out.println("Ingrese el codigo postal de la Ciudad B");
        destino = sc.nextInt();
        System.out.println("---------------------------------------------------------------------");
        // LAS CIUDADES DEBEN EXISTIR
        if (mapaRutas.existeVertice(origen) && mapaRutas.existeVertice(destino)) {
            listaPedidos = solicitudes.obtenerValores(origen + "" + destino);
            if (listaPedidos.esVacia()) {
                System.out.println("No existen pedidos desde " + origen + " a " + destino);
            } else {
                System.out.println("Todos los pedidos de " + origen + " a " + destino);
                System.out.println(listaPedidos.toString());
                for (i = 1; i <= listaPedidos.longitud(); i++) {
                    Solicitud solicitud = (Solicitud) listaPedidos.recuperar(i);
                    espacio = espacio + solicitud.getCantMetrosCubicos();
                }
                System.out.println("El espacio total que hace falta en el camion es de " + espacio + "m3");
            }
        } else {
            System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
        }
    }

    public static void caminoPerfecto() {
        int capacidad, codigo, longitud, i = 1;
        boolean exito = true;
        Lista listaCodigo = new Lista();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Dada una lista de Ciudades y una cantidad de metros cubicos que corresponden a capacidad del camion, verificar si es un “camino perfecto”");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Ingrese la cantidad de metros cubicos de capacidad del camion");
        capacidad = sc.nextInt();
        System.out.println("Ingrese una cantidad de Ciudades");
        longitud = sc.nextInt();
        if (longitud > 1) {
            while (i <= longitud && exito) {
                System.out.println("Ingrese el codigo postal de la Ciudad a insertar en la Lista");
                codigo = sc.nextInt();
                if (mapaRutas.existeVertice(codigo)) {
                    listaCodigo.insertar(codigo, i);
                } else {
                    exito = false;
                }
                i++;
            }
            if (exito) {
                verificarCaminoPerfecto(listaCodigo, capacidad);
            } else {
                System.out.println("Una de las Ciudades ingresadas no existe en el sistema. ERROR");
            }
        } else {
            System.out.println("Ingrese un numero valido de Ciudades");
        }
    }

    public static void verificarCaminoPerfecto(Lista lista, int capacidad) {
        int i = 1, ocupacion = 0;
        boolean exito = true;
        while (i < lista.longitud() && exito) {
            int origen = (int) lista.recuperar(i), destino = (int) lista.recuperar(i + 1);
            if (mapaRutas.existeArco(origen, destino)) {
                Lista listaPedidos = solicitudes.obtenerValores(origen + "" + destino);
                if (listaPedidos.esVacia()) {
                    System.out.println("No es camino perfecto ya que no existen Pedidos entre " + origen + " y " + destino);
                    exito = false;
                } else {
                    // SI EXISTE UN PEDIDO ENTRE A Y B ACUMULO LOS M3 QUE OCUPA
                    Solicitud solicitud = (Solicitud) listaPedidos.recuperar(1);
                    ocupacion = ocupacion + solicitud.getCantMetrosCubicos();
                }
            } else {
                exito = false;
                System.out.println("No es camino perfecto ya que no existe una ruta entre la Ciudad " + origen + " y " + destino);
            }
            i++;
        }
        if (exito) {
            if (capacidad >= ocupacion) {
                System.out.println("Es camino perfecto");
            } else {
                System.out.println("No es camino perfecto ya que la capacidad del camion no soporta todos los pedidos");
            }
        }
    }

    public static void mostrarSistema() {
        int opcion = 0;
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 9. Mostrar el sistema");
        while (opcion != 6) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Mostrar la estructura Diccionario que almacena la informacion de las Ciudades");
            System.out.println("2. Mostrar la estructura HashMap que almacena la informacion de los Clientes");
            System.out.println("3. Mostrar la estructura Grafo Etiquetado que almacena el Mapa de Rutas");
            System.out.println("4. Mostrar la estructura Mapeo A Muchos que almacena los Pedidos");
            System.out.println("5. Mostrar todas las estructuras");
            System.out.println("6. Volver al Menu Principal");
            System.out.println("-----------------------------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    mostrarCiudades();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3:
                    mostrarRutas();
                    break;
                case 4:
                    mostrarPedidos();
                    break;
                case 5:
                    mostrarTodo();
                    break;
                case 6:
                    break; // SE CORTA EL BUCLE
                default:
                    System.out.println("Opcion invalida. Por favor ingrese una opcion valida");
            }
        }
    }

    public static void mostrarCiudades() {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 1. Mostrar la estructura Diccionario que almacena la informacion de las Ciudades");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println(ciudades.toString());
    }

    public static void mostrarClientes() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 2. Mostrar la estructura HashMap que almacena la informacion de los Clientes");
        System.out.println("----------------------------------------------------------------------------------------------------");
        mostrarClientesAlmacenados();
    }

    public static void mostrarRutas() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 3. Mostrar la estructura Grafo Etiquetado que almacena el Mapa de Rutas");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println(mapaRutas.toString());

    }

    public static void mostrarPedidos() {
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("Se selecciono la opcion 4. Mostrar la estructura Mapeo A Muchos que almacena los Pedidos");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println(solicitudes.toString());
    }

    public static void mostrarTodo() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Se selecciono la opcion 5. Mostrar todas las estructuras");
        System.out.println("--------------------------------------------------------");
        System.out.println("Diccionario que almacena las Ciudades");
        System.out.println(ciudades.toString());
        System.out.println("--------------------------------------------------------");
        System.out.println("HashMap que almacena los Clientes");
        mostrarClientesAlmacenados();
        System.out.println("--------------------------------------------------------");
        System.out.println("Grafo Etiquetado que almacena el Mapa de Rutas");
        System.out.println(mapaRutas.toString());
        System.out.println("--------------------------------------------------------");
        System.out.println("Mapeo A Muchos que almacena los Pedidos");
        System.out.println(solicitudes.toString());
    }

    public static void inicializarLog() {
        String rutaLog = "C:\\Users\\Asus\\Documents\\Facultad\\2do Año\\11 - Estructuras de Datos (2023)\\TPO Final\\log.txt";
        try {
            logWriter = new FileWriter(rutaLog, true); // true para permitir agregar registros al archivo existente
            logWriter.write("Inicio del registro: \n");
            logWriter.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Error al inicializar el archivo log.");
        }
    }

    public static void escribirEnLog(String mensaje) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaHora = formatoFecha.format(new Date());
            logWriter.write(fechaHora + " - " + mensaje + "\n");
            logWriter.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Error al escribir en el archivo log.");
        }
    }

    public static void finalizarLog() {
        try {
            logWriter.write("Fin del registro");
            logWriter.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Error al finalizar el archivo log.");
        }
    }

    public static void mostrarClientesAlmacenados() {
        for (HashMap.Entry<String, Cliente> entry : clientes.entrySet()) {
            String clave = entry.getKey();
            Cliente valor = entry.getValue();
            System.out.println(clave + " | " + valor.toString());
        }
    }

}