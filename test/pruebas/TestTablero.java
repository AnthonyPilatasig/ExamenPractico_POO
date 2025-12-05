package pruebas;

import modelo.Casilla;
import modelo.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTablero {

    @Test
    public void descubrirCasilla_fueraDeRango_retornaFalse() {
        Tablero tablero = new Tablero();
        assertFalse(tablero.descubrirCasilla(-1, 0));
        assertFalse(tablero.descubrirCasilla(0, -1));
        assertFalse(tablero.descubrirCasilla(tablero.getFilas(), 0));
        assertFalse(tablero.descubrirCasilla(0, tablero.getColumnas()));
    }

    @Test
    public void descubrirCasilla_casillaConMina_retornaTrue() {
        Tablero tablero = new Tablero();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                c.setTieneMina(false);
                c.setDescubierta(false);
                c.setMarcada(false);
                c.setMinasAlrededor(0);
            }
        }
        tablero.getCasillas()[1][1].setTieneMina(true);

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

        boolean perdio = tablero.descubrirCasilla(1, 1);
        assertTrue(perdio);
        assertTrue(tablero.getCasillas()[1][1].estaDescubierta());
    }

    @Test
    public void marcarCasilla_toggleMarca_yActualizaContadorMinasMarcadas() {
        Tablero tablero = new Tablero();

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                c.setTieneMina(false);
                c.setDescubierta(false);
                c.setMarcada(false);
                c.setMinasAlrededor(0);
            }
        }
        tablero.getCasillas()[0][0].setTieneMina(true);

        tablero.marcarCasilla(0, 0);
        assertTrue(tablero.getCasillas()[0][0].estaMarcada());
        assertEquals(1, tablero.getMinasMarcadas());

        tablero.marcarCasilla(0, 0);
        assertFalse(tablero.getCasillas()[0][0].estaMarcada());
        assertEquals(0, tablero.getMinasMarcadas());
    }

    @Test
    public void juegoGanado_descubrirTodasNoMinas_retornaTrue() {
        Tablero tablero = new Tablero();

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                c.setTieneMina(false);
                c.setDescubierta(false);
                c.setMarcada(false);
            }
        }

        int minas = tablero.getMinas();
        int colocadas = 0;
        outer:
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (colocadas < minas) {
                    tablero.getCasillas()[i][j].setTieneMina(true);
                    colocadas++;
                } else break outer;
            }
        }

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

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (!tablero.getCasillas()[i][j].tieneMina()) {
                    tablero.descubrirCasilla(i, j);
                }
            }
        }

        assertTrue(tablero.juegoGanado());
    }
}