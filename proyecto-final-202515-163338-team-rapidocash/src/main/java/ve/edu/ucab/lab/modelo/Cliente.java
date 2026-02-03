package ve.edu.ucab.lab.modelo;

/**
 *En esta clase se genera el modelo o plantilla del objeto "Cliente" la cual extiende de la clase
 *{@linkplain Usuario}
 * @author Antias Manuel
 * @version 2.14.2
 */

public class Cliente extends Usuario{
  /**
   * Atributo nombreCliente, en este atributo se guarda el nombre del Cliente.
   */
  private String nombreCliente;
  /**
   * Atributo apellidoCliente, en este atributo se guarda el apellido del cliente.
   */
  private String apellidoCliente;
  /**
   * Atributo ciCliente, en este atributo se guarda la cédula de indentidad del cliente.
   */
  private String ciCliente;
  /**
   * Atributo telefonoCliente, en este atributo se guarda el número de teléfono del cliente.
   */
  private String telefonoCliente;
  /**
   * Atributo correoCliente, en este atributo se guarda la dirección de correo del cliente.
   */
  private String correoCliente;

  /**
   * Atributo cuenta, en este atributo se guardan los datos de la clase {@linkplain Cuenta}.
   */
  private Cuenta cuenta;

  /**
   * Constructor del Modelo Cliente.
   * @param pin pin del cliente heredada de la clase {@linkplain Usuario}.
   * @param nombreCliente nombre del cliente.
   * @param apellidoCliente apellido del cliente.
   * @param ciCliente Cédula de indetidad del cliente.
   * @param telefonoCliente número telefónico del cliente.
   * @param correoCliente dirección del correo electrónico del cliente.
   * @param cuenta cuenta bancaria del cliente.
   */
  public Cliente(String pin, String nombreCliente, String apellidoCliente, String ciCliente,
                 String telefonoCliente, String correoCliente,Cuenta cuenta) {
    super(pin);
    this.nombreCliente = nombreCliente;
    this.apellidoCliente = apellidoCliente;
    this.ciCliente = ciCliente;
    this.telefonoCliente = telefonoCliente;
    this.correoCliente = correoCliente;
    this.cuenta = cuenta;
  }

  /**
   *Getter nombreCliente.
   *@return nombreCliente, valor del atributo nombreCliente del Modelo Cliente.
   */
  public String getNombreCliente() {
    return nombreCliente;
  }

  /**
   *Setter nombreCliente.
   * @param nombreCliente parámetro nombreCliente para modificar en el atributo del Modelo Cliente.
   */
  public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
  }

  /**
   *Getter apellidoCliente.
   * @return apellidoCliente, valor del atributo apellidoCliente del Modelo Cliente.
   */
  public String getApellidoCliente() {
    return apellidoCliente;
  }

  /**
   *Setter apellidoCliente.
   * @param apellidoCliente parámetro apellidoCliente para modificar en el atributo del Modelo Cliente.
   */
  public void setApellidoCliente(String apellidoCliente) {
    this.apellidoCliente = apellidoCliente;
  }

  /**
   *Getter ciCliente.
   * @return ciCliente, valor del atributo ciCliente del Modelo Cliente.
   */
  public String getCiCliente() {
    return ciCliente;
  }

  /**
   *Setter ciCliente.
   * @param ciCliente parámetro ciCliente para modificar en el atributo del Modelo Cliente.
   */
  public void setCiCliente(String ciCliente) {
    this.ciCliente = ciCliente;
  }

  /**
   *Getter telefonoCliente.
   *@return telefonoCliente, valor del atributo telefonoCliente del Modelo Cliente.
   */
  public String getTelefonoCliente() {
    return telefonoCliente;
  }

  /**
   *Setter telefonoCliente.
   * @param telefonoCliente parámetro telefonoCliente para modificar en el atributo del Modelo Cliente.
   */
  public void setTelefonoCliente(String telefonoCliente) {
    this.telefonoCliente = telefonoCliente;
  }

  /**
   * Getter correoCliente.
   * @return valor del atributo correoCliente del Modelo Cliente.
   */
  public String getCorreoCliente() {
    return correoCliente;
  }

  /**
   *Setter correoCliente.
   *@param correoCliente parámetro correoCliente para modificar en el atributo del Modelo Cliente.
   */
  public void setCorreoCliente(String correoCliente) {
    this.correoCliente = correoCliente;
  }

  /**
   *Getter cuenta.
   * @return cuenta, S
   */
  public Cuenta getCuenta() {
    return cuenta;
  }

  /**
   *Setter cuenta.
   * @param cuenta parámetro cuenta para modificar en el atributo del Modelo Cliente.
   */
  public void setCuenta(Cuenta cuenta) {
    this.cuenta = cuenta;
  }
}