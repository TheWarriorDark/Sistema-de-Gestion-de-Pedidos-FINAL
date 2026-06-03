package com.entornos;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Clase que representa la factura final de una venta.
 * Contiene la información del cliente, el pedido y el importe final tras descuentos.
 */
public class Factura {
    private String codigoFactura;
    private LocalDate fechaEmision;
    private float totalNeto;
    private float totalIva;
    private float totalEnvio;
    private float descuentosAplicados;
    private float totalFinal;
    
    private Cliente cliente;
    private Pedido pedido;

    /**
     * Constructor para crear una nueva factura.
     * @param cliente El cliente asociado a la factura.
     * @param pedido El pedido que generó la factura.
     * @param totalNeto El coste total de los productos sin impuestos ni envíos.
     * @param totalIva El importe total de impuestos aplicados.
     * @param totalEnvio El coste total de los portes.
     * @param descuentosAplicados El total de descuentos aplicados (productos y fidelidad).
     * @param totalFinal El importe total a pagar tras aplicar descuentos.
     */
    public Factura(Cliente cliente, Pedido pedido, float totalNeto, float totalIva, float totalEnvio, float descuentosAplicados, float totalFinal) {
        this.codigoFactura = "FACT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.fechaEmision = LocalDate.now();
        this.cliente = cliente;
        this.pedido = pedido;
        this.totalNeto = totalNeto;
        this.totalIva = totalIva;
        this.totalEnvio = totalEnvio;
        this.descuentosAplicados = descuentosAplicados;
        this.totalFinal = totalFinal;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public float getTotalNeto() {
        return totalNeto;
    }

    public float getTotalIva() {
        return totalIva;
    }

    public float getTotalEnvio() {
        return totalEnvio;
    }

    public float getDescuentosAplicados() {
        return descuentosAplicados;
    }

    public float getTotalFinal() {
        return totalFinal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- FACTURA ").append(codigoFactura).append(" ---\n");
        sb.append("Fecha de Emisión: ").append(fechaEmision).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append(" (ID: ").append(cliente.getId()).append(")\n\n");
        sb.append(pedido.mostrarResumen()).append("\n");
        sb.append("--- DESGLOSE DE LA FACTURA ---\n");
        sb.append(String.format("Total Neto: %.2f Euros%n", totalNeto));
        sb.append(String.format("Total IVA: %.2f Euros%n", totalIva));
        sb.append(String.format("Total Envío: %.2f Euros%n", totalEnvio));
        sb.append(String.format("Descuentos Aplicados: %.2f Euros%n", descuentosAplicados));
        sb.append("-----------------------------------\n");
        sb.append(String.format("TOTAL FINAL A PAGAR: %.2f Euros%n", totalFinal));
        sb.append("-----------------------------------\n");
        return sb.toString();
    }
}