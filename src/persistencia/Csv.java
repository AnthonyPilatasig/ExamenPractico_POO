package persistencia;
import modelo.Juego;
import modelo.Jugador;
import java.io.FileWriter;
import java.io.IOException;

public class Csv {

    private static final String ARCHIVO_CSV = "estadisticas.csv";

    public void exportarJuego(Juego juego) throws IOException {
        Jugador jugador = juego.getJugador();

        try (FileWriter writer = new FileWriter(ARCHIVO_CSV)) {

            writer.write("nombre,ganadas,perdidas\n");

            writer.write(jugador.getNombre() + "," +
                    jugador.getPartidasGanadas() + "," +
                    jugador.getPartidasPerdidas() + "\n");
        }
    }

    public String getRutaArchivo() {
        return ARCHIVO_CSV;
    }
}