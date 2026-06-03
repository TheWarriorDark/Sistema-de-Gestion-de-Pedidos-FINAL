package com.entornos;

/**
 * Clase abstracta para representar un producto generico.
 * Contiene las propiedades y metodos comunes a todos los productos.
 */
public class Producto {
    private int id;
    private String nombre;
    private float precioBase;

    /**
     * Constructor por defecto.
     * Inicializa un producto con valores por defecto.
     */
    public Producto() {
        this.id = 0;
        this.nombre = "Producto sin nombre";
        this.precioBase = 0.0f;
    }
    /**
     * Constructor para inicializar un producto.
     * @param id El identificador del producto.
     * @param nombre El nombre del producto.
     * @param precioBase El precio base del producto.
     */
    public Producto(int id, String nombre, float precioBase){
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    /**
     * Metodo para obtener el ID del producto.
     * @return El ID del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para establecer el ID del producto.
     * @param id El nuevo ID del producto.
     */
    public void setId(int id) {
        this.id = id;
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
    public float getPrecioBase() {
        return precioBase;
    }

    /**
     * Metodo para establecer el precio base del producto.
     * @param precioBase El nuevo precio del producto.
     */
    public void setPrecioBase(float precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precioBase = precioBase;
    }
    
    /**
     * Metodo para obtener una representacion en cadena del producto.
     * @return Una cadena con el nombre y el precio del producto.
     */
    @Override
    public String toString(){
        return "[" + id + "] " + nombre + " => " + precioBase;
    }

    /**
     * Metodo abstracto para calcular el precio final del producto.
     * Las clases hijas deben implementar su propia logica de cálculo.
     * @return El precio final calculado.
     */
    public float calcularPrecio() {
        return this.precioBase;
    }
}
