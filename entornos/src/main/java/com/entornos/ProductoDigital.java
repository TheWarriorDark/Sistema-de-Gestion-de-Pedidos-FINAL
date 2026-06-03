package com.entornos;

/**
 * Clase para representar un producto digital, que hereda de Producto.
 * Incluye propiedades especificas como la licencia y el tamaño del fichero.
 */
public class ProductoDigital extends Producto{
    public static final float DESCUENTO_DIGITAL = 0.05f; // 5% de descuento
    
    private String licencia;
    private float tamanoEnMb;

    /**
     * Constructor por defecto.
     * Inicializa un producto digital con valores por defecto.
     */
    public ProductoDigital() {
        super(); // Herencia
        this.licencia = "Licencia no especificada";
        this.tamanoEnMb = 0.0f;
    }

    /**
     * Constructor para crear un nuevo producto digital.
     * @param nombre El nombre del producto.
     * @param precio El precio base del producto.
     * @param licencia La licencia de software o uso.
     * @param tamanoEnMb El tamaño del fichero en Megabytes.
     */
    public ProductoDigital(String nombre, float precio, String licencia, float tamanoEnMb){
        super(nombre, precio);
        this.licencia = licencia;
        this.tamanoEnMb = tamanoEnMb;
    }

    /**
     * Metodo para obtener la licencia del producto digital.
     * @return La licencia del producto.
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * Metodo para obtener el tamaño en MB del producto digital.
     * @return El tamaño en MB.
     */
    public float getTamanoEnMb() {
        return tamanoEnMb;
    }

    /**
     * Metodo para establecer la licencia del producto digital.
     * @param licencia La nueva licencia.
     */
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    /**
     * Metodo para establecer el tamaño en MB del producto digital.
     * @param tamanoEnMb El nuevo tamaño en MB.
     */
    public void setTamanoEnMb(float tamanoEnMb) {
        this.tamanoEnMb = tamanoEnMb;
    }

    /**
     * Metodo para obtener una representacion en cadena del producto digital.
     * @return Una cadena con la informacion del producto, incluyendo licencia y tamaño.
     */
    @Override
    public String toString(){
        return super.toString() + " Licencia: " + this.licencia + " | Tamaño (MB): " + this.tamanoEnMb;
    }

    /**
     * Metodo para calcular el precio final del producto digital con un descuento.
     * Aplica un 5% de descuento sobre el precio base.
     * @return El precio final con descuento.
     */
    @Override
    public float calcularPrecio(){
        return super.getPrecio() * (1 - DESCUENTO_DIGITAL);
    }
    
}
 