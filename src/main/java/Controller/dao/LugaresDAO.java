package Controller.dao;

import Controller.DB.ConexionBBDD;
import model.Lugar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LugaresDAO {
    /**
     * Consulta un lugar por su ID de lugar. Si el lugar existe, devuelve un objeto Lugar con los datos correspondientes. Si no existe, devuelve null.
     * @param idLugar ID del lugar a consultar
     * @return
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Lugar consultarLugar(int idLugar) throws SQLException {
        // Implementación del método para consultar un lugar por su ID
        String sql = "SELECT IdLugar, site, cp, ciudad FROM LUGARES WHERE IdLugar = ?";

        //Uso de try-with-resources para asegurar el cierre de Connection y PreparedStatement
        try (Connection con = ConexionBBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLugar);

            //Try separado para el ResultSet, permitiendo mejor manejo de excepciones
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String site = rs.getString("site");
                    String cp = rs.getString("cp");
                    String ciudad = rs.getString("ciudad");
                    return new Lugar(idLugar, site, cp, ciudad);

                } else {
                    return null; // No se encontró el lugar
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Error al extraer los datos de la Base de Datos", ex);
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al consultar lugar", e);

        }

    }

    /**
     * Inserta un nuevo lugar en la base de datos. Si la inserción es exitosa, devuelve el objeto Lugar insertado.
     * Si ocurre un error durante la inserción, lanza una RuntimeException con el mensaje de error.
     * @param lugarinsert
     * @return
     */
    public static Lugar insertarLugar(Lugar lugarinsert){
        String sql = "INSERT INTO LUGARES (IdLugar, cp, ciudad, site) VALUES (?,?,?,?)";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, lugarinsert.getIdLugar());
            ps.setString(2, lugarinsert.getCp());
            ps.setString(3, lugarinsert.getCiudad());
            ps.setString(4, lugarinsert.getSite());

            int filasInsert=ps.executeUpdate();

            if (filasInsert == 0){
                return null;
            }else {
                return lugarinsert;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina un lugar de la base de datos utilizando su ID. Si la eliminación es exitosa, devuelve true.
     * Si no se encuentra el lugar o ocurre un error durante la eliminación, devuelve false.
     * @param idLugar ID del lugar a eliminar
     * @return
     */
    public static boolean borrarLugar(int idLugar){
        String sql = "DELETE FROM LUGARES WHERE IdLugar = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idLugar);
            int filasBorradas = ps.executeUpdate();

            if (filasBorradas == 0){
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

