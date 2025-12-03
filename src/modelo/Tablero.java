package modelo;

import java.util.Random;

public class Tablero {
    private Casilla[][] casillas;
    private int tamaño;
    private int minas;
    private int casillasDescubiertas;

    public Tablero(int tamaño, int minas) {
        this.tamaño = tamaño;
        this.minas = minas;
        this.casillasDescubiertas = 0;
        inicializarTablero();
        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void inicializarTablero() {
        casillas = new Casilla[tamaño][tamaño];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < minas) {
            int x = rand.nextInt(tamaño);
            int y = rand.nextInt(tamaño);
            if (!casillas[x][y].isTieneMina()) {
                casillas[x][y].setTieneMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (casillas[i][j].isTieneMina()) continue;
                int contador = 0;
                for (int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if (ni >= 0 && ni < tamaño && nj >= 0 && nj < tamaño && casillas[ni][nj].isTieneMina()) {
                        contador++;
                    }
                }
                casillas[i][j].setMinasAdyacentes(contador);
            }
        }
    }

    public boolean descubrirCasilla(int x, int y) {
        if (x < 0 || x >= tamaño || y < 0 || y >= tamaño || casillas[x][y].isDescubierta() || casillas[x][y].isMarcada()) {
            return false;
        }

        casillas[x][y].setDescubierta(true);
        casillasDescubiertas++;

        if (casillas[x][y].isTieneMina()) {
            return false; // Piso la mina
        }

        if (casillas[x][y].getMinasAdyacentes() == 0) {
            // Expansion automatica
            descubrirAdyacentes(x, y);
        }

        return true;
    }

    private void descubrirAdyacentes(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < tamaño && ny >= 0 && ny < tamaño) {
                descubrirCasilla(nx, ny);
            }
        }
    }

    public void marcarCasilla(int x, int y) {
        if (x >= 0 && x < tamaño && y >= 0 && y < tamaño) {
            casillas[x][y].setMarcada(!casillas[x][y].isMarcada());
        }
    }

    public boolean victoria() {
        return casillasDescubiertas == (tamaño * tamaño - minas);
    }

    public Casilla[][] getCasillas() { return casillas; }
    public int getTamaño() { return tamaño; }
    public int getMinas() { return minas; }
}