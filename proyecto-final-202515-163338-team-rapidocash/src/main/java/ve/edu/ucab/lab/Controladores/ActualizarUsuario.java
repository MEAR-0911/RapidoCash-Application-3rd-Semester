package ve.edu.ucab.lab.Controladores;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import ve.edu.ucab.lab.Controladores.ControladorActualiazarClientesDatosCorrectos;
import ve.edu.ucab.lab.modelo.Administrador;
import ve.edu.ucab.lab.modelo.TransaccionesAdministradores;


/**
 * Controlador para gestionar la actualización de los datos de los clientes.
 * Permite buscar, validar y actualizar los datos de un cliente en la base de datos.
 *
 * @author Daniel Agudelo
 * @version 2.25.2
 */

public class ActualizarUsuario {

    /**
     * Ruta del archivo que simula la base de datos de los clientes.
     */

    private static ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(
            "src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes","","DatosClientes",
            "");

    /**
     * Fecha de la actualización realizada.
     * Se asigna con la fecha actual cuando se gestiona una actualización de usuario.
     */
    private LocalDate fecha;
    /**
     * Tipo de movimiento que se registra.
     * En este caso, indica que la operación realizada es una actualización de datos de un usuario.
     */
    private String movimiento;
    /**
     * Objeto administrador que ejecuta la acción de actualización.
     * Se utiliza para registrar la actividad del administrador al realizar la actualización de datos.
     */
    private Administrador administrador;

    public ActualizarUsuario() {
        rutasDeEjecutable.rutas();
    }

    private static final String RUTA_ARCHIVO=rutasDeEjecutable.getRutaBaseDatos();


    /**
     * Busca un cliente por su número de teléfono y devuelve sus datos.
     * @param telefono Número de teléfono del cliente.
     * @return Arreglo con los datos del cliente si se encuentra, null si no.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static String[] buscarCliente(String telefono) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosCliente = linea.split(",");
                if (datosCliente[4].equals(telefono)) { // Compara el teléfono
                    return datosCliente;
                }
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un cliente en el archivo.
     * @param pin Nuevo PIN del cliente.
     * @param nombre Nuevo nombre.
     * @param apellido Nuevo apellido.
     * @param cedula Nueva cédula.
     * @param telefono Nuevo teléfono.
     * @param correo Nuevo correo.
     * @param tipoCuenta Nuevo tipo de cuenta.
     * @param estadoCuenta Nuevo estado de la cuenta.
     * @param saldo Nuevo saldo.
     * @throws IOException Si ocurre un error al actualizar el archivo.
     */
    public void actualizarCliente(String pin, String nombre, String apellido, String cedula, String telefono,
                                  String correo, String tipoCuenta, String estadoCuenta, double saldo) throws IOException {
        List<String> lineas = new ArrayList<>();
        boolean clienteEncontrado = false;

        // Lee todas las líneas del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }

        // Busca y actualiza al cliente
        for (int i = 0; i < lineas.size(); i++) {
            String[] datosCliente = lineas.get(i).split(",");
            if (datosCliente[4].equals(telefono)) { // Compara el teléfono
                // Formatear el saldo con el punto como separador decimal
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
                DecimalFormat formatoSaldo = new DecimalFormat("0.00", simbolos);
                String saldoFormateado = formatoSaldo.format(saldo); // Formateamos el saldo

                String clienteActualizado = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        pin, nombre, apellido, cedula, telefono, correo, tipoCuenta, estadoCuenta, saldoFormateado);
                lineas.set(i, clienteActualizado);
                clienteEncontrado = true;
                break;
            }
        }

