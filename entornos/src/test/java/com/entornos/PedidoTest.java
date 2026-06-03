package com.entornos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
        cliente = new Cliente(123, "Test Client", 3, true, "Alemania");
        pedido = new Pedido(cliente);
        productoFisico = new ProductoFisico(1, "Silla Gamer", 150.0f, 20.0f, "Alemania");
        productoDigital = new ProductoDigital(1, "Antivirus", 50.0f, "XYZ-123", 500f);
    }

    //Pruebas de Éxito (AssertTrue/AssertEquals)

    @Test
    @DisplayName("Prueba de éxito: Añadir un producto al pedido")
    void testAnadirProducto() {
        pedido.addProducto(productoFisico);
        assertTrue(pedido.mostrarResumen().contains(productoFisico.getNombre()), "El producto físico debería estar en el resumen del pedido.");
        assertEquals(201.5f, pedido.calcularTotal(), 0.001, "El total debe reflejar el producto añadido con IVA y envío.");
    }

    @Test
    @DisplayName("Prueba de éxito: Eliminar un producto existente del pedido")
    void testEliminarProductoExistente() {
        pedido.addProducto(productoFisico);
        assertTrue(pedido.eliminarProducto(productoFisico), "La eliminación de un producto existente debería devolver true.");
        assertThrows(IllegalArgumentException.class, pedido::calcularTotal, "El total de un pedido vacío debería lanzar excepción.");
    }

    @Test
    @DisplayName("Prueba de error: Calcular total de un pedido vacío lanza excepción")
    void testCalcularTotalPedidoVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, pedido::calcularTotal, "Debería lanzar IllegalArgumentException al procesar un pedido vacío.");
    }

    @Test
    @DisplayName("Prueba de cobertura: Constructor por defecto de Pedido")
    void testConstructorPorDefecto() {
        Pedido pedidoDefecto = new Pedido();
        assertNotNull(pedidoDefecto.mostrarResumen());
        assertThrows(IllegalArgumentException.class, pedidoDefecto::calcularTotal);
    }

    //Pruebas de Error (AssertFalse/AssertNotEquals)

    @Test
    @DisplayName("Prueba de error: Eliminar un producto no existente del pedido")
    void testEliminarProductoNoExistente() {
        pedido.addProducto(productoFisico);
        ProductoFisico otroProducto = new ProductoFisico(99, "Mesa", 200f, 30f, "España");
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
        pedido.addProducto(productoFisico);
        pedido.addProducto(productoDigital);
        // Total esperado con las nuevas reglas
        assertNotEquals(100.0f, pedido.calcularTotal(), "El total calculado no debería ser un valor incorrecto y arbitrario.");
    }

    @Test
    @DisplayName("Prueba de error: Intentar eliminar un producto nulo")
    void testEliminarProductoNulo() {
        pedido.addProducto(productoFisico);
        assertFalse(pedido.eliminarProducto(null), "La eliminación de un producto nulo debería devolver false.");
    }

    //Pruebas Parametrizadas

    static Stream<Object[]> casosDePruebaCalcularTotal() {
        ProductoFisico pf1 = new ProductoFisico(1, "Silla", 150.0f, 20.0f, "Alemania");
        ProductoDigital pd1 = new ProductoDigital(1, "Antivirus", 50.0f, "XYZ-123", 500f);
        ProductoFisico pf2 = new ProductoFisico(2, "Mesa", 250.0f, 40.0f, "España");

        return Stream.of(
            // Caso 1: Un producto físico
            new Object[]{Arrays.asList(pf1), 201.5f},
            // Caso 2: Un producto digital
            new Object[]{Arrays.asList(pd1), 58.0f},
            // Caso 3: Múltiples productos (físico y digital)
            new Object[]{Arrays.asList(pf1, pd1), 259.5f},
            // Caso 4: Múltiples productos físicos
            new Object[]{Arrays.asList(pf1, pf2), 534.0f}
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