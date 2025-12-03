package modelo;

public class Casilla {
    private boolean tieneMina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAdyacentes;

    public Casilla() {
        this.tieneMina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }

    // Getters y setters
    public boolean isTieneMina() { return tieneMina; }
    public void setTieneMina(boolean tieneMina) { this.tieneMina = tieneMina; }

    public boolean isDescubierta() { return descubierta; }
    public void setDescubierta(boolean descubierta) { this.descubierta = descubierta; }

    public boolean isMarcada() { return marcada; }
    public void setMarcada(boolean marcada) { this.marcada = marcada; }

    public int getMinasAdyacentes() { return minasAdyacentes; }
    public void setMinasAdyacentes(int minasAdyacentes) { this.minasAdyacentes = minasAdyacentes; }

    public void incrementarMinasAdyacentes() {
        this.minasAdyacentes++;
    }
}