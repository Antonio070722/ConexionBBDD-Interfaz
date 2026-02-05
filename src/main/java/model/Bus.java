package model;

public class Bus {
    /**
     * Objeto Bus, que contiene el registro del bus, la licencia y el tipo de bus.
     */
    private String Registro;
    private String Licencia;
    private String Tipo;

    //Constructor de la clase Bus, que recibe el registro del bus, la licencia y el tipo de bus.
    public Bus(String registro, String licencia, String tipo) {
        Registro = registro;
        Licencia = licencia;
        Tipo = tipo;
    }

    //Constructor vacío para poder crear objetos sin necesidad de pasar parámetros.
    public Bus(){}

    /**
     * Getters y setters para cada uno de los atributos de la clase Bus.
     * @return
     */
    public String getRegistro() {
        return Registro;
    }

    public void setRegistro(String registro) {
        Registro = registro;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String licencia) {
        Licencia = licencia;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    /**
     * Método toString para mostrar la información de la clase BDP de forma estructurada.
     * @return
     */
    @Override
    public String toString() {
        return "\n\tRegistro: " + Registro +
                "\n\tLicencia: " + Licencia +
                "\n\tTipo: " + Tipo;
    }
}
