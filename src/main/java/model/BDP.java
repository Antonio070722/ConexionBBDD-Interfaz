package model;

public class BDP {
    String Registro;
    int numConductor;
    int IdLugar;
    String dia_semana;

    public BDP(String registro, int numConductor, int idLugar, String dia_semana) {
        Registro = registro;
        this.numConductor = numConductor;
        IdLugar = idLugar;
        this.dia_semana = dia_semana;
    }

    public String getRegistro() {
        return Registro;
    }

    public void setRegistro(String registro) {
        Registro = registro;
    }

    public int getNumConductor() {
        return numConductor;
    }

    public void setNumConductor(int numConductor) {
        this.numConductor = numConductor;
    }

    public int getIdLugar() {
        return IdLugar;
    }

    public void setIdLugar(int idLugar) {
        IdLugar = idLugar;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    @Override
    public String toString() {
        return "\n\tRegistro: " + Registro +
                "\n\tNúmero de Conductor: " + numConductor +
                "\n\tIdLugar: " + IdLugar +
                "\n\tDía de la semana: " + dia_semana;
    }

}
