package modelo;

import java.io.Serializable;

public class Juego implements Serializable {
	
	private static final long seriaVersionUID = 1L;
    private Tablero tablero;
    private Jugador jugador;
    private boolean enJuego;
    private boolean juegoGanado;
    
    public Juego(String nombreJugador) {
        this.tablero = new Tablero();
        this.jugador = new Jugador(nombreJugador);
        this.enJuego = true;
        this.juegoGanado = false;
    }
    
    public Juego(Tablero tablero, Jugador jugador) {
        this.tablero = tablero;
        this.jugador = jugador;
        this.enJuego = true;
        this.juegoGanado = false;
    }
    
    public boolean realizarJugada(int fila, int columna, boolean esMarca) {
        if (!enJuego) {
            return false;
        }
        
        if (esMarca) {
            tablero.marcarCasilla(fila, columna);
            return false;
        } else {
            boolean perdio = tablero.descubrirCasilla(fila, columna);
            
            if (perdio) {
                enJuego = false;
                juegoGanado = false;
                jugador.incrementarPartidasPerdidas();
                tablero.revelarTodasMinas();
                return true;
            }
            
            if (tablero.juegoGanado()) {
                enJuego = false;
                juegoGanado = true;
                jugador.incrementarPartidasGanadas();
                return true;
            }
        }
        return false;
    }
    
    // Getters
    public Tablero getTablero() { return tablero; }
    public Jugador getJugador() { return jugador; }
    public boolean estaEnJuego() { return enJuego; }
    public boolean isJuegoGanado() { return juegoGanado; }
}