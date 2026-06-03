package com.entornos;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String mensajeSalida = "";
        mensajeSalida += "Prueba del sistema\n\n";

        //Cliente
        Cliente agenteJ = new Cliente(
            "C-001",
            "Agente J", 
            5,
            true,
            "Estados Unidos"
        );
        
        mensajeSalida += "Cliente creado: " + agenteJ.getNombre() + "\n\n";
        
        //Productos
        ProductoFisico neurolizadorVisual = new ProductoFisico("P-01", "Neurolizador Visual", 1250.75f, 2.5f, "Francia");
        ProductoDigital manualAgente = new ProductoDigital("D-01", "Manual del Agente MIB (PDF)", 75.50f, "MIB Internal Use Only", 10.0f);
        ProductoFisico trajeNegro = new ProductoFisico("P-02", "Traje Negro Estandar", 450.0f, 1.2f, "España"); 
        ProductoFisico gafasSol = new ProductoFisico("P-03", "Gafas de Sol Ray-Ban", 175.25f, 0.5f, "Italia");

        //Pedido
        Pedido pedido = new Pedido(agenteJ);
        
        pedido.anadirProducto(neurolizadorVisual);
        pedido.anadirProducto(manualAgente);
        pedido.anadirProducto(trajeNegro);
        pedido.anadirProducto(gafasSol);

        //Resultados
        mensajeSalida += "Resumen del Pedido\n";
        mensajeSalida += pedido.mostrarResumen() + "\n\n";

        // Prueba de eliminacion de producto
        mensajeSalida += "Probando eliminar producto\n";
        boolean eliminado = pedido.eliminarProducto(trajeNegro);
        if (eliminado) {
            mensajeSalida += "Producto '" + trajeNegro.getNombre() + "' eliminado con éxito.\n\n";
        } else {
            mensajeSalida += "No se pudo eliminar el producto '" + trajeNegro.getNombre() + "'.\n\n";
        }

        mensajeSalida += "Resumen del Pedido Final\n";
        mensajeSalida += pedido.mostrarResumen() + "\n\n";
        
        // Orquestación de la venta a través de la Tienda
        Tienda miTienda = new Tienda();
        try {
            Factura facturaFinal = miTienda.realizarVenta(agenteJ, pedido);
            mensajeSalida += "Venta procesada con éxito.\n\n";
            mensajeSalida += facturaFinal.toString();
        } catch (IllegalStateException e) {
            mensajeSalida += "Error al procesar la venta: " + e.getMessage();
        }
        
        LOGGER.info(mensajeSalida);

    }
}