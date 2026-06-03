package com.entornos;

/**
 * Clase para representar a un cliente con su informacion personal.
 */
public class Cliente {
    private int id;
    private String nombre;
    private int anosAntiguedad;
    private boolean esVip;
    private String pais;

    /**
     * Constructor por defecto.
     * Inicializa un cliente con valores por defecto.
     */
    public Cliente() {
        this.id = 0;
        this.nombre = "Cliente no especificado";
        this.anosAntiguedad = 0;
        this.esVip = false;
        this.pais = "No especificado";
    }

    /**
     * Constructor para crear un nuevo objeto Cliente.
     * @param id El ID del cliente.
     * @param nombre El nombre del cliente.
     * @param anosAntiguedad Los años de antigüedad del cliente.
     * @param esVip Indica si el cliente es VIP.
     * @param pais El país del cliente.
     */
    public Cliente(int id, String nombre, int anosAntiguedad, boolean esVip, String pais){
        this.id = id;
        this.nombre = nombre;
        this.anosAntiguedad = anosAntiguedad;
        this.esVip = esVip;
        this.pais = pais;
    }

    /**
     * Metodo para obtener el ID del cliente.
     * @return El ID del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para establecer el ID del cliente.
     * @param id El nuevo ID del cliente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo para obtener el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para establecer el nombre del cliente.
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para obtener los años de antigüedad del cliente.
     * @return Los años de antigüedad.
     */
    public int getAnosAntiguedad() {
        return anosAntiguedad;
    }

    /**
     * Metodo para establecer los años de antigüedad del cliente.
     * @param anosAntiguedad Los nuevos años de antigüedad.
     */
    public void setAnosAntiguedad(int anosAntiguedad) {
        this.anosAntiguedad = anosAntiguedad;
    }

    /**
     * Metodo para comprobar si el cliente es VIP.
     * @return true si es VIP, false en caso contrario.
     */
    public boolean isEsVip() {
        return esVip;
    }

    /**
     * Metodo para establecer el estado VIP del cliente.
     * @param esVip El nuevo estado VIP.
     */
    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    /**
     * Metodo para obtener el país del cliente.
     * @return El país del cliente.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Metodo para establecer el país del cliente.
     * @param pais El nuevo país del cliente.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Metodo para obtener una representacion en cadena de la informacion del cliente.
     * @return Una cadena con los datos del cliente.
     */
    @Override
    public String toString(){
        return "[" + this.id + "] Nombre: " + this.nombre + " | Antigüedad: " + this.anosAntiguedad + " años | VIP: " + (this.esVip ? "Sí" : "No") + " | País: " + this.pais;
    }
}
