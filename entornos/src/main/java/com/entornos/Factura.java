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
    private double totalNeto;
    private double totalIva;
    private double totalEnvio;
    private double descuento;
    private double totalFinal;
    
    private Cliente cliente;
    private Pedido pedido;

    /**
     * Constructor por defecto.
     */
    public Factura() {
    }

    /**
     * Constructor para crear una nueva factura.
     * @param cliente El cliente asociado a la factura.
     * @param pedido El pedido que generó la factura.
     * @param totalNeto El coste total de los productos sin impuestos ni envíos.
     * @param totalIva El importe total de impuestos aplicados.
     * @param totalEnvio El coste total de los portes.
     * @param descuento El total de descuentos aplicados (productos y fidelidad).
     * @param totalFinal El importe total a pagar tras aplicar descuentos.
     */
    public Factura(Cliente cliente, Pedido pedido, double totalNeto, double totalIva, double totalEnvio, double descuento, double totalFinal) {
        this.codigoFactura = "FACT-" + LocalDate.now() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.fechaEmision = LocalDate.now();
        this.cliente = cliente;
        this.pedido = pedido;
        this.totalNeto = Math.round(totalNeto * 100.0) / 100.0;
        this.totalIva = Math.round(totalIva * 100.0) / 100.0;
        this.totalEnvio = Math.round(totalEnvio * 100.0) / 100.0;
        this.descuento = Math.round(descuento * 100.0) / 100.0;
        this.totalFinal = Math.round(totalFinal * 100.0) / 100.0;
    }

    /**
     * Metodo para obtener el código único de la factura.
     * @return El código de la factura.
     */
    public String getCodigoFactura() {
        return codigoFactura;
    }

    /**
     * Metodo para establecer el código único de la factura.
     * @param codigoFactura El nuevo código de la factura.
     */
    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    /**
     * Metodo para obtener la fecha en la que se emitió la factura.
     * @return La fecha de emisión.
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Metodo para establecer la fecha de emisión de la factura.
     * @param fechaEmision La nueva fecha de emisión.
     */
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Metodo para obtener el cliente asociado a la factura.
     * @return El cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo para establecer el cliente de la factura.
     * @param cliente El nuevo cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo para obtener el pedido procesado en la factura.
     * @return El pedido.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Metodo para establecer el pedido de la factura.
     * @param pedido El nuevo pedido.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Metodo para obtener el importe total sin impuestos ni envíos.
     * @return El importe total neto.
     */
    public double getTotalNeto() {
        return totalNeto;
    }

    /**
     * Metodo para establecer el importe total neto.
     * @param totalNeto El nuevo importe total neto.
     */
    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    /**
     * Metodo para obtener el total de impuestos (IVA) sumados en la venta.
     * @return El importe total de IVA.
     */
    public double getTotalIva() {
        return totalIva;
    }

    /**
     * Metodo para establecer el total de impuestos.
     * @param totalIva El nuevo total de IVA.
     */
    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    /**
     * Metodo para obtener el sumatorio de todos los costes de envío aplicados.
     * @return El importe total del envío.
     */
    public double getTotalEnvio() {
        return totalEnvio;
    }

    /**
     * Metodo para establecer el coste total de envío.
     * @param totalEnvio El nuevo coste de envío.
     */
    public void setTotalEnvio(double totalEnvio) {
        this.totalEnvio = totalEnvio;
    }

    /**
     * Metodo para obtener el total de descuentos (fidelidad, VIP y productos) descontados.
     * @return El importe total de los descuentos aplicados.
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Metodo para establecer el descuento aplicado.
     * @param descuento El nuevo total de descuentos.
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * Metodo para obtener el importe definitivo a pagar por el cliente.
     * @return El importe total final.
     */
    public double getTotalFinal() {
        return totalFinal;
    }

    /**
     * Metodo para establecer el importe total final.
     * @param totalFinal El nuevo importe total final.
     */
    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    /**
     * Constructor sobrecargado para inicializar una factura con todos los datos financieros y metadatos predefinidos.
     * @param codigoFactura El código único de la factura.
     * @param fechaEmision La fecha de emisión de la factura.
     * @param totalNeto El total neto.
     * @param totalIva El total de IVA.
     * @param totalEnvio El total de envío.
     * @param totalFinal El total final.
     * @param descuento El descuento aplicado.
     */
    public Factura(String codigoFactura, LocalDate fechaEmision, double totalNeto, double totalIva, double totalEnvio, double totalFinal, double descuento) {
        this.codigoFactura = codigoFactura;
        this.fechaEmision = fechaEmision;
        this.totalNeto = Math.round(totalNeto * 100.0) / 100.0;
        this.totalIva = Math.round(totalIva * 100.0) / 100.0;
        this.totalEnvio = Math.round(totalEnvio * 100.0) / 100.0;
        this.totalFinal = Math.round(totalFinal * 100.0) / 100.0;
        this.descuento = Math.round(descuento * 100.0) / 100.0;
    }

    /**
     * Metodo para obtener una representación en cadena formateada de la factura.
     * @return Una cadena de texto con el documento y el desglose de la factura.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura: ").append(codigoFactura).append("\n");
        sb.append("Fecha de emision: ").append(fechaEmision).append("\n");
        if (cliente != null) {
            sb.append("Cliente: ").append(cliente.getNombre()).append(" (ID: ").append(cliente.getId()).append(")\n");
        }
        if (pedido != null) {
            sb.append("Resumen del pedido:\n").append(pedido.mostrarResumen()).append("\n");
        }
        sb.append("Total neto: ").append(totalNeto).append("\n");
        sb.append("Total IVA: ").append(totalIva).append("\n");
        sb.append("Total envio: ").append(totalEnvio).append("\n");
        sb.append("Descuento: ").append(descuento).append("\n");
        sb.append("Total final: ").append(totalFinal).append("\n");
        return sb.toString();
    }
}