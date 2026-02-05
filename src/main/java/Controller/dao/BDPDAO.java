package Controller.dao;

import Controller.DB.ConexionBBDD;
import model.BDP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static String consultarDiaSemanaPorCiudad(String ciudadParaConsultDia){
        String sql = "SELECT b.day_of_week FROM BDP b JOIN Lugares l ON b.IdLugar = l.IdLugar WHERE l.ciudad = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, ciudadParaConsultDia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String diaSemana = rs.getString("day_of_week");
                return diaSemana;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar día de la semana por ciudad: " + e.getMessage());
             return null;
        }

    }

    public static boolean modificarRegistroBDP(String diaSemana, String registro){
        String sql = "UPDATE BDP SET Registro = ? WHERE day_of_week = ?;";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, registro);
            ps.setString(2, diaSemana);

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas == 0){
                return false;
            }else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar el registro en BDP: " + e.getMessage());
             return false;
        }
    }

    public static boolean modificarNumConductorBDP(String diaSemana, int numConductor){
        String sql = "UPDATE BDP SET numConductor = ? WHERE day_of_week = ?;";

        try(Connection con = ConexionBBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, numConductor);
            ps.setString(2, diaSemana);

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas == 0){
                return false;
            }else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar el número de conductor en BDP: " + e.getMessage());
            return false;
        }
    }

    public static boolean modificarIdLugarBDP(String diaSemana, int IdLugar){
        String sql = "UPDATE BDP SET IdLugar = ? WHERE day_of_week = ?;";

        try(Connection con = ConexionBBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, IdLugar);
            ps.setString(2, diaSemana);

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas == 0){
                return false;
            }else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar el ID del lugar en BDP: " + e.getMessage());
            return false;
        }

    }

}
