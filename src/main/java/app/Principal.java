package app;

import Controller.dao.BDPDAO;
import Controller.dao.BusesDAO;
import Controller.dao.ConductoresDAO;
import Controller.dao.LugaresDAO;
import model.BDP;
import model.Bus;
import model.Conductor;
import model.Lugar;
import view.DriverView;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ArrayList<Conductor> conductores = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();
        ConductoresDAO driverDAO = new ConductoresDAO();

        int op = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("----Bienvenido al sistema de AUCORSA----");
        do {
            System.out.println("¿Qué desea hacer?");
            System.out.println("\t0. Salir.");
            System.out.println("\t1. Consultar conductor a partir de nº de conductor.");
            System.out.println("\t2. Insertar conductores.");
            System.out.println("\t3. Eliminar conductor.");
            System.out.println("\t4. Consultar conductores de un autobus a partir del nº de registro de autobus.");
            System.out.println("\t5. Insertar autobus.");
            System.out.println("\t6. Eliminar autobus.");
            System.out.println("\t7. Insertar lugar.");
            System.out.println("\t8. Eliminar lugar.");
            System.out.println("\t9. Insertar ruta.");
            System.out.println("\t10. Eliminar ruta.");
            System.out.println("\t11. Consultar día de la semana de una ruta a partir de la ciudad");
            System.out.println("\t12. Actualizar ruta según el día de la semana.");

            System.out.print("Introduce tu opción: ");
            // Cambio realizado: validamos que el usuario introduzca un entero
            if (!sc.hasNextInt()) {
                System.out.println("Introduce una opción válida (número).");
                sc.next(); // descartamos el token inválido
                continue;
            }
            op = sc.nextInt();
            switch (op) {
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    System.out.println("Introduce el numero de conductor a consultar.");
                    // Cambio realizado: validamos la entrada antes de leer
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }
                    int numConductor = sc.nextInt();

                    // Cambio realizado: llamamos al DAO pasando el id y recogemos el resultado
                    Conductor c = driverDAO.consultarConductor(numConductor);

                    if (c != null) {
                        // Cambio realizado: añadimos el objeto Conductor a la lista correctamente
                        conductores.add(c);
                        System.out.println("Conductor encontrado: " + c);
                    } else {
                        System.out.println("Conductor no encontrado con id: " + numConductor);
                    }
                    break;
                case 2:
                    System.out.println("Introduce primero el número de conductor a insertar: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }
                    int idConductor = sc.nextInt();
                    //Añadido sc.nextLine() para que no salte el siguiente input
                    sc.nextLine();
                    System.out.println("Introduce el nombre del conductor: ");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce los apellidos del conductor: ");
                    String apellidos = sc.nextLine();
                    Conductor nuevoConductor = new Conductor(idConductor ,nombre, apellidos);

                    //Declaro Conductor 'creado' fuera del bloque try-catch para evitar errores
                    Conductor creado = null;
                    try {
                        creado = ConductoresDAO.insertarConductor(nuevoConductor);
                    }catch (Exception e) {
                        System.out.println("Error al insertar el conductor: " + e.getMessage());
                        break;
                    }
                    if (creado != null) {
                        System.out.println("Conductor insertado correctamente: " + creado);
                    } else {
                        System.out.println("Error al insertar el conductor.");
                    }

                    break;
                case 3:
                    System.out.println("Introduce el número de conductor a eliminar: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }

                    int idConductorBorrar = sc.nextInt();

                    boolean borrado=false;
                    try {
                        borrado = ConductoresDAO.borrarConductor(idConductorBorrar);
                    } catch (Exception e) {
                        System.out.println("Error: Conductor no borrado: "+ e.getMessage());
                    }
                    if (borrado){
                        System.out.println("Conductor borrado correctamente.");
                    } else {
                        System.out.println("CONDUCTOR NO ELIMINADO: no se ha encontrado un conductor con el ID: "+idConductorBorrar);
                    }
                    break;

                case 4:
                    System.out.println("Introduce el número de registro");
                    String registroBus = sc.nextLine();
                    registroBus =sc.next();

                    Conductor infoConductorBus = BusesDAO.consultarConductorBus(registroBus);

                    if (infoConductorBus != null) {
                        System.out.println("Conductor de este bus encontrado: "+infoConductorBus);
                    }else {
                        System.out.println("No se encontrado un conductor del autobus con registro: " + registroBus);
                    }

                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("Introduce el número de registro del autobus a registrar: ");
                    String registroNewBus =  sc.nextLine();

                    String tipoNewBus = "";
                    boolean tipovalido=false;
                    do {
                        System.out.println("Introduce un tipo de autobus válido (Urbano, Interurbano, Turismo o Escolar): ");
                        tipoNewBus = sc.nextLine();
                        if(tipoNewBus.equalsIgnoreCase("Urbano") ||
                                tipoNewBus.equalsIgnoreCase("Interurbano") ||
                                tipoNewBus.equalsIgnoreCase("Turismo") ||
                                tipoNewBus.equalsIgnoreCase("Escolar")) {
                            tipovalido=true;
                        }
                    }while(!tipovalido);

                    System.out.println("Introduce la licencia del autobus: ");
                    String licenciaNewBus = sc.next();

                    Bus nuevosBusInsert = new Bus(registroNewBus, tipoNewBus, licenciaNewBus);

                        Bus busInsertado = null;
                        try{
                            busInsertado = BusesDAO.insertarBus(nuevosBusInsert);
                        } catch (Exception e) {
                            System.out.println("Error al insertar el autobus: " + e.getMessage());
                            break;
                        }

                        if (busInsertado != null) {
                            System.out.println("Autobus insertado correctamente: " + busInsertado);
                        } else{
                            System.out.println("Error al insertar el autobus.");
                        }

                    break;
                case 6:
                    sc.nextLine();
                    System.out.println("Introduce el número de registro del autobus a eliminar: ");
                    String registroBusBorrar = sc.nextLine();

                    boolean busBorrado = false;
                    try {
                        busBorrado = BusesDAO.borrarBus(registroBusBorrar);
                    } catch (Exception e) {
                        System.out.println("Error: Autobus no borrado: "+ e.getMessage());
                    }

                    if (busBorrado) {
                        System.out.println("Autobus borrado correctamente.");
                    } else {
                        System.out.println("AUTOBUS NO ELIMINADO: no se ha encontrado un autobus con el número de registro: "+registroBusBorrar);
                    }

                    break;

                case 7:
                    System.out.print("Introduce ID del lugar a insertar: ");
                    if (!sc.hasNextInt()){
                        System.out.println("Introduce un número de lugar válido.");
                        sc.next();
                        break;
                    }
                    int idLugar = sc.nextInt();

                    System.out.print("\nIntroduce el código postal del lugar a insertar: ");
                    if (!sc.hasNextInt()){
                        System.out.println("Introduce un código postal válido.");
                        sc.next();
                        break;
                    }
                    sc.nextLine();
                    String cpLugar = sc.nextLine();

                    System.out.print("\nIntroduce el nombre de la ciudad correspondiente al lugar a insertar: ");
                    String ciudadLugar = sc.nextLine();

                    System.out.print("\nIntroduce el sitio del lugar a insertar: ");
                    String siteLugar = sc.nextLine();

                    Lugar lugarInsert = new Lugar(idLugar, cpLugar, ciudadLugar, siteLugar);

                    Lugar lugarInsertado = null;

                    try{
                        lugarInsertado = LugaresDAO.insertarLugar(lugarInsert);
                    } catch (Exception e) {
                        System.out.println("Error al insertar el lugar: " + e.getMessage());
                        break;
                    }
                        if (lugarInsertado != null) {
                            System.out.println("Lugar insertado correctamente: "+lugarInsertado);
                        }else {
                            System.out.println("Error al insertar el lugar.");
                        }
                    break;
                case 8:
                    System.out.println("Introduce el ID del lugar a eliminar: ");
                    if (!sc.hasNextInt()){
                        System.out.println("Introduce un número de lugar válido.");
                        sc.next();
                        break;
                    }
                    int idLugarABorrar=sc.nextInt();
                    boolean lugarBorrado = false;
                    try {
                        lugarBorrado = LugaresDAO.borrarLugar(idLugarABorrar);
                    }catch (Exception e) {
                        System.out.println("Error: Lugar no borrado: "+ e.getMessage());
                    }
                    if (lugarBorrado){
                        System.out.println("Lugar borrado correctamente.");
                    } else {
                        System.out.println("LUGAR NO ELIMINADO: no se ha encontrado un lugar con el ID: "+idLugarABorrar);
                    }
                    break;

                case 9:
                    System.out.println("Introduce el registro del autobus para la ruta a insertar: ");
                    sc.nextLine();
                    String registroRuta = sc.nextLine();

                    System.out.println("Introduce el número de conductor para la ruta a insertar: ");
                    if (!sc.hasNextInt()){
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }
                    int numConductorRuta = sc.nextInt();

                    System.out.println("Introduce el ID del lugar para la ruta a insertar: ");
                    if (!sc.hasNextInt()){
                        System.out.println("Introduce un número de lugar válido.");
                        sc.next();
                        break;
                    }
                    int idLugarRuta = sc.nextInt();

                    System.out.println("Introduce el día de la semana para la ruta a insertar: ");
                    sc.nextLine();
                    String diaSemanaRuta = sc.nextLine();

                    BDP rutaInsertar = new BDP(registroRuta, numConductorRuta, idLugarRuta, diaSemanaRuta);

                    try{
                        BDP rutaInsertada = BDPDAO.insertarRuta(rutaInsertar);
                    } catch (Exception e) {
                        System.out.println("Error al insertar la ruta: " + e.getMessage());
                        break;
                    }

                    if (rutaInsertar != null){
                        System.out.println("Ruta insertada correctamente: "+rutaInsertar);
                    } else {
                        System.out.println("Error al insertar la ruta.");
                    }

                    break;

                case 10:
                    System.out.println("Introduce el número de conductor de la ruta a eliminar: ");
                    if (!sc.hasNextInt()){
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }
                    int numConductorRutaBorrar = sc.nextInt();

                    System.out.println("Introduce el ID del lugar de la ruta a eliminar: ");
                    if(!sc.hasNextInt()){
                        System.out.println("Introduce un número de lugar válido.");
                        sc.next();
                        break;
                    }
                    int idLugarRutaBorrar = sc.nextInt();

                    System.out.println("Introduce el registro del autobus que recorre la ruta a eliminar: ");
                    sc.nextLine();
                    String registroRutaBorrar = sc.nextLine();

                    boolean rutaBorrada = false;
                    try{
                        rutaBorrada = BDPDAO.borrarRuta(numConductorRutaBorrar, idLugarRutaBorrar, registroRutaBorrar);
                    } catch (Exception e) {
                        System.out.println("Error al borrar la ruta: " + e.getMessage());
                    }

                    if (rutaBorrada){
                        System.out.println("Ruta borrada correctamente.");
                    } else {
                        System.out.println("RUTA NO ELIMINADA: no se ha encontrado una ruta con el número de conductor: "+numConductorRutaBorrar+", ID de lugar: "+idLugarRutaBorrar+" y registro de autobus: "+registroRutaBorrar);
                    }

                    break;

                case 11:
                    System.out.println("Introduce la ciudad para consultar el día de la semana de una ruta: ");
                    sc.nextLine();
                    String ciudadConsultarDiaRuta = sc.nextLine();
                    String diaSemanaPorCiudadEnRuta = "";
                    try {
                        diaSemanaPorCiudadEnRuta = BDPDAO.consultarDiaSemanaPorCiudad(ciudadConsultarDiaRuta);
                    }catch (Exception e){
                        System.out.println("Error al consultar el día de la semana de la ruta: " + e.getMessage());
                        break;
                    }
                    if (diaSemanaPorCiudadEnRuta != null){
                        System.out.println("El día de la semana de la ruta que pasa por la ciudad "+ciudadConsultarDiaRuta+" es: "+diaSemanaPorCiudadEnRuta);
                    } else {
                        System.out.println("No se ha encontrado una ruta que pase por la ciudad: "+ciudadConsultarDiaRuta);
                    }
                break;

                case 12:
                    System.out.println("Introduce el día de la semana asociado para actualizar la ruta: ");
                    sc.nextLine();
                    String diaSemanaActualizarRuta = sc.nextLine();

                    System.out.println("Introduce el dato que deseas actualizar, introduce exactamente una de estas opciones: Registro de autobus, número de conductor o ID del lugar");
                    String datoActualizar = sc.nextLine();
                    switch (datoActualizar){
                        case "Registro de autobus":
                            boolean modificadoRegistro = false;
                            System.out.println("Introduce el nuevo registro de autobus para la ruta a actualizar: ");
                            String nuevoRegistro = sc.nextLine();
                            try {
                                modificadoRegistro = BDPDAO.modificarRegistroBDP(diaSemanaActualizarRuta, nuevoRegistro);
                            } catch (Exception e) {
                                System.out.println("Error al modificar el registro del autobus en la ruta: " + e.getMessage());
                            }
                            if (modificadoRegistro){
                                System.out.println("Registro del autobus modificado correctamente en la ruta.");
                            } else {
                                System.out.println("Error al modificar el registro del autobus en la ruta.");
                            }
                            break;
                        case "Número de conductor":
                            boolean modificadoNumConductor = false;
                            System.out.println("Introduce el nuevo número de conductor para la ruta a actualizar: ");
                            int nuevoNumConductor = sc.nextInt();
                            try {
                                modificadoNumConductor = BDPDAO.modificarNumConductorBDP(diaSemanaActualizarRuta, nuevoNumConductor);
                            } catch (Exception e) {
                                System.out.println("Error al modificar el número de conductor en la ruta: " + e.getMessage());
                            }
                            if (modificadoNumConductor){
                                System.out.println("Número de conductor modificado correctamente en la ruta.");
                            } else {
                                System.out.println("Error al modificar el número de conductor en la ruta.");
                            }
                            break;
                        case "ID del lugar":
                            boolean modificadoIdLugar = false;
                            System.out.println("Introduce el nuevo ID de lugar para la ruta a actualizar: ");
                            int nuevoIdLugar = sc.nextInt();
                            try {
                                modificadoIdLugar = BDPDAO.modificarIdLugarBDP(diaSemanaActualizarRuta, nuevoIdLugar);
                            } catch (Exception e) {
                                System.out.println("Error al modificar el ID de lugar en la ruta: " + e.getMessage());
                            }
                            if (modificadoIdLugar){
                                System.out.println("ID de lugar modificado correctamente en la ruta.");
                            } else {
                                System.out.println("Error al modificar el ID de lugar en la ruta.");
                            }
                            break;
                        default:
                            System.out.println("Introduce una opción válida para el dato a actualizar.");
                            break;
                    }

                    break;

                default:
                    System.out.println("Introduce una opción válida.");
                    break;
            }

        } while (op != 0);
    }
}
