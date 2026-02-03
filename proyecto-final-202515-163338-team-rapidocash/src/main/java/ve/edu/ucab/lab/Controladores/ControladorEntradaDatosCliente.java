package ve.edu.ucab.lab.Controladores;

/**
 * En esta clase controladora de entrada de cliente el código se enfoca en usar las expresiones regulares
 * para poder confirmar que los datos ingresados como el teléfono y/o el pin cumplan con una estructura necesaria para
 * el correcto funcionamiento de la aplicación, así nos evitamos el ingreso incorrecto de datos como entradas vacías,
 * carácteres incorrectos o demás de los requeridos.
 *
 * @author Antias Manuel
 * @version 2.16.1
 */

public class ControladorEntradaDatosCliente {
    /**
     * Atributo numeroTelf, se guarda el número de teléfono del cliente.
     */
    private String numeroTelf;
    /**
     * Atributo pin, se guarda el pin del cliente.
     */
    private String pin;
    /**
     * Atributo validar, se encarga de guardar si lo ingresado por el cliente es correcto.
     */
    private boolean validar;
    /**
     * Atributo datoTelf, se crea un objeto de la clase {@linkplain ControladorDatosNumericosCorrectos}
     */
    private ControladorDatosNumericosCorrectos datoTelf;
    /**
     * Atributo datoTelf, se crea un objeto de la clase {@linkplain ControladorDatosNumericosCorrectos}
     */
    private ControladorDatosNumericosCorrectos datoPin;

    /**
     * Constructor de la clase ControladorEntradaDatosCLiente.
     * @param numeroTelf se guarda el número de teléfono del cliente.
     * @param pin se guarda el pin del cliente.
     */
    public ControladorEntradaDatosCliente(String numeroTelf, String pin) {
        this.numeroTelf = numeroTelf;
        this.pin = pin;
        validar = false;
        datoTelf=new ControladorDatosNumericosCorrectos(numeroTelf,"11","Telefónico");
        datoPin=new ControladorDatosNumericosCorrectos(pin,"4", "Contraseña");
    }

    /**
     * En este método se verifica si las entradas realizadas por el cliente son correctas o no para su efectivo
     * ingreso al programa.
     *
     * @return validar, contiene el valor de si lo ingresado por el cliente es correcto.
     */
    public boolean validarEntrada(){
        datoTelf.confirmarDatos();
        datoPin.confirmarDatos();
        if ((datoTelf.errorNum()) && (datoPin.errorNum())){
            validar=true;
        }
        if (!datoTelf.errorNum()){
            numeroTelf="";
        }
        if (!datoPin.errorNum()){
            pin="";
        }
        return validar;
    }

    /**
     * Getter validado.
     * @return validado, valor de si lo ingresado por el cliente es correcto o no (true/false).
     */
    public boolean validado() {
        return validar;
    }

    /**
     * Getter numeroTelf.
     * @return numeroTelf, valor del número de teléfono ingresado por el cliente.
     */
    public String getNumeroTelf() {
        return numeroTelf;
    }

    /**
     * Getter pin.
     * @return pin, valor del pin ingresado por el cliente.
     */
    public String getPin() {
        return pin;
    }
}