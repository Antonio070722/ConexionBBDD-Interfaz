package model;

public class Bus {
    private String Registro;
    private String Licencia;
    private String Tipo;

    public Bus(String registro, String licencia, String tipo) {
        Registro = registro;
        Licencia = licencia;
        Tipo = tipo;
    }

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

    @Override
    public String toString() {
        return "\n\tRegistro: " + Registro +
                "\n\tLicencia: " + Licencia +
                "\n\tTipo: " + Tipo;
    }
}
