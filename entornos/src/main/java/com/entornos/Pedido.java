package com.entornos;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Integer, Integer> cantidades;
    
    /**
     * Constructor por defecto. Inicializa un pedido con un cliente por defecto y una lista de productos vacia.
     */
    public Pedido() {
        this.idPedido = DEFAULT_ID;
        this.cliente = new Cliente(); // Crea un cliente con valores por defecto
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<>();
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
        this.cantidades = new HashMap<>();
    }

    /**
     * Constructor para crear un pedido para un cliente especifico, inicializando la lista de productos vacia.
     * @param cliente El cliente que realiza el pedido.
     */
    public Pedido(Cliente cliente){
        this.idPedido = DEFAULT_ID;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<>();
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
        this.cantidades = new HashMap<>();
        for (Producto p : this.productos) {
            this.cantidades.put(p.getId(), 1); // Añade cantidad 1 por defecto a los preexistentes
        }
    }

    /**
     * Constructor para crear un pedido con listas y mapas preexistentes.
     * @param idPedido El ID del pedido.
     * @param cliente El cliente asociado a la compra.
     * @param productos Lista de productos iniciales.
     * @param cantidades Mapa de cantidades agrupadas por ID de producto.
     */
    public Pedido(int idPedido, Cliente cliente, List<Producto> productos, Map<Integer, Integer> cantidades) {
        for (Producto p : productos) {
            if (!cantidades.containsKey(p.getId())) {
                throw new IllegalArgumentException("La creación del pedido debe fallar si falta la cantidad de alguno de los productos.");
            }
        }
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>(productos);
        this.cantidades = new HashMap<>(cantidades);
    }

    /**
     * Metodo para obtener el ID del pedido.
     * @return El ID del pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Metodo para obtener el cliente del pedido.
     * @return El cliente asociado.
     */
    public Cliente getCliente() {
        return cliente;
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
    public Map<Integer, Integer> getCantidades() {
        return cantidades;
    }

    /**
     * Metodo para calcular el coste de envio base dependiendo del pais.
     * @param pais El pais de destino.
     * @return El coste del envio.
     */
    public float calcularEnvio(String pais) {
        // Comprobación de diagnóstico: si hay productos y TODOS son digitales, el envío es 0
        if (!productos.isEmpty()) {
            boolean soloDigitales = true;
            boolean soloGenericos = true;
            for (Producto p : productos) {
                if (!(p instanceof ProductoDigital)) {
                    soloDigitales = false;
                }
                if (p.getClass() != Producto.class) {
                    soloGenericos = false;
                }
            }
            if (soloDigitales || soloGenericos) {
                return 0.0f;
            }
        }

        float costeBase = 10.0f; // Tarifa base para el resto de destinos
        if (pais != null) {
            String destNormalized = pais.toUpperCase();
            if (destNormalized.equals("ESPAÑA") || destNormalized.equals("ESPANA")) {
                costeBase = 0.0f;
            } else if (destNormalized.equals("FRANCIA") || destNormalized.equals("ITALIA") || destNormalized.equals("PORTUGAL")) {
                costeBase = 5.0f;
            }
        }
        return costeBase;
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
            int cantidad = cantidades.getOrDefault(producto.getId(), 1);
            precioTotal += producto.getPrecioBase() * cantidad;
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
                Producto p = productos.get(i);
                resumen.append(i + 1).append(". ").append(p.getNombre())
                       .append(" (x").append(cantidades.getOrDefault(p.getId(), 1)).append(")\n");
            }
            resumen.append("\nPrecio total neto: ").append(this.calcularTotal());
        }
        return resumen.toString();
    }

    /**
     * Metodo para añadir un producto a la lista de productos del pedido.
     * @param productoAnadir El producto que se va a añadir.
     */
    public void addProducto(Producto productoAnadir){
        this.productos.add(productoAnadir);
        this.cantidades.put(productoAnadir.getId(), 1);
    }

    /**
     * Metodo para añadir un producto al pedido indicando la cantidad.
     * @param productoAnadir El producto que se va a añadir.
     * @param cantidad La cantidad del producto a añadir.
     */
    public void addProducto(Producto productoAnadir, int cantidad){
        this.productos.add(productoAnadir);
        this.cantidades.put(productoAnadir.getId(), cantidad);
    }

    /**
     * Metodo para eliminar un producto de la lista de productos del pedido.
     * @param productoAEliminar El producto que se va a eliminar.
     * @return true si el producto estaba en la lista y fue eliminado, false en caso contrario.
     */
    public boolean eliminarProducto(Producto productoAEliminar) {
        if (productoAEliminar == null) return false;
        int index = this.productos.indexOf(productoAEliminar);
        if (index != -1) {
            this.productos.remove(index);
            this.cantidades.remove(productoAEliminar.getId());
            return true;
        }
        return false;
    }
}
