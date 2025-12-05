package pruebas;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import org.junit.jupiter.api.*;
import persistencia.Csv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TestCsv {

    private Csv exportador;
    private Juego juego;

    @BeforeEach
    public void setup() {
        exportador = new Csv();

        Jugador jugador = new Jugador("Carlos");
        jugador.incrementarPartidasGanadas();
        jugador.incrementarPartidasGanadas();
        jugador.incrementarPartidasPerdidas();

        juego = new Juego(new Tablero(), jugador);
    }

    @Test
    public void testExportarJuego_CreaArchivoYContenidoCorrecto() throws IOException {

        exportador.exportarJuego(juego);

        File archivo = new File(exportador.getRutaArchivo());
        assertTrue(archivo.exists(), "El archivo CSV no fue creado.");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String header = reader.readLine();
            String data = reader.readLine();

            assertEquals("nombre,ganadas,perdidas", header);

            assertEquals("Carlos,2,1", data);
        }
    }
}