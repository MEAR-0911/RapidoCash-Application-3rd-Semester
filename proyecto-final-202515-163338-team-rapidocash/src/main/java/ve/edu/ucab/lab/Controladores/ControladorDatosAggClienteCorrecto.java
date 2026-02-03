package ve.edu.ucab.lab.Controladores;

/**
 * En esta clase controladora para el ingreso correcto de datos a la hora de agregar a un cliente, se busca asegurar
 * que los datos ingresados por el administrador cumpla con la estructura coherente y necesaria para que se pueda
 * agregar un cliente correctamente. Esta Clase hace uso de las clases: {@linkplain ControladorDatosNumericosCorrectos},
 * {@linkplain ControladorDatosPalabrasCorrectos},{@linkplain ControladorCorreoCorrecto}.
 *
 * @author Antias Manuel
 * @version 2.16.1
 */

public class ControladorDatosAggClienteCorrecto {
    /**
     * Atributo nombreCliente, Se crea un objeto de la clase {@linkplain ControladorDatosPalabrasCorrectos}.
     */
    private ControladorDatosPalabrasCorrectos nombreCliente;
    /**
     * Atributo apellidoCliente, Se crea un objeto de la clase {@linkplain ControladorDatosPalabrasCorrectos}.
     */
    private ControladorDatosPalabrasCorrectos apellidoCliente;
    /**
     * Atributo telfCliente, Se crea un objeto de la clase {@linkplain ControladorDatosNumericosCorrectos}.
     */
    private ControladorDatosNumericosCorrectos telfCliente;
    /**
     * Atributo ciCliente, Se crea un objeto de la clase {@linkplain ControladorDatosNumericosCorrectos}.
     */
    private ControladorDatosNumericosCorrectos ciCliente;
    /**
     * Atributo pinCliente, Se crea un objeto de la clase {@linkplain ControladorDatosNumericosCorrectos}.
     */
    private ControladorDatosNumericosCorrectos pinCliente;
    /**
     * Atributo emailCliente, Se crea un objeto de la clase {@linkplain ControladorCorreoCorrecto}.
     */
    private ControladorCorreoCorrecto emailCliente;
    /**
     * Atributo nombre, se guarda el nombre del cliente a agregar.
     */
    private String nombre;
    /**
     * Atributo apellido, se guarda el apellido del cliente a agregar.
     */
    private String apellido;
    /**
     * Atributo telf, se guarda el teléfono del cliente a agregar.
     */
    private String telf;
    /**
     * Atributo ci, se guarda la cédula de identidad del cliente a agregar.
     */
    private String ci;
    /**
     * Atributo pin, se guarda el pin del cliente a agregar.
     */
    private String pin;
    /**
     * Atributo email, se guarda el email del cliente a agregar.
     */
    private String email;
    /**
     * Atributo validad, se guarda el resultado (true/false) de si lo ingresado por el usuario es correcto.
     */
    private boolean validado;

    /**
     * Constructor de la clase ControladorDatosAggClienteCorrecto.
     * @param nombre nombre del cliente a agregar.
     * @param apellido apellido del cliente a agregar.
     * @param telf teléfono del cliente a agregar.
     * @param ci cédula de identidad del cliente a agregar.
     * @param pin pin del cliente a agregar.
     * @param email Correo electrónico del cliente a agregar.
     */
    public ControladorDatosAggClienteCorrecto(String nombre, String apellido, String telf, String ci, String pin,
                                              String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telf = telf;
        this.ci = ci;
        this.pin = pin;
        this.email = email;
        validado = false;
        nombreCliente=new ControladorDatosPalabrasCorrectos(nombre,"Nombre");
        apellidoCliente=new ControladorDatosPalabrasCorrectos(apellido,"Apellido");
        telfCliente=new ControladorDatosNumericosCorrectos(telf,"11","Telefónico");
        ciCliente=new ControladorDatosNumericosCorrectos(ci,"8","Cédula de Identidad");
        pinCliente=new ControladorDatosNumericosCorrectos(pin,"4","Pin");
        emailCliente=new ControladorCorreoCorrecto(email);
    }

    /**
     * Este método se encarga de validar las entradas del administrador, haciendo uso de los métodos de las clases
     * ya mencionadas con anterioridad. A su vez elimina aquellas entradas que estén erróneas para así poder crear
     * una interfaz más dinámica y atractiva para el usuario.
     *
     * @return validado, dato que informa si lo ingresado por el usuario es correcto o no.
     */
    public boolean validarDatos(){
        nombreCliente.confirmarPalabrasCorrectas();
        apellidoCliente.confirmarPalabrasCorrectas();
        telfCliente.confirmarDatos();
        ciCliente.confirmarDatos();
        pinCliente.confirmarDatos();
        emailCliente.confirmarCorreo();
        if (nombreCliente.errorCadena() && apellidoCliente.errorCadena() && telfCliente.errorNum() && ciCliente.errorNum() &&
                pinCliente.errorNum() && emailCliente.correoCorrecto()){
            validado=true;
        }
        if (!nombreCliente.errorCadena()){
            nombre="";
        }
        if (!apellidoCliente.errorCadena()){
            apellido="";
        }
        if (!telfCliente.errorNum()){
            telf="";
        }
        if (!ciCliente.errorNum()){
            ci="";
        }
        if (!pinCliente.errorNum()){
            pin="";
        }
        if (!emailCliente.correoCorrecto()){
            email="";
        }
        return validado;
    }

    /**
     * Getter nombre.
     * @return nombre, valor del nombre del cliente a agregar.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter apellido.
     * @return apellido, valor del apellido del cliente a agregar.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Getter telf.
     * @return telf, valor del teléfono del cliente a agregar.
     */
    public String getTelf() {
        return telf;
    }

    /**
     * Getter ci.
     * @return ci, valor del Cédula de identidad del cliente a agregar.
     */
    public String getCi() {
        return ci;
    }

    /**
     * Getter pin.
     * @return pin, valor del pin del cliente a agregar.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Getter email.
     * @return email, valor del email del cliente a agregar.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter validado.
     * @return validado, valor de si lo ingresado por el administrador ha estado correcto.
     */
    public boolean validado() {
        return validado;
    }
}
