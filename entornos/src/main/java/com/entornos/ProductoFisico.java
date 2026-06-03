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
    public ProductoFisico(int id, String nombre, float precioBase, double peso, String destino){
        super(id, nombre, precioBase);
        this.peso = (float) peso;
        this.destino = destino;
    }

    /**
     * Constructor para crear un nuevo producto fisico sin destino especificado.
     * @param id El identificador del producto.
     * @param nombre El nombre del producto.
     * @param precioBase El precio base del producto.
     * @param peso El peso del paquete en kg.
     */
    public ProductoFisico(int id, String nombre, float precioBase, double peso){
        super(id, nombre, precioBase);
        this.peso = (float) peso;
        this.destino = "No especificado";
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

    /**
     * Metodo para obtener el destino del envio del producto.
     * @return El destino del envio.
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Metodo para establecer el destino del envio del producto.
     * @param destino El nuevo destino de envio.
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Metodo para calcular el coste del envio basado en el destino y el peso.
     * @return El coste del envio.
     */
    public float calcularCosteEnvio() {
        return this.peso * 0.1f;
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