package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;
import ve.edu.ucab.lab.modelo.Transacciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para mostrar los movimientos de los clientes.
 * Este controlador se encarga de gestionar las transacciones realizadas por un cliente y mostrarlas
 * en una tabla. El controlador obtiene las transacciones del cliente desde la base de datos y las presenta
 * de forma detallada, mostrando la fecha, tipo de transacción y el saldo de operación (si aplica).
 *
 * @version 2.24.2
 * @author Manuel Antias
 * @author Daniel Agudelo
 */
public class ControladorMostrarMovimientosClientes {

    /**
     * Instancia del controlador para obtener las transacciones desde la base de datos.
     */

    /**private ControladorBaseDatosMovimientosClientes controladorTransaccion = ControladorBaseDatosMovimientosClientes.getInstanciaMovimiento();*/

    private ControladorBaseDatosMovimientosClientes controladorTransaccion= new ControladorBaseDatosMovimientosClientes();
    /**
     * El cliente cuyas transacciones se mostrarán.
     */
    private Cliente cliente;

    /**
     * Mapa que asocia el teléfono del cliente a una lista de transacciones.
     */
    private Map<String, List<Transacciones>> transaccion = new LinkedHashMap<>();

    /**
     * Instancia de la transacción.
     */
    private Transacciones transacc;

    /**
     * La tabla donde se mostrarán los movimientos del cliente.
     */
    private JTable tabla;

    /**
     * Constructor del controlador que recibe un cliente y una tabla donde se mostrarán los movimientos.
     *
     * @param cliente El cliente cuyas transacciones se van a mostrar.
     * @param tabla La tabla donde se mostrarán las transacciones del cliente.
     */
    public ControladorMostrarMovimientosClientes(Cliente cliente, JTable tabla) {
        this.cliente = cliente;
        this.tabla = tabla;
        controladorTransaccion.cargarMovimientosClientes();
        transaccion = controladorTransaccion.getTransaccionesPorCliente();
    }

    /**
     * Método para mostrar los movimientos del cliente en la tabla.
     *
     * Este método obtiene las transacciones del cliente a través de su teléfono y las carga en la tabla.
     * Si no se encuentran movimientos, se muestra un mensaje de error.
     */
    public void mostrarMovimientos() {
        //controladorTransaccion.cargarMovimientosClientes();
        List<Transacciones> transaccionesMov = transaccion.get(cliente.getTelefonoCliente());

        // Verifica si el cliente tiene transacciones
        if (transaccionesMov != null) {
            String nombreColumnas[] = {"Fecha de Transacción", "Transacción", "Saldo de operación"};

            // Modelo personalizado para evitar edición
            DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Todas las celdas no son editables
                }
            };

            // trabaja sobre las transacciones y agrega cada una al modelo de la tabla
            for (Transacciones transaccion : transaccionesMov) {
                if (transaccion.getMovimiento().equals("consulta de saldo")|transaccion.getMovimiento().equals("Cambiar Pin")) {
                    // Para consulta y cambiar pin solo mostramos la fecha y el tipo de movimiento
                    Object[] movimiento = {transaccion.getFechita(), transaccion.getMovimiento()};
                    modeloTabla.addRow(movimiento);
                } else {
                    // Para otros movimientos también mostramos el monto de la operación
                    Object[] movimiento = {transaccion.getFechita(), transaccion.getMovimiento(), transaccion.getMontoPrueba()};
                    modeloTabla.addRow(movimiento);
                }
            }

            // Establece el modelo de la tabla con los movimientos
            tabla.setModel(modeloTabla);
            //transaccion.clear();
        }
    }
}
