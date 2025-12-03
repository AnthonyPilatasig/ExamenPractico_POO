package vista;

import modelo.Tablero;
import modelo.Casilla;

public class ConsolaVista {
    public void mostrarTablero(Tablero tablero, boolean mostrarMinas) {
        System.out.print("   ");
        for (int i = 1; i <= tablero.getTamaño(); i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < tablero.getTamaño(); i++) {
            System.out.print((char)('A' + i) + "  ");
            for (int j = 0; j < tablero.getTamaño(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                if (c.isMarcada()) {
                    System.out.print(" F ");
                } else if (!c.isDescubierta() && !mostrarMinas) {
                    System.out.print(" . ");
                } else if (c.isTieneMina()) {
                    System.out.print(" X ");
                } else if (c.getMinasAdyacentes() > 0) {
                    System.out.printf(" %d ", c.getMinasAdyacentes());
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarInstrucciones() {
        System.out.println("=== BUSCAMINAS ===");
        System.out.println("Comandos:");
        System.out.println("  A5    - Descubrir casilla");
        System.out.println("  M A5  - Marcar/desmarcar mina");
        System.out.println("  S     - Guardar partida");
        System.out.println("  C     - Cargar partida");
        System.out.println("  Q     - Salir");
        System.out.println();
    }
}