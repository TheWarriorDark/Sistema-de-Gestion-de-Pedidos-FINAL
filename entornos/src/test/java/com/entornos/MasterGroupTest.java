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

}