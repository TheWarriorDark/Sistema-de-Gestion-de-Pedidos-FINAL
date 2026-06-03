package com.entornos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductoTest {

    @Test
    @DisplayName("Prueba de éxito: Calcular precio de ProductoFisico correctamente")
    void testCalcularPrecioProductoFisico() {
        ProductoFisico producto = new ProductoFisico(1, "Libro", 20.0f, 5.0f, "Francia");
        float expectedPrecio = 20.5f; // 20.0 (precio base) + 0.5 (envio por peso)
        float actualPrecio = producto.calcularPrecio();
        assertEquals(expectedPrecio, actualPrecio, "El precio del producto físico no se calcula correctamente.");
    }

    @Test
    @DisplayName("Prueba de éxito: Calcular precio de ProductoDigital correctamente con descuento")
    void testCalcularPrecioProductoDigital() {
        ProductoDigital producto = new ProductoDigital(1, "Ebook", 20.0f, "licencia123", 150.0f);
        float expectedPrecio = 19.0f; // 20.0 * (1 - 0.05)
        float actualPrecio = producto.calcularPrecio();
        assertEquals(expectedPrecio, actualPrecio, 0.001, "El precio del producto digital no se calcula correctamente.");
    }

    @Test
    @DisplayName("Prueba de error: Lanzar excepción al crear producto con precio negativo")
    void testConstructorProductoConPrecioNegativo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProductoFisico(99, "Producto Malo", -10.0f, 5.0f, "España");
        }, "Debería lanzarse una IllegalArgumentException para precios negativos.");

        assertEquals("El precio no puede ser negativo.", exception.getMessage());
    }

    @Test
    @DisplayName("Prueba de cobertura: Constructores, getters, setters y toString de Productos")
    void testGettersSettersToString() {
        // Producto Físico
        ProductoFisico pf = new ProductoFisico(); // Constructor por defecto
        pf.setId(123);
        pf.setNombre("Monitor");
        pf.setPrecioBase(150.0f);
        pf.setPeso(12.5f);
        pf.setDestino("Italia");
        
        assertEquals(123, pf.getId());
        assertEquals("Monitor", pf.getNombre());
        assertEquals(150.0f, pf.getPrecioBase());
        assertEquals(12.5f, pf.getPeso());
        assertEquals("Italia", pf.getDestino());
        assertNotNull(pf.toString());

        // Producto Digital
        ProductoDigital pd = new ProductoDigital(); // Constructor por defecto
        pd.setId(123);
        pd.setNombre("Software");
        pd.setPrecioBase(100.0f);
        pd.setLicencia("LIC-999");
        pd.setTamanoEnMb(500.0f);
        
        assertEquals(123, pd.getId());
        assertEquals("Software", pd.getNombre());
        assertEquals(100.0f, pd.getPrecioBase());
        assertEquals("LIC-999", pd.getLicencia());
        assertEquals(500.0f, pd.getTamanoEnMb());
        assertNotNull(pd.toString());
    }
}