package ve.edu.ucab.lab.Controladores;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que gestiona la carga de datos de los movimientos realizados por el administrador.
 * Proporciona los datos para su visualización en una tabla.
 * @author Daniel Agudelo
 * @version 2.25.2
 *  */

public class RegistroAdministrador {

    /**
     * RUTA DEL ARCHIVO A SACAR LOS DATOS PARA IMPIRIMIRLOS EN LA TABLA ADECUADAMENTE
     */

    private static ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(
            "src/main/java/ve/edu/ucab/lab/baseDeDatos/HistorialDeMovimientosAdministrador","",
            "HistorialDeMovimientosAdministrador", "");

    public RegistroAdministrador() {
        rutasDeEjecutable.rutas();
    }

    private static final String RUTA_ARCHIVO=rutasDeEjecutable.getRutaBaseDatos();

    /**
     * Carga los datos desde el archivo y los inserta en el modelo de la tabla.
     *
     * @return DefaultTableModel con los datos cargados.
     */
    public DefaultTableModel cargarDatos() {
        // Crear las columnas del modelo
        String[] columnas = {"Fecha", "Usuario", "Tipo de Movimiento", "Persona", "Teléfono"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };

        // Leer los datos del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                // Asegurarse de que la línea tiene los datos esperados
                if (datos.length == 6) {
                    String fecha = datos[0];
                    String usuario = datos[1];
                    String tipoMovimiento = datos[2];
                    String persona = datos[3] + " " + datos[4]; // Concatenar nombre y apellido
                    String telefono = datos[5];

                    // Agregar fila al modelo
                    modeloTabla.addRow(new Object[]{fecha, usuario, tipoMovimiento, persona, telefono});
                } else {
                    System.err.println("Línea inválida en el archivo: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return modeloTabla;
    }
}
