package ve.edu.ucab.lab.modelo;

/**
 *En esta clase se genera el modelo o plantilla del objeto "Usuario" el cual extiende a la clase {@linkplain Cliente}
 * y a la clase {@linkplain Administrador}.
 *
 * @author Antias Manuel
 * @version 2.0.0
 */

public class Usuario {
    /**
     * Atributo pin, en este atributo se guarda el pin de seguridad para el ingreso al sistema por parte de los usuarios.
     */
    private String pin;

    /**
     * Constructor de la clase Usuario.
     * @param pin, pin del usuario.
     */
    public Usuario(String pin) {
        this.pin = pin;
    }

    /**
     * Getter pin.
     * @return pin, valor del atributo pin del Modelo Usuario.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Setter pin.
     * @param pin parámetro pin para modificar en el atributo del Usuario.
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
}
