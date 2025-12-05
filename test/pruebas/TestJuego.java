package pruebas;

import modelo.Casilla;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import excepciones.JugadorSinNombreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestJuego {

    @Test
    public void realizarJugada_descubrirMina_terminaJuegoPerdido() throws JugadorSinNombreException {

        Tablero tablero = new Tablero();

        // Limpiar tablero
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                c.setTieneMina(false);
                c.setDescubierta(false);
                c.setMarcada(false);
                c.setMinasAlrededor(0);
            }
        }

        // Colocar una mina en (2,3)
        tablero.getCasillas()[2][3].setTieneMina(true);

        // Recalcular minas alrededor
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                int contador = 0;
                for (int ii = Math.max(0, i - 1); ii <= Math.min(tablero.getFilas() - 1, i + 1); ii++) {
                    for (int jj = Math.max(0, j - 1); jj <= Math.min(tablero.getColumnas() - 1, j + 1); jj++) {
                        if (!(ii == i && jj == j) && tablero.getCasillas()[ii][jj].tieneMina()) {
                            contador++;
                        }
                    }
                }
                tablero.getCasillas()[i][j].setMinasAlrededor(contador);
            }
        }

        Jugador jugador = new Jugador("JugadorTest");
        Juego juego = new Juego(tablero, jugador);

        boolean resultado = juego.realizarJugada(2, 3, false);

        assertTrue(resultado);
        assertFalse(juego.estaEnJuego());
        assertFalse(juego.isJuegoGanado());
        assertEquals(1, juego.getJugador().getPartidasPerdidas());
    }

    @Test
    public void realizarJugada_marcarNoTerminaJuego() throws JugadorSinNombreException {

        Tablero tablero = new Tablero();

        // Limpiar tablero
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                c.setTieneMina(false);
                c.setDescubierta(false);
                c.setMarcada(false);
                c.setMinasAlrededor(0);
            }
        }

        // Colocar una mina en (0,0)
        tablero.getCasillas()[0][0].setTieneMina(true);

        // Recalcular minas alrededor
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                int contador = 0;
                for (int ii = Math.max(0, i - 1); ii <= Math.min(tablero.getFilas() - 1, i + 1); ii++) {
                    for (int jj = Math.max(0, j - 1); jj <= Math.min(tablero.getColumnas() - 1, j + 1); jj++) {
                        if (!(ii == i && jj == j) && tablero.getCasillas()[ii][jj].tieneMina()) {
                            contador++;
                        }
                    }
                }
                tablero.getCasillas()[i][j].setMinasAlrededor(contador);
            }
        }

        Jugador jugador = new Jugador("J");
        Juego juego = new Juego(tablero, jugador);

        boolean resultado = juego.realizarJugada(0, 0, true);

        assertFalse(resultado);                    // marcar no termina el juego
        assertTrue(juego.estaEnJuego());          // sigue en juego
        assertEquals(0, juego.getJugador().getPartidasGanadas());
        assertEquals(0, juego.getJugador().getPartidasPerdidas());
        assertTrue(tablero.getCasillas()[0][0].estaMarcada());
    }
}
