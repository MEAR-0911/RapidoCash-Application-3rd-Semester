package ve.edu.ucab.lab.modelo;

import java.time.LocalDate;

/**
 * Clase que representa una transacción realizada por un administrador en el sistema.
 * Esta clase almacena detalles de las operaciones administrativas, como la fecha,
 * el tipo de movimiento realizado, el usuario que realizó la operación y el número
 * de teléfono del cliente involucrado.
 *
 * @author Manuel Antias
 * @version 2.25.2
 */

public class TransaccionesAdministradores {
    /**
     * Fecha en que la transacción fue realizada.
     * Almacena la fecha como un objeto {@link LocalDate}.
     */

    private LocalDate fecha;
    /**
     * Usuario del administrador que realizó la transacción.
     * Este campo contiene el nombre de usuario del administrador.
     */

    private String userAdmi;
    /**
     * Tipo de movimiento realizado por el administrador.
     * Este campo describe la acción administrativa (por ejemplo, "Actualizar", "Eliminar Usuario").
     */

    private String movimientoAdmi;
    /**
     * Número de teléfono del cliente que fue afectado por la transacción.
     * Este campo almacena el teléfono del cliente involucrado en la operación.
     */
    private String numeroTelefonoCliente;
    /**
     * Fecha de la transacción en formato de cadena.
     * Este campo almacena la fecha en un formato de texto legible (por ejemplo, "12-01-2025").
     */
    private String fechaString;
    /**
     * Constructor que inicializa los detalles de la transacción administrativa.
     *
     * @param fecha Fecha de la transacción (tipo {@link LocalDate}).
     * @param userAdmi Nombre de usuario del administrador que realizó la transacción.
     * @param movimientoAdmi Tipo de movimiento realizado (e.g., "Actualizar", "Eliminar Usuario").
     * @param numeroTelefonoCliente Número de teléfono del cliente afectado por la transacción.
     * @param fechaString Fecha de la transacción en formato de cadena.
     */

    public TransaccionesAdministradores(LocalDate fecha, String userAdmi, String movimientoAdmi,
                                        String numeroTelefonoCliente, String fechaString) {
        this.fecha = fecha;
        this.userAdmi = userAdmi;
        this.movimientoAdmi = movimientoAdmi;
        this.numeroTelefonoCliente = numeroTelefonoCliente;
        this.fechaString = fechaString;
    }
    /**
     * Obtiene la fecha de la transacción.
     *
     * @return La fecha en que la transacción fue realizada.
     */
    public LocalDate getFecha() {

        return fecha;
    }
    /**
     * Establece la fecha de la transacción.
     *
     * @param fecha La nueva fecha de la transacción.
     */

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**
     * Obtiene el nombre del usuario administrador que realizó la transacción.
     *
     * @return El nombre de usuario del administrador.
     */

    public String getUserAdmi() {
        return userAdmi;
    }
    /**
     * Establece el nombre del usuario administrador que realizó la transacción.
     *
     * @param userAdmi El nombre de usuario del administrador.
     */

    public void setUserAdmi(String userAdmi) {
        this.userAdmi = userAdmi;
    }
    /**
     * Obtiene el tipo de movimiento realizado por el administrador.
     *
     * @return El tipo de movimiento realizado (por ejemplo, "Actualizar", "Eliminar Usuario").
     */

    public String getMovimientoAdmi() {
        return movimientoAdmi;
    }
    /**
     * Establece el tipo de movimiento realizado por el administrador.
     *
     * @param movimientoAdmi El tipo de movimiento realizado.
     */

    public void setMovimientoAdmi(String movimientoAdmi) {
        this.movimientoAdmi = movimientoAdmi;
    }
    /**
     * Obtiene el número de teléfono del cliente afectado por la transacción.
     *
     * @return El número de teléfono del cliente.
     */

    public String getNumeroTelefonoCliente() {
        return numeroTelefonoCliente;
    }
    /**
     * Establece el número de teléfono del cliente afectado por la transacción.
     *
     * @param numeroTelefonoCliente El número de teléfono del cliente.
     */

    public void setNumeroTelefonoCliente(String numeroTelefonoCliente) {
        this.numeroTelefonoCliente = numeroTelefonoCliente;
    }
    /**
     * Obtiene la fecha de la transacción en formato de cadena.
     *
     * @return La fecha de la transacción en formato de cadena (por ejemplo, "12-01-2025").
     */

    public String getFechaString() {
        return fechaString;
    }
    /**
     * Establece la fecha de la transacción en formato de cadena.
     *
     * @param fechaString La fecha de la transacción en formato de cadena.
     */

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }
}
