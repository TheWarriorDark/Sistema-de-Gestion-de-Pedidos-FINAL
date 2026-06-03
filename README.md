# Proyecto - Sistema de Gestión de Pedidos (Versión Final)

## 📜 Descripción

Proyecto desarrollado para el módulo de **Entornos de Desarrollo** aplicando metodologías TDD (Test-Driven Development) y Clean Code. Implementación de un sistema en Java usando POO para gestionar pedidos, aplicar reglas financieras complejas (IVA, envíos por peso/país, descuentos VIP/Fidelidad), orquestar ventas mediante una Tienda y emitir Facturas inmutables.

## 📂 Estructura del Proyecto

*   `output`:Diagramas UML (.puml, .svg, .png) y cosas externas.
*   `output/UML_Codigo`: Código generado con el.
*   `src/main/java`: Código fuente de producción.
*   `src/test/java`: Batería de pruebas unitarias y de integración (JUnit 5).
*   `salida_cmd`: Salida de la cmd cuando se ejecuta el main.

## 🚀 Instrucciones de Ejecución

El proyecto utiliza **Maven** para la gestión de dependencias y la ejecución del ciclo de vida.

1.  **Para compilar el código:**
    ```sh
    mvn compile
    ```

2.  **Para ejecutar la batería de pruebas (100% Cobertura):**
    ```sh
    mvn test
    ```

3.  **Para ejecutar la simulación del sistema E2E (Main):**
    ```sh
    mvn exec:java -Dexec.mainClass="com.entornos.Main"
    ```

## 🛡️ Calidad y Pruebas del Sistema

El proyecto cumple con los más altos estándares de calidad de software:
*   **Flujo E2E Funcional:** Implementación completa del ciclo de vida de una venta orquestada en `Main` y verificada en `MainTest`.
*   **Pruebas de Robustez:** Sistema blindado contra corrupciones de estado mediante *Defensive Copying* en colecciones, validación de variables nulas/vacías (países), prevención de cobros cruzados (verificación de ID cliente) y control estricto de excepciones (`IllegalArgumentException`).
*   **Métricas No Funcionales:**
    *   **Mantenibilidad:** Código analizado con SonarQube (0 Code Smells), Complejidad Cognitiva reducida mediante extracción de métodos, y uso de sistema de *Logging* nativo.
    *   **Fiabilidad:** Redondeo preciso a 2 decimales usando variables `double` para el manejo de los importes financieros de la factura.
    *   **Cobertura:** 100% de cobertura de código validado mediante *JaCoCo*.
    *   **Eficiencia:** Estructuras de datos optimizadas (`HashMap` para asociar productos a cantidades).

## ▶️ Ejecución

![Ejecución del programa](https://i.imgur.com/XUc7czC.png)

 
