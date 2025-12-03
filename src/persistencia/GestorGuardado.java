package persistencia;

import modelo.Juego;
import excepciones.JuegoGuardadoException;
import java.io.*;

public class GestorGuardado {
    private static final String ARCHIVO_GUARDADO = "juego_guardado.dat";
    private static final String ARCHIVO_ESTADISTICAS = "estadisticas.txt";
    
    public void guardarJuego(Juego juego) throws JuegoGuardadoException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_GUARDADO))) {
            oos.writeObject(juego);
            guardarEstadisticas(juego);
        } catch (IOException e) {
            throw new JuegoGuardadoException("Error al guardar el juego: " + e.getMessage());
        }
    }
    
    public Juego cargarJuego() throws JuegoGuardadoException {
        File archivo = new File(ARCHIVO_GUARDADO);
        if (!archivo.exists()) {
            throw new JuegoGuardadoException("No hay juego guardado.");
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_GUARDADO))) {
            return (Juego) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new JuegoGuardadoException("Error al cargar el juego: " + e.getMessage());
        }
    }
    
    private void guardarEstadisticas(Juego juego) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_ESTADISTICAS, true))) {
            writer.println(juego.getJugador().toString() + " - " + 
                          java.time.LocalDateTime.now());
        } catch (IOException e) {
            System.out.println("Error al guardar estadísticas: " + e.getMessage());
        }
    }
    
    public String cargarEstadisticas() throws JuegoGuardadoException {
        StringBuilder estadisticas = new StringBuilder();
        File archivo = new File(ARCHIVO_ESTADISTICAS);
        
        if (!archivo.exists()) {
            throw new JuegoGuardadoException("No hay estadísticas disponibles.");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_ESTADISTICAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                estadisticas.append(linea).append("\n");
            }
        } catch (IOException e) {
            throw new JuegoGuardadoException("Error al cargar estadísticas: " + e.getMessage());
        }
        
        return estadisticas.toString();
    }
}