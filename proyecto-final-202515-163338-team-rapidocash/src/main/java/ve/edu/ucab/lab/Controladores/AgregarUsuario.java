package ve.edu.ucab.lab.Controladores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Clase encargada de agregar un nuevo cliente a un archivo de base de datos.
 * Proporciona un método para manejar la lógica completa desde la validación
 * hasta la escritura en el archivo.
 *
 * @author Daniel Agudelo
 * @version 2.25.2
 */
public class AgregarUsuario {

    /**
     * Ruta del archivo que simula la base de datos de los clientes.
     */

    private static ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(
            "src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes","","DatosClientes",
            "");

    public AgregarUsuario() {
        rutasDeEjecutable.rutas();
    }

    private static final String RUTA_ARCHIVO=rutasDeEjecutable.getRutaBaseDatos();

    /**
     * Maneja la recolección de datos directamente desde los componentes del formulario.
     *
     * @param datoNombre Campo de texto para el nombre.
     * @param datoApellido Campo de texto para el apellido.
     * @param datoCedula Campo de texto para la cédula.
     * @param datoTelefono Campo de texto para el teléfono.
     * @param datoCorreo Campo de texto para el correo.
     * @param datoPin Campo de texto para el PIN.
     * @param opcionCorriente Checkbox para cuenta corriente.
     * @param opcionAhorro Checkbox para cuenta de ahorro.
     * @throws Exception Si hay errores en validaciones o al escribir en el archivo.
     */
    public void manejarAgregarUsuario(
            JTextField datoNombre, JTextField datoApellido, JTextField datoCedula,
            JTextField datoTelefono, JTextField datoCorreo, JPasswordField datoPin,
            JCheckBox opcionCorriente, JRadioButton opcionAhorro
    ) throws Exception {
        // Recolectar y validar los datos
        String nombre = datoNombre.getText().trim();
        String apellido = datoApellido.getText().trim();
        String cedula = datoCedula.getText().trim();
        String telefono = datoTelefono.getText().trim();
        String correo = datoCorreo.getText().trim();
        String pin = new String(datoPin.getPassword()).trim();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || correo.isEmpty() || pin.isEmpty()) {
            throw new IllegalArgumentException("Por favor, llene todos los campos.");
        }

        // Determinar el tipo de cuenta
        String tipoCuenta;
        if (opcionCorriente.isSelected()) {
            tipoCuenta = "corriente";
        } else if (opcionAhorro.isSelected()) {
            tipoCuenta = "ahorro";
        } else {
            throw new IllegalArgumentException("Debe seleccionar el tipo de cuenta.");
        }

        // Obtener el saldo inicial
        double saldo;
        try {
            saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo inicial de la cuenta:"));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El saldo debe ser un número válido.");
        }

        // Estado de la cuenta por defecto
        String estadoCuenta = "activo";

        // Escribir los datos en el archivo
        escribirClienteEnArchivo(pin, nombre, apellido, cedula, telefono, correo, tipoCuenta, estadoCuenta, saldo);
    }

    /**
     * Escribe los datos del cliente en el archivo.
     *
     * @param pin PIN del cliente
     * @param nombre Nombre del cliente
     * @param apellido Apellido del cliente
     * @param cedula Cédula del cliente
     * @param telefono Teléfono del cliente
     * @param correo Correo del cliente
     * @param tipoCuenta Tipo de cuenta
     * @param estadoCuenta Estado de la cuenta
     * @param saldo Saldo inicial
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */


    private void escribirClienteEnArchivo(String pin, String nombre, String apellido, String cedula,
                                          String telefono, String correo, String tipoCuenta,
                                          String estadoCuenta, double saldo) throws IOException {
        // Crear DecimalFormatSymbols con el locale de Estados Unidos para asegurar que use punto (.)
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatoSaldo = new DecimalFormat("0.00", simbolos); // Dos decimales y punto como separador
        String saldoFormateado = formatoSaldo.format(saldo); // Formateamos el saldo

        String cliente = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                pin, nombre, apellido, cedula, telefono, correo, tipoCuenta, estadoCuenta, saldoFormateado);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            bw.write(cliente);
            bw.newLine();
        }
    }


}
