package com.entornos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class PedidoTest {

    private Cliente cliente;
    private Pedido pedido;
    private ProductoFisico productoFisico;
    private ProductoDigital productoDigital;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Test Client", "test@example.com", "123 Test St");
        pedido = new Pedido(cliente);
        productoFisico = new ProductoFisico("Silla Gamer", 150.0f, 20.0f); // Precio final: 170.0
        productoDigital = new ProductoDigital("Antivirus", 50.0f, "XYZ-123", 500f); // Precio final: 47.5
    }

    //Pruebas de Éxito (AssertTrue/AssertEquals)

    @Test
    @DisplayName("Prueba de éxito: Añadir un producto al pedido")
    void testAnadirProducto() {
        pedido.anadirProducto(productoFisico);
        assertTrue(pedido.mostrarResumen().contains(productoFisico.getNombre()), "El producto físico debería estar en el resumen del pedido.");
        assertEquals(170.0f, pedido.calcularTotal(), "El total debe reflejar el producto añadido.");
    }

    @Test
    @DisplayName("Prueba de éxito: Eliminar un producto existente del pedido")
    void testEliminarProductoExistente() {
        pedido.anadirProducto(productoFisico);
        assertTrue(pedido.eliminarProducto(productoFisico), "La eliminación de un producto existente debería devolver true.");
        assertEquals(0, pedido.calcularTotal(), "El total del pedido debería ser 0 después de eliminar el único producto.");
    }

    @Test
    @DisplayName("Prueba de éxito: Calcular total de un pedido vacío es cero")
    void testCalcularTotalPedidoVacio() {
        assertEquals(0.0f, pedido.calcularTotal(), "El total de un pedido vacío debería ser 0.");
    }

    @Test
    @DisplayName("Prueba de cobertura: Constructor por defecto de Pedido")
    void testConstructorPorDefecto() {
        Pedido pedidoDefecto = new Pedido();
        assertNotNull(pedidoDefecto.mostrarResumen());
        assertEquals(0.0f, pedidoDefecto.calcularTotal());
    }

    //Pruebas de Error (AssertFalse/AssertNotEquals)

    @Test
    @DisplayName("Prueba de error: Eliminar un producto no existente del pedido")
    void testEliminarProductoNoExistente() {
        pedido.anadirProducto(productoFisico);
        ProductoFisico otroProducto = new ProductoFisico("Mesa", 200f, 30f);
        assertFalse(pedido.eliminarProducto(otroProducto), "La eliminación de un producto no existente debería devolver false.");
    }
    
    @Test
    @DisplayName("Prueba de error: Eliminar un producto de un pedido vacío")
    void testEliminarProductoDePedidoVacio() {
        assertFalse(pedido.eliminarProducto(productoFisico), "La eliminación de un producto de un pedido vacío debería devolver false.");
    }

    @Test
    @DisplayName("Prueba de error: El total calculado no es un valor incorrecto")
    void testCalcularTotalNoEsIncorrecto() {
        pedido.anadirProducto(productoFisico);
        pedido.anadirProducto(productoDigital);
        // Total esperado: 170.0 + 47.5 = 217.5
        assertNotEquals(100.0f, pedido.calcularTotal(), "El total calculado no debería ser un valor incorrecto y arbitrario.");
    }

    @Test
    @DisplayName("Prueba de error: Intentar eliminar un producto nulo")
    void testEliminarProductoNulo() {
        pedido.anadirProducto(productoFisico);
        assertFalse(pedido.eliminarProducto(null), "La eliminación de un producto nulo debería devolver false.");
    }

    //Pruebas Parametrizadas

    static Stream<Object[]> casosDePruebaCalcularTotal() {
        ProductoFisico pf1 = new ProductoFisico("Silla", 150.0f, 20.0f); // 170.0
        ProductoDigital pd1 = new ProductoDigital("Antivirus", 50.0f, "XYZ-123", 500f); // 47.5
        ProductoFisico pf2 = new ProductoFisico("Mesa", 250.0f, 40.0f); // 290.0

        return Stream.of(
            // Caso 1: Lista vacía
            new Object[]{new ArrayList<Producto>(), 0.0f},
            // Caso 2: Un producto físico
            new Object[]{Arrays.asList(pf1), 170.0f},
            // Caso 3: Un producto digital
            new Object[]{Arrays.asList(pd1), 47.5f},
            // Caso 4: Múltiples productos (físico y digital)
            new Object[]{Arrays.asList(pf1, pd1), 217.5f},
            // Caso 5: Múltiples productos físicos
            new Object[]{Arrays.asList(pf1, pf2), 460.0f}
        );
    }

    @ParameterizedTest
    @MethodSource("casosDePruebaCalcularTotal")
    @DisplayName("Prueba parametrizada: Calcular total con diferentes listas de productos")
    void testCalcularTotalParametrizado(List<Producto> productos, float totalEsperado) {
        Pedido pedidoParametrizado = new Pedido(cliente, productos);

        float totalCalculado = pedidoParametrizado.calcularTotal();
        assertEquals(totalEsperado, totalCalculado, 0.001, "El total calculado para la lista de productos no es el esperado.");
    }
}