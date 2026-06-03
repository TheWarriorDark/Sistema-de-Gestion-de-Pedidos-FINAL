package com.entornos;

/**
 * Clase para representar un producto fisico, que hereda de Producto.
 * Incluye el coste de envio como propiedad adicional.
 */
public class ProductoFisico extends Producto{
    private float costeEnvio;

    /**
     * Constructor por defecto.
     * Inicializa un producto fisico con valores por defecto.
     */
    public ProductoFisico() {
        super(); // Herencia
        this.costeEnvio = 0.0f;
    }

    /**
     * Constructor para crear un nuevo producto fisico.
     * @param nombre El nombre del producto.
     * @param precio El precio base del producto.
     * @param costeEnvio El coste de envio asociado al producto.
     */
    public ProductoFisico(String nombre, float precio, float costeEnvio){
        super(nombre,precio);
        this.costeEnvio = costeEnvio;
    }

    /**
     * Metodo para obtener el coste de envio del producto.
     * @return El coste de envio.
     */
    public float getCosteEnvio() {
        return costeEnvio;
    }

    /**
     * Metodo para establecer el coste de envio del producto.
     * @param costeEnvio El nuevo coste de envio.
     */
    public void setCosteEnvio(float costeEnvio) {
        this.costeEnvio = costeEnvio;
    }

    /**
     * Metodo para obtener una representacion en cadena del producto fisico.
     * @return Una cadena con la informacion del producto y su coste de envio.
     */
    @Override
    public String toString(){
        return super.toString() + " | Coste de envio: " + this.costeEnvio;
    }

    /**
     * Metodo para calcular el precio final del producto fisico.
     * Suma el precio base y el coste de envio.
     * @return El precio final incluyendo el envio.
     */
    @Override
    public float calcularPrecio(){
        return super.getPrecio() + this.costeEnvio;
    }
}