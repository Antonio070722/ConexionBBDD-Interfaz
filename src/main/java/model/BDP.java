package model;

public class BDP {
    /**
     * Objeto para la relación entre Bus, Conductor y Lugar. Contiene el registro del bus, el número del conductor, el id del lugar y el día de la semana.
     */
    String Registro;
    int numConductor;
    int IdLugar;
    String dia_semana;

    /**
     * Constructor de la clase BDP, que recibe el registro del bus, el número del conductor, el id del lugar y el día de la semana.
     * @param registro
     * @param numConductor
     * @param idLugar
     * @param dia_semana
     */
    public BDP(String registro, int numConductor, int idLugar, String dia_semana) {
        Registro = registro;
        this.numConductor = numConductor;
        IdLugar = idLugar;
        this.dia_semana = dia_semana;
    }
    // Constructor vacío para poder crear objetos sin necesidad de pasar parámetros.
    public BDP(){}

    /**
     * Getters y setters para cada uno de los atributos de la clase BDP.
     * @return
     */
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

    /**
     * Método toString para mostrar la información de la clase BDP de forma estructurada.
     * @return
     */
    @Override
    public String toString() {
        return "\n\tRegistro: " + Registro +
                "\n\tNúmero de Conductor: " + numConductor +
                "\n\tIdLugar: " + IdLugar +
                "\n\tDía de la semana: " + dia_semana;
    }

}
