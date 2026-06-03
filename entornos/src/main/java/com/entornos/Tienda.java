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
        // 1. Calcular el total inicial del pedido (incluye IVA y gastos de envío)
        float totalPedido = pedido.calcularTotal();

        // 2. Calcular el desglose (Neto, IVA, Envío, Descuentos de Productos)
        float totalNeto = 0;
        float totalIva = 0;
        float totalEnvio = 0;
        float descuentosProductos = 0;
        
        for (int i = 0; i < pedido.getProductos().size(); i++) {
            Producto p = pedido.getProductos().get(i);
            int cant = pedido.getCantidades().get(i);
            totalNeto += p.getPrecioBase() * cant;
            
            if (p instanceof ProductoDigital) {
                ProductoDigital pd = (ProductoDigital) p;
                totalIva += (pd.aplicarIVA(ProductoDigital.IVA_GENERAL) - pd.getPrecioBase()) * cant;
                descuentosProductos += (pd.getPrecioBase() * ProductoDigital.DESCUENTO_DIGITAL) * cant;
            } else if (p instanceof ProductoFisico) {
                ProductoFisico pf = (ProductoFisico) p;
                totalIva += (pf.getPrecioBase() * 0.21f) * cant;
                totalEnvio += pf.calcularCosteEnvio();
            } else {
                totalIva += (p.getPrecioBase() * 0.21f) * cant;
            }
        }

        // 3. Calcular descuentos de fidelidad y VIP de la tienda
        float descuentoFidelidad = (cliente.getAnosAntiguedad() / 5) * 0.05f; // 5% por cada 5 años
        float descuentoVip = cliente.isEsVip() ? 0.10f : 0.0f;

        float porcentajeDescuentoTotal = descuentoFidelidad + descuentoVip;
        float montoDescuentoTienda = totalPedido * porcentajeDescuentoTotal;
        float descuentosTotalesAplicados = descuentosProductos + montoDescuentoTienda;
        float totalFinal = totalPedido - montoDescuentoTienda;

        // 4. Generar la factura con el desglose
        return new Factura(cliente, pedido, totalNeto, totalIva, totalEnvio, descuentosTotalesAplicados, totalFinal);
    }
}