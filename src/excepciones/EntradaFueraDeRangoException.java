package excepciones;

public class EntradaFueraDeRangoException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntradaFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}
