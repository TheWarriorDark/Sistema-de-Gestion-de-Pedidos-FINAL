package com.entornos;

/**
 * Clase para representar un producto fisico, que hereda de Producto.
 * Incluye el coste de envio como propiedad adicional.
 */
public class ProductoFisico extends Producto{
    private float peso;
    private String destino;

    /**
     * Constructor por defecto.
     * Inicializa un producto fisico con valores por defecto.
     */
    public ProductoFisico() {
        super(); // Herencia
        this.peso = 0.0f;
        this.destino = "España";
    }

    /**
     * Constructor para crear un nuevo producto fisico.
     * @param id El identificador del producto.
     * @param nombre El nombre del producto.
     * @param precioBase El precio base del producto.
     * @param peso El peso del paquete en kg.
     * @param destino El destino de envío.
     */
    public ProductoFisico(String id, String nombre, float precioBase, float peso, String destino){
        super(id, nombre, precioBase);
        this.peso = peso;
        this.destino = destino;
    }

    /**
     * Metodo para obtener el peso del producto.
     * @return El peso en kg.
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Metodo para establecer el peso del producto.
     * @param peso El nuevo peso en kg.
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Metodo para calcular el coste del envio basado en el destino y el peso.
     * @return El coste del envio.
     */
    public float calcularCosteEnvio() {
        float costeBase = 10.0f; // Tarifa base para el resto de destinos
        if (destino != null) {
            String destNormalized = destino.toUpperCase();
            if (destNormalized.equals("ESPAÑA") || destNormalized.equals("ESPANA")) {
                costeBase = 0.0f;
            } else if (destNormalized.equals("FRANCIA") || destNormalized.equals("ITALIA") || destNormalized.equals("PORTUGAL")) {
                costeBase = 5.0f;
            }
        }
        
        float sobrepeso = this.peso - 10.0f;
        if (sobrepeso > 0) {
            costeBase += (float) Math.ceil(sobrepeso);
        }
        
        return costeBase;
    }

    /**
     * Metodo para obtener una representacion en cadena del producto fisico.
     * @return Una cadena con la informacion del producto y su coste de envio.
     */
    @Override
    public String toString(){
        return super.toString() + " | Peso: " + this.peso + "kg | Destino: " + this.destino + " | Coste de envio: " + calcularCosteEnvio();
    }

    /**
     * Metodo para calcular el precio final del producto fisico.
     * Suma el precio base y el coste de envio.
     * @return El precio final incluyendo el envio.
     */
    @Override
    public float calcularPrecio(){
        return super.getPrecioBase() + calcularCosteEnvio();
    }
}