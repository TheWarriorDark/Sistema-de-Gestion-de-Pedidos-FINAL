package com.entornos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    @DisplayName("Prueba de cobertura: Constructor por defecto y getters")
    void testConstructorPorDefecto() {
        Cliente cliente = new Cliente();
        assertEquals("Cliente no especificado", cliente.getNombre());
        assertEquals("correo@desconocido.com", cliente.getCorreo());
        assertEquals("Direccion no especificada", cliente.getDireccion());
    }

    @Test
    @DisplayName("Prueba de cobertura: Constructor con parámetros, setters y toString")
    void testSettersYToString() {
        Cliente cliente = new Cliente("Moe", "moe@taberna.com", "Avenida Siempreviva");
        
        // Probar setters
        cliente.setNombre("Ned Flanders");
        cliente.setCorreo("ned@iglesia.com");
        cliente.setDireccion("Avenida Siempreviva 740");

        // Validar que los setters funcionaron
        assertEquals("Ned Flanders", cliente.getNombre());
        assertEquals("ned@iglesia.com", cliente.getCorreo());
        assertEquals("Avenida Siempreviva 740", cliente.getDireccion());

        // Probar toString
        String cadena = cliente.toString();
        assertNotNull(cadena);
        assertTrue(cadena.contains("Ned Flanders"));
        assertTrue(cadena.contains("ned@iglesia.com"));
        assertTrue(cadena.contains("Avenida Siempreviva 740"));
    }
}