package com.entornos;

/**
 * Clase que orquesta el proceso de venta, aplicando descuentos y generando la factura.
 */
public class Tienda {

    /**
     * Procesa la venta de un pedido para un cliente, aplicando descuentos y generando una factura.
     *
     * @param cliente El cliente que realiza la compra.
     * @param pedido El pedido con los productos.
     * @return Un objeto Factura con el resultado final de la venta.
     * @throws IllegalStateException si el pedido está vacío.
     */
    public Factura realizarVenta(Cliente cliente, Pedido pedido) {
        if (cliente.getPais() == null || cliente.getPais().isBlank()) {
            throw new IllegalArgumentException("El país del cliente no puede ser nulo o estar vacío.");
        }

        if (cliente.getId() != pedido.getCliente().getId()) {
            throw new IllegalArgumentException("El cliente recibido no coincide con el cliente del pedido.");
        }

        // 1. Obtener el total neto del pedido
        float totalNetoPedido = pedido.calcularTotal();

        // 2. Calcular el desglose (Neto, IVA, Envío, Descuentos de Productos)
        float totalNeto = 0;
        float totalIva = 0;
        float totalEnvio = pedido.calcularEnvio(cliente.getPais()); // Iniciar con la tarifa base de país
        float descuentosProductos = 0;
        
        for (int i = 0; i < pedido.getProductos().size(); i++) {
            Producto p = pedido.getProductos().get(i);
            int cant = pedido.getCantidades().getOrDefault(p.getId(), 1);
            totalNeto += p.getPrecioBase() * cant;
            
            switch (p) {
                case ProductoDigital pd -> {
                    totalIva += (pd.aplicarIVA(ProductoDigital.IVA_GENERAL) - pd.getPrecioBase()) * cant;
                    descuentosProductos += (pd.getPrecioBase() * ProductoDigital.DESCUENTO_DIGITAL) * cant;
                }
                case ProductoFisico pf -> {
                    // Físico no añade IVA, solo el coste por peso
                    totalEnvio += pf.calcularCosteEnvio() * cant;
                }
                default -> {} // Los genéricos base están exentos de IVA, no sumamos nada
            }
        }

        // Reconstruir el total bruto antes de aplicar descuentos de tienda
        float totalBruto = totalNetoPedido + totalIva + totalEnvio - descuentosProductos;

        // 3. Calcular descuentos de fidelidad y VIP de la tienda
        int bloquesFidelidad = cliente.getAnosAntiguedad() / 5; // Extraído a int para evidenciar división entera intencionada
        float descuentoFidelidad = bloquesFidelidad * 0.05f; // 5% por cada 5 años
        float descuentoVip = cliente.isEsVip() ? 0.10f : 0.0f;

        float porcentajeDescuentoTotal = descuentoFidelidad + descuentoVip;
        float montoDescuentoTienda = totalBruto * porcentajeDescuentoTotal;
        float descuentosTotalesAplicados = descuentosProductos + montoDescuentoTienda;
        float totalFinal = totalBruto - montoDescuentoTienda;

        // 4. Generar la factura con el desglose
        return new Factura(cliente, pedido, totalNeto, totalIva, totalEnvio, descuentosTotalesAplicados, totalFinal);
    }
}