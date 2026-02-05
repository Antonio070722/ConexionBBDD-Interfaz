package model;

public class Lugar {
    /**
     * Objeto Lugar, que contiene el id del lugar, el código postal, la ciudad y el sitio.
     */
    private int IdLugar;
    private String cp;
    private String ciudad;
    private String site;

    /**
     * Constructor de la clase Lugar, que recibe el id del lugar, el código postal, la ciudad y el sitio.
     * @param IdLugar
     * @param cp
     * @param ciudad
     * @param site
     */
    public Lugar(int IdLugar, String cp, String ciudad, String site) {
        this.IdLugar = IdLugar;
        this.site = site;
        this.cp = cp;
        this.ciudad = ciudad;
    }

    //Constructor vacío para poder crear objetos sin necesidad de pasar parámetros.
    public Lugar(){}

    /**
     * Getters y setters para cada uno de los atributos de la clase Lugar.
     * @return
     */

    public int getIdLugar() {
        return IdLugar;
    }

    public void setIdLugar(int idLugar) {
        IdLugar = idLugar;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Método toString para mostrar la información de la clase BDP de forma estructurada.
     * @return
     */
    @Override
    public String toString() {
        return "\n\tIdLugar: " + IdLugar +
                "\n\tSite: " + site +
                "\n\tCP: " + cp +
                "\n\tCiudad: " + ciudad;
    }
}
