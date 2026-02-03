package ve.edu.ucab.lab.Controladores;

public class ControladorActualiazarClientesDatosCorrectos extends ControladorDatosAggClienteCorrecto{

    /**
     * Constructor de la clase ControladorActualizarClientesDatosCorrectos.
     * @param nombre nombre del cliente ingresado por el admi.
     * @param apellido apellido del cliente ingresado por el admi.
     * @param telf teléfono del cliente ingresado por el admi.
     * @param ci cédula de identidad del cliente ingresado por el admi.
     * @param pin pin del cliente ingresado por el admi.
     * @param email email del cliente ingresado por el admi.
     */
    public ControladorActualiazarClientesDatosCorrectos(String nombre, String apellido, String telf, String ci, String pin, String email) {
        super(nombre, apellido, telf, ci, pin, email);
    }

    @Override
    public boolean validarDatos() {
        return super.validarDatos();
    }

    @Override
    public boolean validado() {
        return super.validado();
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getApellido() {
        return super.getApellido();
    }

    @Override
    public String getTelf() {
        return super.getTelf();
    }

    @Override
    public String getCi() {
        return super.getCi();
    }

    @Override
    public String getPin() {
        return super.getPin();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }
}
