package com.entornos;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase para gestionar un pedido. Un pedido esta asociado a un Cliente y contiene una lista de Productos.
 * Permite realizar el pedido junto a la clase Cliente.
 */
public class Pedido {

    private static final int DEFAULT_ID = 0;
    public static final String PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE = "No se puede procesar un pedido si la lista de productos está vacía.";

    private int idPedido;
    private Cliente cliente;
    private List<Producto> productos;
    private List<Integer> cantidades;
    
    /**
     * Constructor por defecto. Inicializa un pedido con un cliente por defecto y una lista de productos vacia.
     */
    public Pedido() {
        this.idPedido = DEFAULT_ID;
        this.cliente = new Cliente(); // Crea un cliente con valores por defecto
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    /**
     * Constructor para crear un pedido para un cliente especifico, inicializando la lista de productos vacia y asignando un id.
     * @param idPedido El ID del pedido.
     * @param cliente El cliente que realiza el pedido.
     */
    public Pedido(int idPedido, Cliente cliente){
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    /**
     * Constructor para crear un pedido para un cliente especifico, inicializando la lista de productos vacia.
     * @param cliente El cliente que realiza el pedido.
     */
    public Pedido(Cliente cliente){
        this.idPedido = DEFAULT_ID;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    /**
     * Constructor para crear un pedido para un cliente con una lista de productos preexistente.
     * @param cliente El cliente que realiza el pedido.
     * @param productos La lista de productos inicial del pedido.
     */
    public Pedido(Cliente cliente, List<Producto> productos){
        this.idPedido = DEFAULT_ID;
        this.cliente = cliente;
        this.productos = new ArrayList<>(productos);
        this.cantidades = new ArrayList<>();
        for (int i = 0; i < this.productos.size(); i++) {
            this.cantidades.add(1); // Añade cantidad 1 por defecto a los preexistentes
        }
    }

    /**
     * Metodo para obtener el ID del pedido.
     * @return El ID del pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Metodo para establecer el ID del pedido.
     * @param idPedido El nuevo ID del pedido.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Metodo para obtener la lista de productos en el pedido.
     * @return La lista de productos.
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Metodo para obtener las cantidades asociadas a cada producto.
     * @return La lista de cantidades.
     */
    public List<Integer> getCantidades() {
        return cantidades;
    }

    /**
     * Metodo para calcular el precio total del pedido.
     * Suma los precios calculados de cada producto en la lista, considerando descuentos o costes de envio.
     * @return El importe total del pedido.
     */
    public float calcularTotal(){
        if (this.productos == null || this.productos.isEmpty()) {
            throw new IllegalArgumentException(PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE);
        }
        float precioTotal = 0;
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            int cantidad = cantidades.get(i);
            
            switch (producto) {
                case ProductoDigital pd -> {
                    // Precio con IVA usando el método de ProductoDigital
                    float precioConIva = pd.aplicarIVA(ProductoDigital.IVA_GENERAL);
                    // Mantenemos la lógica de negocio restando el 5% de descuento sobre el precio base
                    float descuento = pd.getPrecioBase() * ProductoDigital.DESCUENTO_DIGITAL;
                    precioTotal += (precioConIva - descuento) * cantidad;
                }
                case ProductoFisico pf -> {
                    // Físico: (Precio Base + IVA 21%) + Coste Envío
                    float precioConIva = pf.getPrecioBase() * 1.21f;
                    precioTotal += (precioConIva * cantidad) + pf.calcularCosteEnvio();
                }
                default -> precioTotal += producto.getPrecioBase() * 1.21f * cantidad;
            }
        }
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
        
        if (productos.isEmpty()) {
            resumen.append("El pedido no tiene productos actualmente.\n");
        } else {
            for(int i = 0; i < productos.size(); i++){
                resumen.append(i + 1).append(". ").append(productos.get(i).getNombre())
                       .append(" (x").append(cantidades.get(i)).append(")\n");
            }
            resumen.append("\nPrecio final (con IVA y envíos): ").append(this.calcularTotal());
        }
        return resumen.toString();
    }

    /**
     * Metodo para añadir un producto a la lista de productos del pedido.
     * @param productoAnadir El producto que se va a añadir.
     */
    public void addProducto(Producto productoAnadir){
        this.productos.add(productoAnadir);
        this.cantidades.add(1);
    }

    /**
     * Metodo para añadir un producto al pedido indicando la cantidad.
     * @param productoAnadir El producto que se va a añadir.
     * @param cantidad La cantidad del producto a añadir.
     */
    public void addProducto(Producto productoAnadir, int cantidad){
        this.productos.add(productoAnadir);
        this.cantidades.add(cantidad);
    }

    /**
     * Metodo para eliminar un producto de la lista de productos del pedido.
     * @param productoAEliminar El producto que se va a eliminar.
     * @return true si el producto estaba en la lista y fue eliminado, false en caso contrario.
     */
    public boolean eliminarProducto(Producto productoAEliminar) {
        int index = this.productos.indexOf(productoAEliminar);
        if (index != -1) {
            this.productos.remove(index);
            this.cantidades.remove(index);
            return true;
        }
        return false;
    }
}
