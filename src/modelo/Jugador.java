package modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private String nombre;
    private int partidasGanadas;
    private int partidasPerdidas;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getPartidasGanadas() { return partidasGanadas; }
    public void incrementarPartidasGanadas() { partidasGanadas++; }
    public int getPartidasPerdidas() { return partidasPerdidas; }
    public void incrementarPartidasPerdidas() { partidasPerdidas++; }
    
    @Override
    public String toString() {
        return nombre + " - Ganadas: " + partidasGanadas + " - Perdidas: " + partidasPerdidas;
    }
}