package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para mostrar el saldo actual de un cliente.
 * Este controlador se encarga de obtener el saldo actual del cliente y mostrarlo en una tabla.
 * El saldo se presenta con un formato de dos decimales y no es editable por el usuario.
 *
 * @version 2.24.2
 * @author Manuel Antias
 * @author Daniel Agudelo
 */
public class ControladorMostrarSaldoActual {

    /**
     * El cliente cuyo saldo se va a mostrar.
     */
    private Cliente cliente;

    /**
     * La tabla donde se mostrará el saldo actual del cliente.
     */
    private JTable tablaSaldo;

    /**
     * El saldo actual del cliente, formateado a dos decimales.
     */
    private String saldoActual;

    /**
     * Constructor del controlador que recibe un cliente y una tabla donde se mostrará el saldo.
     *
     * @param cliente El cliente cuyo saldo se va a mostrar.
     * @param tablaSaldo La tabla donde se mostrará el saldo del cliente.
     */
    public ControladorMostrarSaldoActual(Cliente cliente, JTable tablaSaldo) {
        this.cliente = cliente;
        this.tablaSaldo = tablaSaldo;
        saldoActual = String.format("%.2f", cliente.getCuenta().getSaldoCliente());
    }

    /**
     * Método para mostrar el saldo actual del cliente en la tabla.
     * <p>
     * Este método obtiene el saldo del cliente y lo presenta en la tabla con el formato adecuado.
     * El saldo es mostrado en una sola celda, y la tabla no es editable.
     * </p>
     */
    public void mostrarSaldo() {
        String nombreColumnas[] = {"Su saldo actual es de: "};

        // Modelo personalizado para evitar edición
        DefaultTableModel modelo = new DefaultTableModel(nombreColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        // Se agrega el saldo actual en la tabla
        Object[] movimiento = {saldoActual};
        modelo.addRow(movimiento);

        // Establece el modelo de la tabla con el saldo actual
        tablaSaldo.setModel(modelo);
    }
}
