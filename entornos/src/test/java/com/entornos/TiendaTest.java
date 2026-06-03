package com.entornos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TiendaTest {

    private Tienda tienda;
    private Pedido pedidoLleno;

    @BeforeEach
    void setUp() {
        tienda = new Tienda();
        
        Cliente cliente = new Cliente(1, "Test", 0, false, "España");
        pedidoLleno = new Pedido(cliente);
        // Total pedido: (100 * 1.21) + 0 (envío) = 121.0
        pedidoLleno.addProducto(new ProductoFisico(1, "Producto Test", 100f, 2f, "España"));
    }

    @Test
    @DisplayName("Prueba de error: Realizar venta con pedido vacío lanza excepción")
    void testRealizarVentaPedidoVacio() {
        Cliente cliente = new Cliente();
        Pedido pedidoVacio = new Pedido(cliente);
        assertThrows(IllegalArgumentException.class, () -> {
            tienda.realizarVenta(cliente, pedidoVacio);
        }, "Debería lanzar excepción si el pedido está vacío.");
    }

    @Test
    @DisplayName("Prueba de éxito: Venta sin descuentos")
    void testVentaSinDescuentos() {
        Cliente clienteNuevo = new Cliente(2, "Nuevo", 0, false, "España");
        Factura factura = tienda.realizarVenta(clienteNuevo, pedidoLleno);
        assertEquals(100.2f, factura.getTotalFinal(), 0.01, "El total final debe ser igual al total del pedido si no hay descuentos.");
    }

    @Test
    @DisplayName("Prueba de éxito: Venta con descuento de fidelidad")
    void testVentaConDescuentoFidelidad() {
        Cliente clienteFiel = new Cliente(3, "Fiel", 6, false, "España"); // 6 años -> 5% dto
        Factura factura = tienda.realizarVenta(clienteFiel, pedidoLleno);
        float totalEsperado = 100.2f * 0.95f; // 95.19
        assertEquals(totalEsperado, factura.getTotalFinal(), 0.01, "Debería aplicarse un 5% de descuento por fidelidad.");
    }

    @Test
    @DisplayName("Prueba de éxito: Venta con descuento VIP y fidelidad")
    void testVentaConDescuentoVipYFidelidad() {
        Cliente clienteTop = new Cliente(4, "Top", 11, true, "España"); // 11 años -> 10% dto + 10% VIP
        Factura factura = tienda.realizarVenta(clienteTop, pedidoLleno);
        float totalEsperado = 100.2f * 0.80f; // 80.16
        assertEquals(totalEsperado, factura.getTotalFinal(), 0.01, "Debería aplicarse un 20% de descuento total (fidelidad + VIP).");
    }
}