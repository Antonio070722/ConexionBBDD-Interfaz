package model;

public class Conductor {
    /**
     * Objeto Conductor, que contiene el número del conductor, el nombre y los apellidos.
     */
    private int numeroConductor;
    private String nombre;
    private String apellidos;

    /**
     * Constructor de la clase Conductor, que recibe el número del conductor, el nombre y los apellidos.
     * @param numeroConductor
     * @param nombre
     * @param apellido
     */
    public Conductor(int numeroConductor ,String nombre, String apellido) {
        this.nombre = nombre;
        this.apellidos = apellido;
        this.numeroConductor = numeroConductor;
    }

    //Constructor vacio
    public Conductor() {}

    /**
     * Getters y setters para cada uno de los atributos de la clase Conductor.
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getNumeroConductor() {
        return numeroConductor;
    }

    public void setNumeroConductor(int numeroConductor) {
        this.numeroConductor = numeroConductor;
    }

    /**
     * Método toString para mostrar la información de la clase BDP de forma estructurada.
     * @return
     */
    @Override
    public String toString() {
        return "\n\tNumeroConductor: " + numeroConductor +
                "\n\tNombre: " + nombre +
                "\n\tApellidos: " + apellidos;
    }
}
