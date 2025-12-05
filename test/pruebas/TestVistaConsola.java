package pruebas;
import modelo.Tablero;
import vista.VistaConsola;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class TestVistaConsola {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void mostrarTablero_imprimeCabeceraYEstadisticas() {
        VistaConsola vista = new VistaConsola();
        Tablero tablero = new Tablero();

        vista.mostrarTablero(tablero);

        String salida = outContent.toString();
        assertTrue(salida.contains("A B C D E F G H I J"));
        assertTrue(salida.contains("Minas restantes:"));
        assertTrue(salida.contains("Casillas descubiertas:"));
    }

    @Test
    public void mostrarInstrucciones_imprimeTextoInstrucciones() {
        VistaConsola vista = new VistaConsola();
        vista.mostrarInstrucciones();

        String salida = outContent.toString();
        assertTrue(salida.contains("=== INSTRUCCIONES ==="));
        assertTrue(salida.contains("Ingresa coordenadas"));
        assertTrue(salida.contains("'guardar'"));
    }

    @Test
    public void mostrarVictoria_yDerrota_imprimenMensajes() {
        VistaConsola vista = new VistaConsola();

        vista.mostrarVictoria();
        vista.mostrarDerrota();

        String salida = outContent.toString();
        assertTrue(salida.contains("¡FELICITACIONES!") || salida.contains("¡HAS GANADO"));
        assertTrue(salida.contains("¡BOOM! ¡PERDISTE!") || salida.contains("Has pisado una mina"));
    }
}