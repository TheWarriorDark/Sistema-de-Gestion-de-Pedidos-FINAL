package com.entornos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FacturaTest {

    private Cliente cliente;
    private Pedido pedido;
    private Factura factura;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Test Client", 5, true, "España");
        pedido = new Pedido(cliente);
        pedido.addProducto(new ProductoFisico(1, "Test Product", 100f, 1f, "España"));
        factura = new Factura(cliente, pedido, 100.0, 21.0, 0.0, 12.1, 108.9);
    }

    @Test
    @DisplayName("Prueba de cobertura: Getters y toString de Factura")
    void testGettersAndToString() {
        assertNotNull(factura.getCodigoFactura());
        assertEquals(cliente, factura.getCliente());
        assertEquals(pedido, factura.getPedido());
        assertEquals(100.0, factura.getTotalNeto());
        assertEquals(21.0, factura.getTotalIva());
        assertEquals(0.0, factura.getTotalEnvio());
        assertEquals(12.1, factura.getDescuento());
        assertEquals(108.9, factura.getTotalFinal());
        assertEquals(LocalDate.now(), factura.getFechaEmision());

        String facturaStr = factura.toString();
        assertTrue(facturaStr.contains("FACT-"));
        assertTrue(facturaStr.contains("Test Client"));
        assertTrue(facturaStr.contains("108"));
    }
}