package ve.edu.ucab.lab.modelo;

/**
 *En esta clase se genera la plantilla del objeto Cuenta Bancaria del cliente.
 *
 * @author Antias Manuel
 * @version 2.14.2
 */

public class Cuenta {
    /**
     * Atributo tipoCuenteCliente, en este atributo se guarda el tipo de cuenta del Cliente.
     */
    private String tipoCuentaCliente;
    /**
     * Atributo estadoCuentaCliente, en este atributo se guarda el estado de cuenta del Cliente (String Activo/Bloqueado).
     */
    private String cuentaCliente;
    /**
     * Atributo estadoCuentaCliente, en este atributo se guarda el estado de cuenta del cliente (Boolean true/false).
     */
    private Boolean estadoCuentaCliente;
    /**
     * Atributo saldoCliente, en este atributo se guarda el saldo actual del cliente.
     */
    private double saldoCliente;

    /**
     *Constructor del modelo Cuenta.
     * @param tipoCuentaCliente tipo de cuenta bancaria del Cliente.
     * @param cuentaCliente estado de cuenta del Cliente (String Activo/Bloqueado).
     * @param saldoCliente saldo actual en la cuenta del cliente.
     */
    public Cuenta(String tipoCuentaCliente, String cuentaCliente, double saldoCliente) {
        this.tipoCuentaCliente = tipoCuentaCliente;
        this.cuentaCliente = cuentaCliente;
        this.saldoCliente = saldoCliente;
        if (cuentaCliente.equals("activo")){
            this.estadoCuentaCliente = true;
        }
        else
            this.estadoCuentaCliente = false;
    }

    /**
     * Getter tipoCuentaCliente.
     * @return tipoCuentaCliente, valor del atributo tipoCuentaCliente del Modelo Cuenta.
     */
    public String getTipoCuentaCliente() {
        return tipoCuentaCliente;
    }

    /**
     * Setter tipoCuentaCliente.
     * @param tipoCuentaCliente parámetro tipoCuentaCliente para modificar en el atributo del Modelo Cuenta.
     */
    public void setTipoCuentaCliente(String tipoCuentaCliente) {
        this.tipoCuentaCliente = tipoCuentaCliente;
    }

    /**
     * Getter cuentaCliente.
     * @return cuentaCliente, valor del atributo cuentaCliente del Modelo Cuenta.
     */
    public String getCuentaCliente() {
        return cuentaCliente;
    }

    /**
     * Setter cuentaCliente.
     * @param cuentaCliente parámetro cuentaCliente para modificar en el atributo del Modelo Cuenta
     *                      (String Activo/Bloqueado).
     */
    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    /**
     * Getter estadoCuentaCliente.
     *
     * @return estadoCuentaCliente, valor que devuelve el estado de cuenta de un cliente (true/false).
     */
    public Boolean getEstadoCuentaCliente() {
        return estadoCuentaCliente;
    }

    /**
     *Getter saldoCliente.
     * @return saldoCliente, valor del atributo saldoCliente del Modelo Cuenta.
     */
    public double getSaldoCliente() {
        return saldoCliente;
    }

    /**
     *Setter saldoCliente.
     * @param saldoCliente parámetro saldoCliente para modificar en el atributo del Modelo Cuenta.
     */
    public void setSaldoCliente(double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }

}