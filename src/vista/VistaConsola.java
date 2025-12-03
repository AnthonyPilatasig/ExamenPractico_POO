package vista;

import modelo.Juego;
import modelo.Tablero;
import modelo.Casilla;

public class VistaConsola {
    
    public void mostrarTablero(Tablero tablero) {
        System.out.println("\n   A B C D E F G H I J");
        System.out.println("  +-------------------+");
        
        Casilla[][] casillas = tablero.getCasillas();
        
        for (int i = 0; i < tablero.getFilas(); i++) {
            System.out.print(i + " |");
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print(" " + casillas[i][j].toString());
            }
            System.out.println(" |");
        }
        System.out.println("  +-------------------+");
        
        System.out.println("\nMinas restantes: " + (tablero.getMinas() - tablero.getMinasMarcadas()));
        System.out.println("Casillas descubiertas: " + tablero.getCasillasDescubiertas() + 
                         "/" + (tablero.getFilas() * tablero.getColumnas() - tablero.getMinas()));
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarInstrucciones() {
        System.out.println("\n=== INSTRUCCIONES ===");
        System.out.println("1. Ingresa coordenadas (ejemplo: A5)");
        System.out.println("2. Para marcar/desmarcar una mina, agrega 'm' (ejemplo: A5m)");
        System.out.println("3. Comandos especiales:");
        System.out.println("   - 'guardar': Guardar el juego");
        System.out.println("   - 'cargar': Cargar juego guardado");
        System.out.println("   - 'salir': Salir del juego");
        System.out.println("   - 'ayuda': Mostrar estas instrucciones");
        System.out.println("   - 'estadisticas': Mostrar estadísticas");
        System.out.println("====================\n");
    }
    
    public void mostrarMenuPrincipal() {
        System.out.println("\n=== BUSCAMINAS POO ===");
        System.out.println("1. Nuevo Juego");
        System.out.println("2. Cargar Juego");
        System.out.println("3. Ver Estadísticas");
        System.out.println("4. Instrucciones");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }
    
    public void mostrarVictoria() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║         ¡FELICITACIONES!         ║");
        System.out.println("║     ¡HAS GANADO EL JUEGO!       ║");
        System.out.println("╚══════════════════════════════════╝");
    }
    
    public void mostrarDerrota() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║          ¡BOOM! ¡PERDISTE!        ║");
        System.out.println("║      Has pisado una mina         ║");
        System.out.println("╚══════════════════════════════════╝");
    }
    
    public void mostrarEstadisticas(String estadisticas) {
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println(estadisticas);
        System.out.println("====================\n");
    }
}