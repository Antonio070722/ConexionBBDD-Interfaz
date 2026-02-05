package Controller.dao;

import Controller.DB.ConexionBBDD;
import model.Bus;
import model.Conductor;

import java.sql.Connection;
import java.sql.*;

public class BusesDAO {
    /**
     * Consulta el conductor asignado a un bus específico utilizando su registro. El método devuelve un nuevo objeto
     * Conductor con los datos del conductor asignado al bus. Si no se encuentra el bus o no tiene un conductor asignado, devuelve null.
     * @param registro El registro del bus para el cual se desea consultar el conductor asignado.
     * @return
     */
    public static Conductor consultarConductorBus(String registro){
        String sql = "SELECT c.numeroConductor, c.nombre, c.apellidos FROM BDP b JOIN CONDUCTORES c ON b.numConductor = c.numeroConductor WHERE b.Registro = ?;";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, registro);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    int numeroConductor = rs.getInt("numeroConductor");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    return new Conductor(numeroConductor, nombre, apellidos);
                }else {
                    return null; // No se encontró el bus
                }
            }catch (SQLException ex) {
                throw new RuntimeException("Error al extraer los datos de la Base de Datos"+ ex);
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al consultar bus"+ e);
        }
    }

    /**
     * Inserta un nuevo bus en la base de datos utilizando los datos proporcionados en el objeto businsert, si
     * la inserción es exitosa, devuelve un nuevo objeto Bus con los datos del bus insertado. Si la inserción falla, devuelve null.
     * @param businsert El objeto Bus que contiene los datos del bus a insertar en la base de datos, incluyendo el registro, tipo y licencia.
     * @return
     */
    public static Bus insertarBus(Bus businsert){
        String sql = "INSERT INTO BUSES (Registro, Tipo, Licencia) VALUES (?,?,?)";
        try(Connection con  = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, businsert.getRegistro());
            ps.setString(2, businsert.getTipo());
            ps.setString(3, businsert.getLicencia());

            int filasInsertadas = ps.executeUpdate();
            Bus businsertado = new Bus(businsert.getRegistro(), businsert.getTipo(), businsert.getLicencia());

            if(filasInsertadas == 0){
                return null;
            }else {
                return businsertado;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el bus: " + e.getMessage());
            return null;
        }
    }

    /**
     * Elimina un bus de la base de datos utilizando su registro como identificador. El método devuelve true si el bus se
     * eliminó correctamente, o false si no se encontró el bus o si ocurrió un error durante la eliminación.
     * @param registro El registro del bus que se desea eliminar de la base de datos.
     * @return
     */
    public static boolean borrarBus(String registro){
        String sql = "DELETE FROM BUSES WHERE Registro = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, registro);

            int filasBorradas = ps.executeUpdate();

            if(filasBorradas == 0){
                return false;
            }else  {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al borrar el bus: " + e.getMessage());
            return false;
        }
    }

}
