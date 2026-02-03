package ve.edu.ucab.lab.modelo;
/**
 * En esta clase se genera el modelo o plantilla del objeto "Administrador", la cual extiende de la clase
 * {@linkplain Usuario}
 *
 * @author Antias Manuel
 * @version 2.0.0
 */


public class Administrador extends Usuario {
  /**
   * Atributo nombreAdministrador, En este atributo se guarda el nombre del Administrador.
   */
  private String nombreAdministrador;
  /**
   * Atributo apellidoAdministrador, En este atributo se guarda el apellido del Administrador.
   */
  private String apellidoAdministrador;
  /**
   * Atributo correoAdministrador, En este atributo se guarda la dirección de correo del Administrador.
   */
  private String correoAdministrador;
  /**
   * Atributo userAdministrador, En este atributo se guarda el nombre de usuario del administrador.
   */
  private String userAdministrador;
  /**
   * Atributo ciAdministrador, En este atributo se guarda la cédula de identidad del Administrador.
   */
  private String ciAdministrador;
  /**
   * Atributo cuentaAdministrador, En este atributo se guarda el estado de cuenta del Administrador
   * (String Activo/Bloqueado).
   */
  private String cuentaAdministrador;
  /**
   * Atributo estadoCuentaAdministrador, En este atributo se guarda el estado de cuenta del Administrador
   * (Boolean true/false).
   */
  private Boolean estadoCuentaAdministrador;

  /**
   *Constructor del modelo Administrador.
   * @param pin, Pin del Administrador.
   * @param nombreAdministrador Nombre del Administrador.
   * @param apellidoAdministrador Apellido del Administrador.
   * @param correoAdministrador Dirección de correo del Administrador.
   * @param userAdministrador Nombre de usuario del Administrador.
   * @param ciAdministrador Cédula de identidad del Administrador.
   * @param cuentaAdministrador Estado de cuenta del Administrador (String Activo/Bloqueado).
   */
  public Administrador(String pin, String nombreAdministrador, String apellidoAdministrador,
                       String correoAdministrador, String userAdministrador, String ciAdministrador,
                       String cuentaAdministrador) {
    super(pin);
    this.nombreAdministrador = nombreAdministrador;
    this.apellidoAdministrador = apellidoAdministrador;
    this.correoAdministrador = correoAdministrador;
    this.userAdministrador = userAdministrador;
    this.ciAdministrador = ciAdministrador;
    this.cuentaAdministrador = cuentaAdministrador;
    if (cuentaAdministrador.equals("activo")) {
      this.estadoCuentaAdministrador = true;
    } else {
      this.estadoCuentaAdministrador = false;
    }
  }
  /**
   *Getter nombreAdministrador.
   * @return nombreAdministrador, valor del atributo nombreAdministrador del Modelo Administrador.
   */
  public String getNombreAdministrador() {
    return nombreAdministrador;
  }

  /**
   *Setter nombreAdministrador.
   * @param nombreAdministrador parámetro nombreAdministrador para modificar en el atributo del Modelo Administrador.
   */

  public void setNombreAdministrador(String nombreAdministrador) {
    this.nombreAdministrador = nombreAdministrador;
  }

  /**
   *Getter apellidoAdministrador.
   * @return apellidoAdministrador, valor del atributo apellidoAdministrador del Modelo Administrador.
   */
  public String getApellidoAdministrador() {
    return apellidoAdministrador;
  }

  /**
   *Setter apellidoAdministrador.
   * @param apellidoAdministrador parámetro apellidoAdministrador para modificar en el atributo del Modelo
   *                               Administrador.
   */
  public void setApellidoAdministrador(String apellidoAdministrador) {
    this.apellidoAdministrador = apellidoAdministrador;
  }

  /**
   *Getter correoAdministrador.
   * @return correoAdministrador, valor del atributo correoAdministrador del Modelo Administrador.
   */
  public String getCorreoAdministrador() {
    return correoAdministrador;
  }

  /**
   *Setter correoAdministrador
   * @param correoAdministrador parámetro correoAdministrador para modificar en el atributo del Modelo Administrador.
   */
  public void setCorreoAdministrador(String correoAdministrador) {
    this.correoAdministrador = correoAdministrador;
  }

  /**
   *Getter userAdministrador.
   * @return userAdministrador, valor del atributo userAdministrador del Modelo Administrador.
   */
  public String getUserAdministrador() {
    return userAdministrador;
  }

  /**
   *Setter userAdministrador.
   * @param userAdministrador parámetro userAdministrador para modificar en el atributo del Modelo Administrador.
   */
  public void setUserAdministrador(String userAdministrador) {
    this.userAdministrador = userAdministrador;
  }

  /**
   *Getter ciAdministrador.
   * @return ciAdministrador, valor del atributo ciAdministrador del Modelo Administrador.
   */
  public String getCiAdministrador() {
    return ciAdministrador;
  }

  /**
   *Setter ciAdministrador.
   * @param ciAdministrador parámetro ciAdministrador para modificar en el atributo del Modelo Administrador.
   */
  public void setCiAdministrador(String ciAdministrador) {
    this.ciAdministrador = ciAdministrador;
  }

  /**
   *Getter cuentaAdministrador.
   * @return cuentaAdministrador, valor del atributo cuentaAdministrador del Modelo Administrador.
   */
  public String getCuentaAdministrador() {
    return cuentaAdministrador;
  }

  /**
   *Setter cuentaAdministrador.
   * @param cuentaAdministrador parámetro cuentaAdministrador para modificar en el atributo del Modelo Administrador.
   */
  public void setCuentaAdministrador(String cuentaAdministrador) {
    this.cuentaAdministrador = cuentaAdministrador;
  }

  /**
   *Getter estadoCuentaAdministrador.
   * @return estadoCuentaAdministrador, valor del atributo estadoCuentaAdministrador del Modelo Administrador.
   */
  public Boolean getEstadoCuentaAdministrador() {
    return estadoCuentaAdministrador;
  }

  /**
   *Setter estadoCuentaAdministrador
   * @param estadoCuentaAdministrador parámetro estadoCuentaAdministrador para modificar en el atributo
   *                                   del Modelo Administrador.
   */
  public void setEstadoCuentaAdministrador(Boolean estadoCuentaAdministrador) {
    this.estadoCuentaAdministrador = estadoCuentaAdministrador;
  }
}