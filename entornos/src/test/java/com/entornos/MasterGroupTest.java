package com.entornos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MasterGroupTest {

	@DisplayName("La venta genera metadatos basicos de factura")
	@Test
	public void testRealizarVentaGeneraMetadatosFactura() {
		Tienda tienda = new Tienda();
		Cliente cliente = new Cliente(1, "Ana", 4, false, "España");
		Pedido pedido = new Pedido(10, cliente);
		pedido.addProducto(new Producto(7, "Producto base", 15), 2);

		Factura factura = tienda.realizarVenta(cliente, pedido);

		assertNotNull(factura.getCodigoFactura(), "La factura deberia generar un codigo no nulo");
		assertEquals(LocalDate.now(), factura.getFechaEmision(),
				"La fecha de emision deberia ser la fecha actual");
		assertTrue(factura.getCodigoFactura().startsWith("FACT-" + LocalDate.now()),
				"El codigo de factura deberia empezar por el prefijo FACT- seguido de la fecha actual");
	}

    @DisplayName("La venta falla si el pedido no contiene productos")
	@Test
	public void testRealizarVentaConPedidoVacioFalla() {
		Tienda tienda = new Tienda();
		Cliente cliente = new Cliente(2, "Luis", 1, false, "España");
		Pedido pedido = new Pedido(11, cliente);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> tienda.realizarVenta(cliente, pedido),
				"Una venta con un pedido vacio deberia lanzar IllegalArgumentException");

		assertEquals(Pedido.PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE, exception.getMessage(),
				"El mensaje de error deberia indicar que la lista de productos esta vacia");
	}

    @DisplayName("El envio sin productos fisicos usa solo la tarifa base del pais")
	@Test
	public void testCalcularEnvioSinProductosFisicos() {
		Cliente cliente = new Cliente(3, "Marta", 2, false, "Portugal");
		Pedido pedido = new Pedido(12, cliente);
		pedido.addProducto(new Producto(1, "Licencia", 20), 1);
		pedido.addProducto(new ProductoDigital(2, "Curso", 30), 2);

		assertEquals(5.0, pedido.calcularEnvio(cliente.getPais()),
				"Un pedido sin productos fisicos esta aplicando solo la tarifa base del pais");
	}

    @DisplayName("Diagnostico: un pedido solo digital no deberia generar gastos de envio")
	@Test
	public void testCalcularEnvioSoloDigitalDebeSerCero() {
		Cliente cliente = new Cliente(30, "Marta", 2, false, "Portugal");
		Pedido pedido = new Pedido(120, cliente);
		pedido.addProducto(new ProductoDigital(10, "Curso", 30), 2);

		assertEquals(0.0, pedido.calcularEnvio(cliente.getPais()),
				"Un pedido compuesto solo por productos digitales no deberia generar gastos de envio");
	}
    
    @DisplayName("La venta falla si el pais del cliente es nulo")
        @Test
        public void testRealizarVentaConPaisNuloFalla() {
            Tienda tienda = new Tienda();
            Cliente cliente = new Cliente(31, "Marta", 2, false, null);
            Pedido pedido = new Pedido(121, cliente);
            pedido.addProducto(new ProductoFisico(11, "Libro", 20, 2), 1);

            assertThrows(IllegalArgumentException.class,
                    () -> tienda.realizarVenta(cliente, pedido),
                    "La venta deberia fallar si el pais del cliente es nulo");
        }

        @DisplayName("La venta falla si el pais del cliente esta vacio")
	@Test
	public void testRealizarVentaConPaisVacioFalla() {
		Tienda tienda = new Tienda();
		Cliente cliente = new Cliente(32, "Marta", 2, false, "   ");
		Pedido pedido = new Pedido(122, cliente);
		pedido.addProducto(new ProductoFisico(12, "Libro", 20, 2), 1);

		assertThrows(IllegalArgumentException.class,
				() -> tienda.realizarVenta(cliente, pedido),
				"La venta deberia fallar si el pais del cliente esta vacio o en blanco");
	}
    
    @DisplayName("La venta de un pedido solo fisico no deberia generar IVA digital")
	@Test
	public void testRealizarVentaSoloFisicoSinIvaDigital() {
		Tienda tienda = new Tienda();
		Cliente cliente = new Cliente(33, "Elena", 0, false, "España");
		Pedido pedido = new Pedido(123, cliente);
		pedido.addProducto(new ProductoFisico(13, "Teclado", 50, 2), 2);

		Factura factura = tienda.realizarVenta(cliente, pedido);

		assertEquals(100.0, factura.getTotalNeto(),
				"La venta solo con productos fisicos deberia sumar correctamente el total neto");
		assertEquals(0.4, factura.getTotalEnvio(),
				"La venta solo con productos fisicos deberia calcular el envio segun el peso total");
		assertEquals(0.0, factura.getTotalIva(),
				"La venta solo con productos fisicos no deberia incluir IVA digital");
	}
}