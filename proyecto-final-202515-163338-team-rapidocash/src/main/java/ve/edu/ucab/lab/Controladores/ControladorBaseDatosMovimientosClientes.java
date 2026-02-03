package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Transacciones;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * Este controlador se encarga de extraer el historial de movimientos de los usuarios para crear una lista de objetos
 * movimientos, con el objetivo de luego facilitar el historial propio del cliente.
 *
 * @version 2.28.3
 * @author Manuel Antias.
 */
public class ControladorBaseDatosMovimientosClientes {

    /**
     * Atributo transaccionesPorCliente, en esta variable que es del tipo Map, contiene una clave String y una lista
     * de objetos del tipo {@linkplain Transacciones}.
     */
    private Map<String, List<Transacciones>> transaccionesPorCliente = new HashMap<>();

    private String rutaBaseDatos;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Es el método encargado de extraer los datos de la base de Datos y a su vez, agruparlos por una clave telefónica
     * al Hashmap, teniendo así una lista Transacciones, con todos los objetos que compartan una misma clave única
     * (Número Telefónico), lo que permite hacer un mejor manejo de datos. Y cómo el Map es del tipo LinkedHashMap,
     * dichos objetos se anexan de manera ordenada.
     */
    public void cargarMovimientosClientes(){
        try {
            rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/MovimientosBancariosCliente",
                    "src/main/java/Imagenes/ArchivoCorrupto.png","MovimientosBancariosCliente",
                    "ArchivoCorrupto.png");
            rutasDeEjecutable.rutas();
            rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
            rutaImagenes=rutasDeEjecutable.getRutaImagenes();

            File archivoMov = new File(rutaBaseDatos);
            Scanner lecturaArchiv = new Scanner(archivoMov);
            while (lecturaArchiv.hasNextLine()) {
                String linea = lecturaArchiv.nextLine();
                String[] datos = linea.split(",");
                /**Acomodamos el formato de la fecha a día-mes-año.*/
                DateTimeFormatter fechaDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fechaLocalDate = LocalDate.parse(datos[5], fechaDate);
                Transacciones transacciones = new Transacciones(fechaLocalDate,datos[4],datos[1],datos[2],datos[0],0.0,
                        datos[3],datos[5],datos[6]);
                /**Se crea una lista Transacción con el los objetos del Map Transacciones con su key incluída*/
                List<Transacciones> transaccionesCliente = transaccionesPorCliente.get(datos[0]);
                if (transaccionesCliente == null) {
                    /**En dado caso de que la lista no existe, se crea*/
                    transaccionesCliente = new ArrayList<>();
                }
                /**Se añaden los objetos transacciones a la lista*/
                transaccionesCliente.add(transacciones);
                /**Se crea el HashMap con la lista de objetos y la key*/
                transaccionesPorCliente.put(datos[0],transaccionesCliente);
            }
            lecturaArchiv.close();
        } catch (FileNotFoundException e) {
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);

        }
    }

    /**
     * Getter transaccionesPorCliente.
     * @return transaccionesPorCliente, se retorna la lista Map que contiene la lista de objetos transacciones.
     */
    public Map<String, List<Transacciones>> getTransaccionesPorCliente() {
        return transaccionesPorCliente;
    }

}
