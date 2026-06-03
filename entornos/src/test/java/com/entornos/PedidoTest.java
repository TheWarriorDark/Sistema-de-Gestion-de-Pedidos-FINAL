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
        cliente = new Cliente("C-123", "Test Client", 3, true, "Alemania");
        pedido = new Pedido(cliente);
        productoFisico = new ProductoFisico("P-1", "Silla Gamer", 150.0f, 20.0f, "Alemania"); // 150 + 10 envío + 10 sobrepeso = 170.0
        productoDigital = new ProductoDigital("D-1", "Antivirus", 50.0f, "XYZ-123", 500f); // Precio final: 47.5
    }

    //Pruebas de Éxito (AssertTrue/AssertEquals)

    @Test
    @DisplayName("Prueba de éxito: Añadir un producto al pedido")
    void testAnadirProducto() {
        pedido.anadirProducto(productoFisico);
        assertTrue(pedido.mostrarResumen().contains(productoFisico.getNombre()), "El producto físico debería estar en el resumen del pedido.");
        assertEquals(201.5f, pedido.calcularTotal(), 0.001, "El total debe reflejar el producto añadido con IVA y envío.");
    }

    @Test
    @DisplayName("Prueba de éxito: Eliminar un producto existente del pedido")
    void testEliminarProductoExistente() {
        pedido.anadirProducto(productoFisico);
        assertTrue(pedido.eliminarProducto(productoFisico), "La eliminación de un producto existente debería devolver true.");
        assertThrows(IllegalStateException.class, () -> pedido.calcularTotal(), "El total de un pedido vacío debería lanzar excepción.");
    }

    @Test
    @DisplayName("Prueba de error: Calcular total de un pedido vacío lanza excepción")
    void testCalcularTotalPedidoVacioLanzaExcepcion() {
        assertThrows(IllegalStateException.class, () -> pedido.calcularTotal(), "Debería lanzar IllegalStateException al procesar un pedido vacío.");
    }

    @Test
    @DisplayName("Prueba de cobertura: Constructor por defecto de Pedido")
    void testConstructorPorDefecto() {
        Pedido pedidoDefecto = new Pedido();
        assertNotNull(pedidoDefecto.mostrarResumen());
        assertThrows(IllegalStateException.class, () -> pedidoDefecto.calcularTotal());
    }

    //Pruebas de Error (AssertFalse/AssertNotEquals)

    @Test
    @DisplayName("Prueba de error: Eliminar un producto no existente del pedido")
    void testEliminarProductoNoExistente() {
        pedido.anadirProducto(productoFisico);
        ProductoFisico otroProducto = new ProductoFisico("P-99", "Mesa", 200f, 30f, "España");
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
        // Total esperado con las nuevas reglas
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
        ProductoFisico pf1 = new ProductoFisico("P-1", "Silla", 150.0f, 20.0f, "Alemania"); // 170.0
        ProductoDigital pd1 = new ProductoDigital("D-1", "Antivirus", 50.0f, "XYZ-123", 500f); // 47.5
        ProductoFisico pf2 = new ProductoFisico("P-2", "Mesa", 250.0f, 40.0f, "España"); // 250 + 0 envío + 30 sobrepeso = 280.0

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