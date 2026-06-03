package com.entornos;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase para gestionar un pedido. Un pedido esta asociado a un Cliente y contiene una lista de Productos.
 * Permite realizar el pedido junto a la clase Cliente.
 */
public class Pedido {

    private Cliente cliente;
    private List<Producto> productos;
    
    /**
     * Constructor por defecto. Inicializa un pedido con un cliente por defecto y una lista de productos vacia.
     */
    public Pedido() {
        this.cliente = new Cliente(); // Crea un cliente con valores por defecto
        this.productos = new ArrayList<>();
    }

    /**
     * Constructor para crear un pedido para un cliente especifico, inicializando la lista de productos vacia.
     * @param cliente El cliente que realiza el pedido.
     */
    public Pedido(Cliente cliente){
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    /**
     * Constructor para crear un pedido para un cliente con una lista de productos preexistente.
     * @param cliente El cliente que realiza el pedido.
     * @param productos La lista de productos inicial del pedido.
     */
    public Pedido(Cliente cliente, List<Producto> productos){
        this.cliente = cliente;
        this.productos = productos;
    }

    /**
     * Metodo para calcular el precio total del pedido.
     * Suma los precios calculados de cada producto en la lista, considerando descuentos o costes de envio.
     * @return El importe total del pedido.
     */
    public float calcularTotal(){
        float precioTotal = 0;
        for(Producto producto : productos)
            precioTotal += producto.calcularPrecio();
        return precioTotal;
    }

    /**
     * Metodo para mostrar un resumen con la informacion del pedido.
     * Incluye los datos del cliente, la lista de productos comprados y el importe total.
     * @return Una cadena de texto con el resumen del pedido.
     */
    public String mostrarResumen(){
        StringBuilder resumen = new StringBuilder();
        resumen.append("Datos del cliente\n");
        resumen.append("* ID: ").append(cliente.getId()).append("\n");
        resumen.append("* Nombre: ").append(cliente.getNombre()).append("\n");
        resumen.append("* Antigüedad: ").append(cliente.getAnosAntiguedad()).append(" años\n");
        resumen.append("* VIP: ").append(cliente.isEsVip() ? "Sí" : "No").append("\n");
        resumen.append("* País: ").append(cliente.getPais()).append("\n\n");
        resumen.append("Productos en el pedido\n");
        
        for(int i = 0; i < productos.size(); i++){
            resumen.append(i + 1).append(". ").append(productos.get(i).getNombre()).append("\n");
        }
        resumen.append("\nPrecio final: ").append(this.calcularTotal());
        return resumen.toString();
    }

    /**
     * Metodo para añadir un producto a la lista de productos del pedido.
     * @param productoAnadir El producto que se va a añadir.
     */
    public void anadirProducto(Producto productoAnadir){
        this.productos.add(productoAnadir);
    }

    /**
     * Metodo para eliminar un producto de la lista de productos del pedido.
     * @param productoAEliminar El producto que se va a eliminar.
     * @return true si el producto estaba en la lista y fue eliminado, false en caso contrario.
     */
    public boolean eliminarProducto(Producto productoAEliminar) {
        return this.productos.remove(productoAEliminar);
    }
}
