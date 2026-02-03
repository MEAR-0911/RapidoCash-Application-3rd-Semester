package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Controlador para la eliminación de usuarios (clientes).
 * Esta clase proporciona funcionalidades para buscar un cliente por su número de teléfono,
 * mostrar sus datos en una tabla, eliminarlo de la base de datos y actualizar los registros
 * en el archivo de base de datos correspondiente.
 * @version 2.25.2
 * @author Daniel Agudelo
 */

public class EliminarUsuario {
    /**
     * Instancia del controlador de base de datos que maneja los clientes.
     * Esta variable es responsable de acceder y manipular la lista de clientes
     * almacenados en la base de datos.
     */
    private static ControladorBaseDatosClientes controladorClientes;

    private String rutaBaseDatos;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;


    /**
     * Constructor de la clase. Inicializa el controlador de clientes.
     */

    public EliminarUsuario() {
        // Inicializamos el controlador que contiene la lista de clientes
        this.controladorClientes = ControladorBaseDatosClientes.getInstanciaCliente();
    }

    /**
     * Busca un cliente por su número de teléfono y actualiza la tabla proporcionada con los datos.
     * @param telefono El número de teléfono del cliente.
     * @param tabla La tabla que se actualizará con los datos del cliente.
     * @return true si el cliente fue encontrado y la tabla se actualizó, false si no.
     */
    public static boolean buscarClienteYActualizarTabla(String telefono, JTable tabla) {
        // Asegurarse de que los datos estén actualizados
        controladorClientes.cargarClientes(); // Recargar clientes desde la base de datos

        // Buscar el cliente por teléfono
        Cliente cliente = buscarClientePorTelefono(telefono);

        if (cliente != null) {
            // Obtenemos el modelo existente de la tabla
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();

            // Limpiamos las filas actuales del modelo
            model.setRowCount(0);

            // Agregamos la información del cliente al modelo
            model.addRow(new Object[] {
                    cliente.getNombreCliente(),
                    cliente.getApellidoCliente(),
                    cliente.getCiCliente(),
                    cliente.getTelefonoCliente(),
                    cliente.getCorreoCliente(),
                    cliente.getCuenta().getTipoCuentaCliente()
            });

            tabla.repaint();
            tabla.revalidate();

            // Mostramos un mensaje de éxito
            JOptionPane.showMessageDialog(null, "Cliente encontrado. Se muestran los datos en la tabla.");
            return true;
        } else {
            // Mostramos un mensaje de error si no se encontró el cliente
            JOptionPane.showMessageDialog(null, "Cliente no encontrado. Por favor verifique el número de teléfono.");
            return false;
        }
    }



    /**
     * Busca un cliente por su número de teléfono.
     * @param telefono El número de teléfono del cliente.
     * @return El cliente si se encuentra, null si no.
     */
    public static Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente cliente : controladorClientes.getClientes().values()) {
            if (cliente.getTelefonoCliente().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Elimina un cliente por su teléfono.
     * @param telefono El número de teléfono del cliente a eliminar.
     * @return true si el cliente fue eliminado, false si no se encontró.
     */
    public boolean eliminarClientePorTelefono(String telefono) {
        Cliente clienteAEliminar = buscarClientePorTelefono(telefono);

        if (clienteAEliminar != null) {
            // Eliminamos el cliente del mapa de clientes
            controladorClientes.getClientes().remove(clienteAEliminar.getTelefonoCliente());

            // Ahora debemos actualizar el archivo de base de datos
            actualizarBaseDeDatos();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un cliente con ese número de teléfono.");
            return false;
        }
    }

    /**
     * Actualiza el archivo de base de datos con los datos actuales de los clientes.
     * Escribe los datos de todos los clientes en el archivo de base de datos.
     */

    private void actualizarBaseDeDatos() {
        try {
            rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes",
                    "","DatosClientes","");
            rutasDeEjecutable.rutas();
            rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();

            // Abrimos el archivo de datos de clientes
            File archivo = new File(rutaBaseDatos);
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

            // Escribimos los datos de todos los clientes actuales en el archivo
            for (Cliente cliente : controladorClientes.getClientes().values()) {
                String linea = cliente.getPin() + ","
                        + cliente.getNombreCliente() + ","
                        + cliente.getApellidoCliente() + ","
                        + cliente.getCiCliente() + ","
                        + cliente.getTelefonoCliente() + ","
                        + cliente.getCorreoCliente() + ","
                        + cliente.getCuenta().getTipoCuentaCliente() + ","
                        + (cliente.getCuenta().getEstadoCuentaCliente().equals("activo") ? "activo" : "bloqueado") + ","
                        + cliente.getCuenta().getSaldoCliente();
                writer.write(linea);
                writer.newLine();
            }

            // Cerramos el archivo después de escribir los datos
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos.");
        }
    }
}
