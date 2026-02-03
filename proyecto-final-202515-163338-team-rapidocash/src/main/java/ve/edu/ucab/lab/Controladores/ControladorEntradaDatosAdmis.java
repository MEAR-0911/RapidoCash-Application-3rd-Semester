package ve.edu.ucab.lab.Controladores;

/**
 * En esta clase controladora de entrada de administradores el código se enfoca en usar las expresiones regulares
 * para poder confirmar que los datos ingresados como el username, el pin cumplan con una estructura necesaria para
 * el correcto funcionamiento de la aplicación, así nos evitamos el ingreso incorrecto de datos como entradas vacías,
 * carácteres incorrectos o demás de los requeridos.
 *
 * @author Antias Manuel
 * @version 2.16.1
 */

public class ControladorEntradaDatosAdmis {
    /**
     * Atributo userName, se guarda el nombre de usuario del administrador.
     */
    private String userName;
    /**
     * Atributo pin, se guarda el pin del administrador.
     */
    private String pin;
    /**
     * Atributo validar, se guarda el resultado de si lo ingresado por el administrador es correcto.
     */
    private boolean validar;
    /**
     * Atributo datoUser, se crea un objeto de la clase {@linkplain ControladorDatosPalabrasCorrectos}.
     */
    private ControladorDatosPalabrasCorrectos datoUser;
    /**
     * Atributo datopin, se crea un objeto de la clase {@linkplain ControladorDatosNumericosCorrectos}.
     */
    private ControladorDatosNumericosCorrectos datoPin;


    /**
     * Constructor de la clase EntradaDatosAdmis
     * @param userName userName del Administrador.
     * @param pin pin del administrador.
     */
    public ControladorEntradaDatosAdmis(String userName, String pin) {
        this.userName = userName;
        this.pin = pin;
        validar = false;
        datoUser = new ControladorDatosPalabrasCorrectos(userName, "Nombre de Usuario");
        datoPin=new ControladorDatosNumericosCorrectos(pin,"4","Pin");
    }

    /**
     * Este método se encarga de validar si los datos ingresados por el administrador cumplen con el formato
     * deseado para el correcto funcionamiento del programa. Se usan los métodos de las clases ya mencionadas
     * en los objetos creados.
     *
     * @return validar, indica si todos los datos ingresados son correctos (true/false).
     */
    public boolean validarEntrada() {
        datoUser.confirmarPalabrasCorrectas();
        datoPin.confirmarDatos();
        if (datoUser.errorCadena() && datoPin.errorNum()) {
            validar=true;
        } if (!datoUser.errorCadena()) {
            userName="";
        } if (!datoPin.errorNum()) {
            pin="";
        }
        return validar;
    }

    /**
     * Getter userName.
     * @return userName, valor del nombre de usuario del administrador.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter pin.
     * @return pin, valor del pin del administrador.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Getter validado.
     * @return validado, valor de si lo ingresado por el administrador es correcto.
     */
    public boolean validado() {
        return validar;
    }
}
