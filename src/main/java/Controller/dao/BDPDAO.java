package Controller.dao;

import Controller.DB.ConexionBBDD;
import model.BDP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDPDAO {
    public static BDP insertarRuta(BDP rutaAInsertar){
        String sql = "INSERT INTO BDP (Registro, numConductor, IdLugar, day_of_week) VALUES (?,?,?,?)";
        try(Connection con = ConexionBBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, rutaAInsertar.getRegistro());
            ps.setInt(2, rutaAInsertar.getNumConductor());
            ps.setInt(3, rutaAInsertar.getIdLugar());
            ps.setString(4, rutaAInsertar.getDia_semana());

            int filasInsert=ps.executeUpdate();

            if (filasInsert == 0){
                return null;
            }else {
                return rutaAInsertar;
            }

        } catch (Exception e) {
            System.out.println("Error al insertar ruta: " + e.getMessage());
             return null;
        }
    }

    public static boolean borrarRuta(int numConductor, int idLugar, String registro){
        String sql = "DELETE FROM BDP WHERE numConductor = ? AND IdLugar = ? AND Registro = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, numConductor);
            ps.setInt(2, idLugar);
            ps.setString(3, registro);

            int filasBorradas = ps.executeUpdate();

            if (filasBorradas == 0){
                return false;
            }else  {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al borrar ruta: " + e.getMessage());
             return false;
        }
    }

}
