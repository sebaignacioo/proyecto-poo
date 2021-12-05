package aplicacion.models;

/**
 * Clase que define un apoderado. Clase hija de Persona.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class Apoderado extends Persona {
    private int telefono;
    private String email;

    /**
     * Genera un objeto de tipo Apoderado
     *
     * @param rut       RUT del apoderado
     * @param nombres   Nombres del apoderado
     * @param apPaterno Apellido paterno del apoderado
     * @param apMaterno Apellido materno del apoderado
     * @param telefono  Teléfono del apoderado
     * @param email     Correo electrónico del apoderado
     */
    public Apoderado(String rut, String nombres, String apPaterno, String apMaterno, int telefono, String email) {
        super(rut, nombres, apPaterno, apMaterno);
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene el teléfono del apoderado
     *
     * @return Teléfono de contacto del apoderado
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Obtiene el correo electrónico del apoderado
     *
     * @return Correo electrónico del apoderado
     */
    public String getEmail() {
        return email;
    }

    /**
     * Actualiza el teléfono de contacto del apoderado
     *
     * @param telefono Teléfono de contacto del apoderado
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Actualiza el correo electrónico del apoderado
     *
     * @param email Correo electrónico del apoderado
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Permite obtener un String con los datos del apoderado para impresión.
     *
     * @return String con los datos del apoderado.
     */
    @Override
    public String toString() {
        return super.toString("Apoderado") +
                "    Datos de contacto\n" +
                "      -> Teléfono       : " + telefono + "\n" +
                "      -> Email          : " + email + "\n";
    }

    /**
     * Permite obtener un String con los datos del apoderado para impresión.
     *
     * @param titulo Titulo a agregar a la impresión por pantalla.
     * @return String con los datos del apoderado.
     */
    @Override
    public String toString(String titulo) {
        return super.toString(titulo) +
                "    Datos de contacto\n" +
                "      -> Teléfono       : " + telefono + "\n" +
                "      -> Email          : " + email + "\n";
    }
}