        // Guarda los cambios en el archivo
        if (clienteEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String nuevaLinea : lineas) {
                    bw.write(nuevaLinea);
                    bw.newLine();
                }
            }
        } else {
            throw new IOException("Cliente no encontrado para actualizar");
        }
    }

    /**
     * Gestiona la actualización de un usuario desde la interfaz gráfica.
     * Valida los datos ingresados, actualiza la base de datos y registra la actividad del administrador.
     *
     * @param nombreUsuario Campo de texto del nombre del usuario.
     * @param apellidoUsuario Campo de texto del apellido del usuario.
     * @param telefonoUsuario Campo de texto del teléfono del usuario.
     * @param cedulaUsuario Campo de texto de la cédula del usuario.
     * @param pinUsuario Campo de texto del PIN del usuario.
     * @param correoUsuario Campo de texto del correo del usuario.
     * @param tipoDeCuenta Campo de texto del tipo de cuenta.
     * @param estadoDeCuenta Campo de texto del estado de la cuenta.
     * @param administrador Objeto administrador que realiza la actualización.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean gestionarActualizacionUsuario(
            JTextField nombreUsuario, JTextField apellidoUsuario, JTextField telefonoUsuario,
            JTextField cedulaUsuario, JTextField pinUsuario, JTextField correoUsuario,
            JTextField tipoDeCuenta, JTextField estadoDeCuenta, Administrador administrador) {

        try {
            // Crear instancia de Controlador para validar datos
            ControladorActualiazarClientesDatosCorrectos datosCorrectos = new ControladorActualiazarClientesDatosCorrectos(
                    nombreUsuario.getText(), apellidoUsuario.getText(), telefonoUsuario.getText(),
                    cedulaUsuario.getText(), pinUsuario.getText(), correoUsuario.getText()
            );

            datosCorrectos.validarDatos();

            // Actualizar los campos con los datos corregidos
            nombreUsuario.setText(datosCorrectos.getNombre());
            apellidoUsuario.setText(datosCorrectos.getApellido());
            telefonoUsuario.setText(datosCorrectos.getTelf());
            cedulaUsuario.setText(datosCorrectos.getCi());
            pinUsuario.setText(datosCorrectos.getPin());
            correoUsuario.setText(datosCorrectos.getEmail());

            if (!datosCorrectos.validado()) {
                JOptionPane.showMessageDialog(null, "Los datos ingresados no son válidos. Por favor, corríjalos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Obtener datos corregidos
            String nuevoNombre = nombreUsuario.getText().trim();
            String nuevoApellido = apellidoUsuario.getText().trim();
            String nuevoTelefono = telefonoUsuario.getText().trim();
            String nuevaCedula = cedulaUsuario.getText().trim();
            String nuevoPin = pinUsuario.getText().trim();
            String nuevoCorreo = correoUsuario.getText().trim();
            String nuevoTipoCuenta = tipoDeCuenta.getText().trim();
            String nuevoEstadoCuenta = estadoDeCuenta.getText().trim();
            this.fecha=LocalDate.now();
            this.movimiento="Actualizar";
            this.administrador=administrador;

            // Validar que los campos obligatorios no estén vacíos
            if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoTelefono.isEmpty() ||
                    nuevaCedula.isEmpty() || nuevoPin.isEmpty() || nuevoCorreo.isEmpty() ||
                    nuevoTipoCuenta.isEmpty() || nuevoEstadoCuenta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Buscar cliente
            String[] datosCliente = buscarCliente(nuevoTelefono);
            if (datosCliente != null) {
                // Mantener saldo y actualizar datos
                double saldo = Double.parseDouble(datosCliente[8]);
                actualizarCliente(nuevoPin, nuevoNombre, nuevoApellido, nuevaCedula,
                        nuevoTelefono, nuevoCorreo, nuevoTipoCuenta,
                        nuevoEstadoCuenta, saldo);


                JOptionPane.showMessageDialog(null, "Datos del usuario actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                /**
                 * Aquí está el código para poder anexar al registro de administrador
                 */
                TransaccionesAdministradores transaccionAdmi=new TransaccionesAdministradores(fecha,administrador.getUserAdministrador(),
                        movimiento,telefonoUsuario.getText(),"");
                ControladorLlenarRegistroActividarAdministrador movAdministrador=new ControladorLlenarRegistroActividarAdministrador(
                        transaccionAdmi);
                movAdministrador.subirMovimientosAdmis();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el cliente a actualizar.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /**
     * Realiza la búsqueda de un cliente por teléfono y actualiza los campos de texto proporcionados.
     * @param telefono Número de teléfono a buscar.
     * @param campos Un arreglo de JTextFields en el orden esperado: nombre, apellido, teléfono, cédula, PIN, correo, tipoCuenta, estadoCuenta.
     */
    public static void buscarYActualizarCampos(String telefono, JTextField[] campos) {
        try {
            // Buscar cliente por teléfono
            String[] datosCliente = buscarCliente(telefono);

            if (datosCliente != null) {
                // Llena los campos de texto con los datos del cliente
                for (int i = 0; i < campos.length && i < datosCliente.length; i++) {
                    campos[i].setText(datosCliente[i]);
                }
                JOptionPane.showMessageDialog(null, "Cliente encontrado y datos cargados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
