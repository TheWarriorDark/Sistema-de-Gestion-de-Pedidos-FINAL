package com.entornos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {

    @Test
    @DisplayName("Prueba de cobertura: Ejecución completa del main")
    void testMain() {
        // Ejecuta el método main exactamente igual que lo haría la terminal
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}