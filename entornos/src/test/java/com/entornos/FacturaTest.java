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
        cliente = new Cliente("C-1", "Test Client", 5, true, "España");
        pedido = new Pedido(cliente);
        pedido.anadirProducto(new ProductoFisico("P-1", "Test Product", 100f, 1f, "España"));
        factura = new Factura(cliente, pedido, 100f, 21f, 0f, 12.1f, 108.9f);
    }

    @Test
    @DisplayName("Prueba de cobertura: Getters y toString de Factura")
    void testGettersAndToString() {
        assertNotNull(factura.getCodigoFactura());
        assertEquals(cliente, factura.getCliente());
        assertEquals(pedido, factura.getPedido());
        assertEquals(100f, factura.getTotalNeto());
        assertEquals(21f, factura.getTotalIva());
        assertEquals(0f, factura.getTotalEnvio());
        assertEquals(12.1f, factura.getDescuentosAplicados());
        assertEquals(108.9f, factura.getTotalFinal());
        assertEquals(LocalDate.now(), factura.getFechaEmision());

        String facturaStr = factura.toString();
        assertTrue(facturaStr.contains("FACT-"));
        assertTrue(facturaStr.contains("Test Client"));
        assertTrue(facturaStr.contains("108"));
    }
}