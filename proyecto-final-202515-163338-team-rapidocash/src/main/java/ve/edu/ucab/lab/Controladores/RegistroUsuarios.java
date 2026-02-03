package ve.edu.ucab.lab.Controladores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Clase que permite manejar el registro de usuarios, incluyendo la carga de datos
 * desde un archivo de base de datos y su visualización en una tabla.
 *
 * @author Daniel Agudelo
 * @version 2.25.3
 */
public class RegistroUsuarios {

    /**
     * Tabla donde se mostrarán los registros de los usuarios.
     * Se utilizará este componente de la interfaz gráfica para visualizar los movimientos bancarios.
     */
    private JTable tabla;

    private static ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(
            "src/main/java/ve/edu/ucab/lab/baseDeDatos/MovimientosBancariosCliente","",
            "MovimientosBancariosCliente", "");

    public RegistroUsuarios() {
        rutasDeEjecutable.rutas();
    }
    /**
     * Ruta del archivo que contiene los datos de los usuarios.
     */
    private static final String RUTA_ARCHIVO=rutasDeEjecutable.getRutaBaseDatos();

    /**
     * Constructor de la clase RegistroUsuarios.
     *
     * @param tabla JTable donde se mostrarán los datos cargados.
     */
    public RegistroUsuarios(JTable tabla) {
        this.tabla = tabla;
        configurarTabla();
    }

    /**
     * Configura las columnas del JTable asociado con un modelo de tabla no editable.
     */
    private void configurarTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas son no editables
            }
        };

        modeloTabla.setColumnIdentifiers(new Object[]{
                "Teléfono", "Nombre", "Apellido", "Tipo de Cuenta",
                "Tipo de Movimiento", "Fecha", "Saldo"
        });
        tabla.setModel(modeloTabla);
    }

    /**
     * Carga los datos desde el archivo de base de datos al JTable.
     * Si el tipo de movimiento es "Consulta de Saldo" o "Cambiar PIN", la columna "Saldo" se deja vacía.
     */
    public void cargarDatos() {
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        try (BufferedReader br = Files.newBufferedReader(Paths.get(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                if (valores.length == 7) { // Validar que la línea tenga exactamente 7 valores
                    String tipoMovimiento = valores[4];
                    if ("Consulta de Saldo".equalsIgnoreCase(tipoMovimiento) || "Cambiar PIN".equalsIgnoreCase(tipoMovimiento)) {
                        valores[6] = ""; // Dejar vacía la columna "Saldo"
                    }
                    modeloTabla.addRow(valores);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al cargar los datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
