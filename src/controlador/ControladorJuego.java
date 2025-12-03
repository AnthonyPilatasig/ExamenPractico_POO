package controlador;

import modelo.Juego;
import modelo.Tablero;
import modelo.Casilla;
import excepciones.*;
import persistencia.GestorGuardado;
import vista.VistaConsola;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ControladorJuego {
    private Juego juego;
    private VistaConsola vista;
    private Scanner scanner;
    private GestorGuardado gestorGuardado;
    
    public ControladorJuego() {
        this.vista = new VistaConsola();
        this.scanner = new Scanner(System.in);
        this.gestorGuardado = new GestorGuardado();
    }
    
    public void iniciar() {
        boolean ejecutando = true;
        
        while (ejecutando) {
            vista.mostrarMenuPrincipal();
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        nuevoJuego();
                        break;
                    case 2:
                        cargarJuego();
                        break;
                    case 3:
                        mostrarEstadisticas();
                        break;
                    case 4:
                        vista.mostrarInstrucciones();
                        break;
                    case 5:
                        ejecutando = false;
                        System.out.println("¡Gracias por jugar!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine(); // Limpiar entrada inválida
            }
        }
    }
    
    private void nuevoJuego() {
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        
        if (nombre.trim().isEmpty()) {
            nombre = "Jugador";
        }
        
        juego = new Juego(nombre);
        jugar();
    }
    
    private void cargarJuego() {
        try {
            juego = gestorGuardado.cargarJuego();
            if (juego != null) {
                System.out.println("Juego cargado exitosamente!");
                jugar();
            }
        } catch (JuegoGuardadoException e) {
            System.out.println("Error al cargar el juego: " + e.getMessage());
        }
    }
    
    private void jugar() {
        vista.mostrarInstrucciones();
        
        while (juego.estaEnJuego()) {
            vista.mostrarTablero(juego.getTablero());
            System.out.print("\nIngresa tu jugada: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            
            try {
                procesarEntrada(entrada);
            } catch (EntradaInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (CasillaYaDescubiertaException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            if (!juego.estaEnJuego()) {
                vista.mostrarTablero(juego.getTablero());
                if (juego.isJuegoGanado()) {
                    vista.mostrarVictoria();
                } else {
                    vista.mostrarDerrota();
                }
            }
        }
    }
    
    private void procesarEntrada(String entrada) throws EntradaInvalidaException, CasillaYaDescubiertaException {
        if (entrada.equalsIgnoreCase("salir")) {
            System.out.println("Saliendo del juego...");
            System.exit(0);
        } else if (entrada.equalsIgnoreCase("guardar")) {
            guardarJuego();
        } else if (entrada.equalsIgnoreCase("cargar")) {
            cargarJuego();
        } else if (entrada.equalsIgnoreCase("ayuda")) {
            vista.mostrarInstrucciones();
        } else if (entrada.equalsIgnoreCase("estadisticas")) {
            mostrarEstadisticasJuego();
        } else if (entrada.matches("[A-J][0-9]M?")) {
            procesarJugada(entrada);
        } else {
            throw new EntradaInvalidaException("Formato inválido. Usa A5 o A5m");
        }
    }
    
    private void procesarJugada(String entrada) throws CasillaYaDescubiertaException, EntradaInvalidaException {
        char letraColumna = entrada.charAt(0);
        char digitoFila = entrada.charAt(1);
        boolean esMarca = entrada.length() == 3 && entrada.charAt(2) == 'M';
        
        int columna = letraColumna - 'A';
        int fila = Character.getNumericValue(digitoFila);
        
        if (fila < 0 || fila >= juego.getTablero().getFilas() || 
            columna < 0 || columna >= juego.getTablero().getColumnas()) {
            throw new EntradaInvalidaException("Coordenadas fuera del tablero");
        }
        
        Casilla casilla = juego.getTablero().getCasillas()[fila][columna];
        if (!esMarca && casilla.estaDescubierta()) {
            throw new CasillaYaDescubiertaException("Esta casilla ya fue descubierta");
        }
        
        if (!esMarca && casilla.estaMarcada()) {
            throw new EntradaInvalidaException("No puedes descubrir una casilla marcada. Desmárcala primero.");
        }
        
        juego.realizarJugada(fila, columna, esMarca);
    }
    
    private void guardarJuego() {
        try {
            gestorGuardado.guardarJuego(juego);
            System.out.println("Juego guardado exitosamente!");
        } catch (JuegoGuardadoException e) {
            System.out.println("Error al guardar el juego: " + e.getMessage());
        }
    }
    
    private void mostrarEstadisticas() {
        try {
            Juego juegoCargado = gestorGuardado.cargarJuego();
            if (juegoCargado != null) {
                vista.mostrarEstadisticas(juegoCargado.getJugador().toString());
            }
        } catch (JuegoGuardadoException e) {
            System.out.println("No hay estadísticas disponibles.");
        }
    }
    
    private void mostrarEstadisticasJuego() {
        if (juego != null) {
            vista.mostrarEstadisticas(juego.getJugador().toString());
        }
    }
}