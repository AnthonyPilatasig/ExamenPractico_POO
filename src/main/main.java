package main;

import controlador.ControladorJuego;

public class main {

    public static void main(String[] args) {
        System.out.println("=== BUSCAMINAS POO - UNIVERSIDAD POLITÉCNICA SALESIANA ===");
        System.out.println("Desarrollado por: [Nombre del Equipo]");
        System.out.println("Asignatura: Programación Orientada a Objetos\n");
        
        ControladorJuego controlador = new ControladorJuego();
        controlador.iniciar();
    }
}
