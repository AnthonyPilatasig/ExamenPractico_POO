package excepciones;

public class JugadorSinNombreException extends Exception {

    public JugadorSinNombreException() {
        super("El jugador debe ingresar un nombre para continuar.");
    }

    public JugadorSinNombreException(String mensaje) {
        super(mensaje);
    }
}
