package ve.edu.ucab.lab.modelo;

import java.time.LocalDate;

/**
 * Representa una transacción realizada por un cliente.
 * Esta clase almacena la información de las transacciones, incluyendo los detalles del cliente,
 * tipo de movimiento, monto de la operación, tipo de cuenta, y la fecha de la transacción.
 *
 * @author Manuel Antias
 * @author Daniel Agudelo
 * @version 2.25.2
 */
public class Transacciones {

    /**
     * Nombre del cliente que realizó la transacción.
     */
    private String nombreCliente;

    /**
     * Apellido del cliente que realizó la transacción.
     */
    private String apellidoCliente;

    /**
     * Número de teléfono del cliente que realizó la transacción.
     */
    private String numeroTelfCliente;

    /**
     * Fecha en que se realizó la transacción.
     */
    private LocalDate fechaTransaccion;

    /**
     * Tipo de movimiento realizado en la transacción (e.g., "depósito", "retiro").
     */
    private String movimiento;

    /**
     * Monto de la transacción realizada.
     */
    private double monto;

    /**
     * Tipo de cuenta del cliente relacionada con la transacción (e.g., "ahorro", "corriente").
     */
    private String tipoCuenta;

    /**
     * Fecha en formato String relacionada con la transacción.
     */
    private String fechita;

    /**
     * Monto relacionado con una prueba (si aplica) en la transacción.
     */
    private String montoPrueba;

    /**
     * Constructor de la clase Transacciones.
     * Inicializa los valores de la transacción con los datos proporcionados.
     *
     * @param fechaTransaccion Fecha en que se realiza la transacción.
     * @param movimiento Tipo de movimiento realizado (e.g., "depósito", "retiro").
     * @param nombreCliente Nombre del cliente que realizó la transacción.
     * @param apellidoCliente Apellido del cliente que realizó la transacción.
     * @param numeroTelfCliente Número de teléfono del cliente.
     * @param monto Monto de la transacción realizada.
     * @param tipoCuenta Tipo de cuenta del cliente (e.g., "ahorro", "corriente").
     * @param fechita Fecha en formato String relacionada con la transacción.
     * @param montoPrueba Monto relacionado con una prueba, si aplica.
     */
    public Transacciones(LocalDate fechaTransaccion, String movimiento, String nombreCliente, String apellidoCliente,
                         String numeroTelfCliente, double monto, String tipoCuenta, String fechita, String montoPrueba) {
        this.fechaTransaccion = fechaTransaccion;
        this.movimiento = movimiento;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.numeroTelfCliente = numeroTelfCliente;
        this.monto = monto;
        this.tipoCuenta = tipoCuenta;
        this.fechita = fechita;
        this.montoPrueba = montoPrueba;
    }

    /**
     * Obtiene la fecha de la transacción.
     *
     * @return La fecha de la transacción.
     */
    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * Establece la fecha de la transacción.
     *
     * @param fechaTransaccion La fecha de la transacción a establecer.
     */
    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    /**
     * Obtiene el tipo de movimiento realizado en la transacción.
     *
     * @return El tipo de movimiento (e.g., "depósito", "retiro").
     */
    public String getMovimiento() {
        return movimiento;
    }

    /**
     * Establece el tipo de movimiento de la transacción.
     *
     * @param movimiento El tipo de movimiento a establecer.
     */
    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Obtiene el nombre del cliente que realizó la transacción.
     *
     * @return El nombre del cliente.
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente que realizó la transacción.
     *
     * @param nombreCliente El nombre del cliente a establecer.
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el apellido del cliente que realizó la transacción.
     *
     * @return El apellido del cliente.
     */
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    /**
     * Establece el apellido del cliente que realizó la transacción.
     *
     * @param apellidoCliente El apellido del cliente a establecer.
     */
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    /**
     * Obtiene el número de teléfono del cliente que realizó la transacción.
     *
     * @return El número de teléfono del cliente.
     */
    public String getNumeroTelfCliente() {
        return numeroTelfCliente;
    }

    /**
     * Establece el número de teléfono del cliente que realizó la transacción.
     *
     * @param numeroTelfCliente El número de teléfono del cliente a establecer.
     */
    public void setNumeroTelfCliente(String numeroTelfCliente) {
        this.numeroTelfCliente = numeroTelfCliente;
    }

    /**
     * Obtiene el monto de la transacción realizada.
     *
     * @return El monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción realizada.
     *
     * @param monto El monto de la transacción a establecer.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el tipo de cuenta del cliente relacionado con la transacción.
     *
     * @return El tipo de cuenta (e.g., "ahorro", "corriente").
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Establece el tipo de cuenta del cliente relacionado con la transacción.
     *
     * @param tipoCuenta El tipo de cuenta a establecer.
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Obtiene la fecha en formato String relacionada con la transacción.
     *
     * @return La fecha en formato String.
     */
    public String getFechita() {
        return fechita;
    }

    /**
     * Establece la fecha en formato String relacionada con la transacción.
     *
     * @param fechita La fecha en formato String a establecer.
     */
    public void setFechita(String fechita) {
        this.fechita = fechita;
    }

    /**
     * Obtiene el monto relacionado con una prueba en la transacción.
     *
     * @return El monto de la prueba (si aplica).
     */
    public String getMontoPrueba() {
        return montoPrueba;
    }

    /**
     * Establece el monto relacionado con una prueba en la transacción.
     *
     * @param montoPrueba El monto de la prueba a establecer.
     */
    public void setMontoPrueba(String montoPrueba) {
        this.montoPrueba = montoPrueba;
    }
}
