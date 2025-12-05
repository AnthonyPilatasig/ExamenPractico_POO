package pruebas;

import controlador.ControladorJuego;
import excepciones.CasillaYaDescubiertaException;
import excepciones.EntradaInvalidaException;
import modelo.Tablero;
import modelo.Juego;
import modelo.Jugador;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class TestControlador {

    private Method obtenerMetodoProcesarEntrada(ControladorJuego controlador) throws NoSuchMethodException {
        Method metodo = ControladorJuego.class.getDeclaredMethod("procesarEntrada", String.class);
        metodo.setAccessible(true);
        return metodo;
    }

    @Test
    public void procesarEntrada_formatoValidoDescubrir_noLanzaExcepcion() throws Exception {
        ControladorJuego controlador = new ControladorJuego();
        Tablero tablero = new Tablero();
        
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                tablero.getCasillas()[i][j].setTieneMina(false);
                tablero.getCasillas()[i][j].setMinasAlrededor(0);
                tablero.getCasillas()[i][j].setDescubierta(false);
                tablero.getCasillas()[i][j].setMarcada(false);
            }
        }
        Jugador jugador = new Jugador("Test");
        Juego juego = new Juego(tablero, jugador);

        java.lang.reflect.Field campoJuego = ControladorJuego.class.getDeclaredField("juego");
        campoJuego.setAccessible(true);
        campoJuego.set(controlador, juego);

        Method metodo = obtenerMetodoProcesarEntrada(controlador);

        assertDoesNotThrow(() -> {
            try {
                metodo.invoke(controlador, "A0");
            } catch (InvocationTargetException ite) {

                throw ite.getTargetException();
            }
        });
    }

    @Test
    public void procesarEntrada_formatoMarcaValido_noLanzaExcepcion() throws Exception {
        ControladorJuego controlador = new ControladorJuego();

        Tablero tablero = new Tablero();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                tablero.getCasillas()[i][j].setTieneMina(false);
                tablero.getCasillas()[i][j].setMinasAlrededor(0);
                tablero.getCasillas()[i][j].setDescubierta(false);
                tablero.getCasillas()[i][j].setMarcada(false);
            }
        }
        Juego juego = new Juego(tablero, new Jugador("T"));
        java.lang.reflect.Field campoJuego = ControladorJuego.class.getDeclaredField("juego");
        campoJuego.setAccessible(true);
        campoJuego.set(controlador, juego);

        Method metodo = obtenerMetodoProcesarEntrada(controlador);
 
        assertDoesNotThrow(() -> {
            try {
                metodo.invoke(controlador, "A0M");
            } catch (InvocationTargetException ite) {
                throw ite.getTargetException();
            }
        });

        assertTrue(juego.getTablero().getCasillas()[0][0].estaMarcada());
    }

    @Test
    public void procesarEntrada_formatoInvalido_lanzaEntradaInvalidaException() throws Exception {
        ControladorJuego controlador = new ControladorJuego();
        Method metodo = obtenerMetodoProcesarEntrada(controlador);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            metodo.invoke(controlador, "INVALID"); 
        });

        Throwable target = thrown.getTargetException();
        assertTrue(target instanceof EntradaInvalidaException);
    }

    @Test
    public void procesarEntrada_descubrirCasillaYaDescubierta_lanzaCasillaYaDescubiertaException() throws Exception {
        ControladorJuego controlador = new ControladorJuego();

        Tablero tablero = new Tablero();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                tablero.getCasillas()[i][j].setTieneMina(false);
                tablero.getCasillas()[i][j].setMinasAlrededor(0);
                tablero.getCasillas()[i][j].setDescubierta(false);
                tablero.getCasillas()[i][j].setMarcada(false);
            }
        }
        tablero.getCasillas()[0][0].setDescubierta(true);

        Juego juego = new Juego(tablero, new Jugador("T"));
        java.lang.reflect.Field campoJuego = ControladorJuego.class.getDeclaredField("juego");
        campoJuego.setAccessible(true);
        campoJuego.set(controlador, juego);

        Method metodo = obtenerMetodoProcesarEntrada(controlador);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            metodo.invoke(controlador, "A0");
        });

        Throwable target = thrown.getTargetException();
        assertTrue(target instanceof CasillaYaDescubiertaException);
    }
}