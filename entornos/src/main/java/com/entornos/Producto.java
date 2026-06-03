package com.entornos;

/**
 * Clase abstracta para representar un producto generico.
 * Contiene las propiedades y metodos comunes a todos los productos.
 */
public abstract class Producto {
    private String nombre;
    private float precio;

    /**
     * Constructor por defecto.
     * Inicializa un producto con valores por defecto.
     */
    protected Producto() {
        this.nombre = "Producto sin nombre";
        this.precio = 0.0f;
    }
    /**
     * Constructor para inicializar un producto.
     * @param nombre El nombre del producto.
     * @param precio El precio base del producto.
     */
    protected Producto(String nombre, float precio){
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Metodo para obtener el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para establecer el nombre del producto.
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para obtener el precio base del producto.
     * @return El precio del producto.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Metodo para establecer el precio base del producto.
     * @param precio El nuevo precio del producto.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    /**
     * Metodo para obtener una representacion en cadena del producto.
     * @return Una cadena con el nombre y el precio del producto.
     */
    @Override
    public String toString(){
        return nombre + " => " + precio;
    }

    /**
     * Metodo abstracto para calcular el precio final del producto.
     * Las clases hijas deben implementar su propia logica de cálculo.
     * @return El precio final calculado.
     */
    public abstract float calcularPrecio();
}
