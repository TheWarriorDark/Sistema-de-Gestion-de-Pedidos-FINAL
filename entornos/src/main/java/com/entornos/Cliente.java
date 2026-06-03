package com.entornos;

/**
 * Clase para representar a un cliente con su informacion personal.
 */
public class Cliente {
    private String nombre;
    private String correo;
    private String direccion;

    /**
     * Constructor por defecto.
     * Inicializa un cliente con valores por defecto.
     */
    public Cliente() {
        this.nombre = "Cliente no especificado";
        this.correo = "correo@desconocido.com";
        this.direccion = "Direccion no especificada";
    }

    /**
     * Constructor para crear un nuevo objeto Cliente.
     * @param nombre El nombre del cliente.
     * @param correo El correo electronico del cliente.
     * @param direccion La direccion postal del cliente.
     */
    public Cliente(String nombre, String correo, String direccion){
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
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
     * Metodo para obtener el correo electronico del cliente.
     * @return El correo electronico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Metodo para establecer el correo electronico del cliente.
     * @param correo El nuevo correo del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Metodo para obtener la direccion del cliente.
     * @return La direccion del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo para establecer la direccion del cliente.
     * @param direccion La nueva direccion del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo para obtener una representacion en cadena de la informacion del cliente.
     * @return Una cadena con los datos del cliente.
     */
    @Override
    public String toString(){
        return "Nombre: " + this.nombre + " | Correo: " + this.correo + " | Direccion: " + this.direccion;
    }
}
