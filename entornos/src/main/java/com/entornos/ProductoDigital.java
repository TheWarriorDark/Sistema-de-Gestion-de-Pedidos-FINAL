package com.entornos;

/**
 * Clase para representar un producto digital, que hereda de Producto.
 * Incluye propiedades especificas como la licencia y el tamaño del fichero.
 */
public class ProductoDigital extends Producto{
    /** Constante que define el descuento aplicado a productos digitales (5%). */
    public static final float DESCUENTO_DIGITAL = 0.05f; // 5% de descuento
    
    /** Constante que representa el tipo de IVA general (21%). */
    public static final int IVA_GENERAL = 0;
    /** Constante que representa el tipo de IVA reducido (10%). */
    public static final int IVA_REDUCIDO = 1;
    /** Constante que representa el tipo de IVA superreducido (4%). */
    public static final int IVA_SUPER = 2;

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
     * @param id El identificador del producto.
     * @param nombre El nombre del producto.
     * @param precioBase El precio base del producto.
     * @param licencia La licencia de software o uso.
     * @param tamanoEnMb El tamaño del fichero en Megabytes.
     */
    public ProductoDigital(int id, String nombre, float precioBase, String licencia, float tamanoEnMb){
        super(id, nombre, precioBase);
        this.licencia = licencia;
        this.tamanoEnMb = tamanoEnMb;
    }

    /**
     * Constructor para crear un nuevo producto digital con informacion basica.
     * @param id El identificador del producto.
     * @param nombre El nombre del producto.
     * @param precioBase El precio base del producto.
     */
    public ProductoDigital(int id, String nombre, float precioBase){
        super(id, nombre, precioBase);
        this.licencia = "Licencia no especificada";
        this.tamanoEnMb = 0.0f;
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
     * Metodo para aplicar el IVA seleccionado al precio base.
     * @param tipoIva El tipo de IVA (0 para GENERAL, 1 para REDUCIDO, 2 para SUPER)
     * @return El importe total tras aplicar el IVA al precio base.
     * @throws IllegalArgumentException si el tipo de IVA proporcionado no es reconocido.
     */
    public float aplicarIVA(int tipoIva) {
        float porcentaje;
        switch (tipoIva) {
            case IVA_GENERAL: porcentaje = 0.21f; break;
            case IVA_REDUCIDO: porcentaje = 0.10f; break;
            case IVA_SUPER: porcentaje = 0.04f; break;
            default: throw new IllegalArgumentException("Tipo de IVA no reconocido.");
        }
        
        return getPrecioBase() * (1 + porcentaje);
    }

    /**
     * Metodo para calcular el precio final del producto digital con un descuento.
     * Aplica un 5% de descuento sobre el precio base.
     * @return El precio final con descuento.
     */
    @Override
    public float calcularPrecio(){
        return super.getPrecioBase() * (1 - DESCUENTO_DIGITAL);
    }
    
}
 