package com.entornos;

public class Main {
    public static void main(String[] args) {
        String mensajeSalida = "";
        mensajeSalida += "Prueba del sistema\n\n";

        //Cliente
        Cliente agenteJ = new Cliente(
            "Agente J", 
            "j@mib.gov", 
            "504 de Battery Dr, Nueva York"
        );
        
        mensajeSalida += "Cliente creado: " + agenteJ.getNombre() + "\n\n";
        
        //Productos
        ProductoFisico neurolizadorVisual = new ProductoFisico("Neurolizador Visual", 1250.75f, 50.0f);
        ProductoDigital manualAgente = new ProductoDigital("Manual del Agente MIB (PDF)", 75.50f, "MIB Internal Use Only", 10.0f);
        ProductoFisico trajeNegro = new ProductoFisico("Traje Negro Estandar", 450.0f, 25.0f); 
        ProductoFisico gafasSol = new ProductoFisico("Gafas de Sol Ray-Ban", 175.25f, 10.0f);

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
        
        // Verificacion del resultado
        // 1300.75 (Neurolizador) + 71.725 (Manual) + 185.25 (Gafas) = 1557.725
        
        if(pedido.calcularTotal() == 1557.725f){ mensajeSalida += "Todo bien, todo correcto y yo que me alegro.";}
        else { mensajeSalida += "Neuralizacion inminente."; }

        System.out.println(mensajeSalida);

    }
}