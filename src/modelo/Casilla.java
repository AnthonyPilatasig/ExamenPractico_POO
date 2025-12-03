package modelo;

public class Casilla {
    private int fila;
    private int columna;
    private boolean tieneMina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAlrededor;
    
    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tieneMina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAlrededor = 0;
    }
    
    // Getters y Setters
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public boolean tieneMina() { return tieneMina; }
    public void setTieneMina(boolean tieneMina) { this.tieneMina = tieneMina; }
    public boolean estaDescubierta() { return descubierta; }
    public void setDescubierta(boolean descubierta) { this.descubierta = descubierta; }
    public boolean estaMarcada() { return marcada; }
    public void setMarcada(boolean marcada) { this.marcada = marcada; }
    public int getMinasAlrededor() { return minasAlrededor; }
    public void setMinasAlrededor(int minasAlrededor) { 
        this.minasAlrededor = minasAlrededor; 
    }
    
    @Override
    public String toString() {
        if (!descubierta) {
            return marcada ? "⚑" : "■";
        }
        if (tieneMina) {
            return "X";
        }
        return minasAlrededor > 0 ? String.valueOf(minasAlrededor) : " ";
    }
}