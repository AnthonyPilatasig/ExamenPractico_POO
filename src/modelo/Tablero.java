package modelo;

import java.io.Serializable;
import java.util.Random;

public class Tablero implements Serializable {
	
	private static final long serialVeraionUID = 1L;
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final int MINAS = 10;
    
    private Casilla[][] casillas;
    private int casillasDescubiertas;
    private int minasMarcadas;
    
    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
        colocarMinas();
        calcularMinasAlrededor();
        casillasDescubiertas = 0;
        minasMarcadas = 0;
    }
    
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }
    
    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        
        while (minasColocadas < MINAS) {
            int fila = rand.nextInt(FILAS);
            int columna = rand.nextInt(COLUMNAS);
            
            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].setTieneMina(true);
                minasColocadas++;
            }
        }
    }
    
    private void calcularMinasAlrededor() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!casillas[i][j].tieneMina()) {
                    int contador = contarMinasAlrededor(i, j);
                    casillas[i][j].setMinasAlrededor(contador);
                }
            }
        }
    }
    
    private int contarMinasAlrededor(int fila, int columna) {
        int contador = 0;
        
        for (int i = Math.max(0, fila - 1); i <= Math.min(FILAS - 1, fila + 1); i++) {
            for (int j = Math.max(0, columna - 1); j <= Math.min(COLUMNAS - 1, columna + 1); j++) {
                if (!(i == fila && j == columna) && casillas[i][j].tieneMina()) {
                    contador++;
                }
            }
        }
        return contador;
    }
    
    public boolean descubrirCasilla(int fila, int columna) {
        if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
            return false;
        }
        
        Casilla casilla = casillas[fila][columna];
        
        if (casilla.estaDescubierta() || casilla.estaMarcada()) {
            return false;
        }
        
        casilla.setDescubierta(true);
        casillasDescubiertas++;
        
        if (casilla.tieneMina()) {
            return true; // Perdió
        }
        
        if (casilla.getMinasAlrededor() == 0) {
            descubrirCasillasVacias(fila, columna);
        }
        
        return false;
    }
    
    private void descubrirCasillasVacias(int fila, int columna) {
        for (int i = Math.max(0, fila - 1); i <= Math.min(FILAS - 1, fila + 1); i++) {
            for (int j = Math.max(0, columna - 1); j <= Math.min(COLUMNAS - 1, columna + 1); j++) {
                if (!(i == fila && j == columna) && !casillas[i][j].estaDescubierta() 
                    && !casillas[i][j].estaMarcada()) {
                    descubrirCasilla(i, j);
                }
            }
        }
    }
    
    public void marcarCasilla(int fila, int columna) {
        if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS 
            && !casillas[fila][columna].estaDescubierta()) {
            Casilla casilla = casillas[fila][columna];
            casilla.setMarcada(!casilla.estaMarcada());
            
            if (casilla.estaMarcada() && casilla.tieneMina()) {
                minasMarcadas++;
            } else if (!casilla.estaMarcada() && casilla.tieneMina()) {
                minasMarcadas--;
            }
        }
    }
    
    public boolean juegoGanado() {
        return casillasDescubiertas == (FILAS * COLUMNAS - MINAS);
    }
    
    public void revelarTodasMinas() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (casillas[i][j].tieneMina()) {
                    casillas[i][j].setDescubierta(true);
                }
            }
        }
    }
    
    // Getters
    public Casilla[][] getCasillas() { return casillas; }
    public int getFilas() { return FILAS; }
    public int getColumnas() { return COLUMNAS; }
    public int getMinas() { return MINAS; }
    public int getMinasMarcadas() { return minasMarcadas; }
    public int getCasillasDescubiertas() { return casillasDescubiertas; }
}