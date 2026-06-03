package com.entornos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    @DisplayName("Prueba de cobertura: Constructor por defecto y getters")
    void testConstructorPorDefecto() {
        Cliente cliente = new Cliente();
        assertEquals(0, cliente.getId());
        assertEquals("Cliente no especificado", cliente.getNombre());
        assertEquals(0, cliente.getAnosAntiguedad());
        assertFalse(cliente.isEsVip());
        assertEquals("No especificado", cliente.getPais());
    }

    @Test
    @DisplayName("Prueba de cobertura: Constructor con parámetros, setters y toString")
    void testSettersYToString() {
        Cliente cliente = new Cliente(1, "Moe", 2, false, "Francia");
        
        // Probar setters
        cliente.setId(2);
        cliente.setNombre("Ned Flanders");
        cliente.setAnosAntiguedad(5);
        cliente.setEsVip(true);
        cliente.setPais("Alemania");

        // Validar que los setters funcionaron
        assertEquals(2, cliente.getId());
        assertEquals("Ned Flanders", cliente.getNombre());
        assertEquals(5, cliente.getAnosAntiguedad());
        assertTrue(cliente.isEsVip());
        assertEquals("Alemania", cliente.getPais());

        // Probar toString
        String cadena = cliente.toString();
        assertNotNull(cadena);
        assertTrue(cadena.contains("Ned Flanders"));
        assertTrue(cadena.contains("Alemania"));
    }
}