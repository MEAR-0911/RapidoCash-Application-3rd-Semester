package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;
import ve.edu.ucab.lab.modelo.Transacciones;
import ve.edu.ucab.lab.modelo.TransaccionesAdministradores;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Este controlador se encarga de llenar la base de datos de los movimientos bancarios de los clientes, para luego
 * ser usado y mostrarlo en su respectiva clase.
 *
 * @version 2.28.3
 * @author Manuel Antias.
 */

public class ControladorLlenarBaseDatosMovimientosBancarios {
    /**
     * Este atributo guarda un objetotransacción del tipo {@linkplain Transacciones} con el que podremos acceder a sus
     * atributos para comparaciones y toma de datos.
     */
    private Transacciones transaccion;
    /**
     * Este atributo guarda una lista del tipo Map con una compilación de objetos del tipo {@linkplain Transacciones}.
     */
    private Map<String, Transacciones> movimientos = new LinkedHashMap<>();
    /**
     * Este atribuo de tipo LocalDate guarda una fecha sacada del atributo del objeto transacciones.
     */
    private LocalDate fecha;
    /**
     * Este atributo guarda la misma fecha localDate pero en String y con un formato día/mes/año.
     */
    private String fechaString;

    private String rutaBaseDatos;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     *
     * @param transaccion objeto del tipo Transacción que se recibe por parámetro.
     */
    public ControladorLlenarBaseDatosMovimientosBancarios(Transacciones transaccion) {
        this.transaccion = transaccion;
        this.fecha=transaccion.getFechaTransaccion();
        fechaString=fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        movimientos.put(transaccion.getNumeroTelfCliente(),transaccion);

    }

    /**
     * Este método se encarga de sobreescribir cargando todos los movimientos que ha realizado el cliente durante
     * la ejecución completa del programa.
     */
    public void subirMovimientos(){
        try{

            rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/MovimientosBancariosCliente",
                    "src/main/java/Imagenes/ArchivoCorrupto.png","MovimientosBancariosCliente",
                    "ArchivoCorrupto.png");
            rutasDeEjecutable.rutas();
            rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
            rutaImagenes=rutasDeEjecutable.getRutaImagenes();

            FileWriter archivoMovimientos = new FileWriter(rutaBaseDatos,true);
            StringBuilder linea = new StringBuilder();
            String dato;
            for (Transacciones movimiento : movimientos.values()) {
                dato=movimiento.getNumeroTelfCliente()+","+movimiento.getNombreCliente()+","+
                        movimiento.getApellidoCliente()+","+movimiento.getTipoCuenta()+","+movimiento.getMovimiento()+
                        ","+fechaString+","+movimiento.getMonto();
                linea.append(dato+"\r\n");
                archivoMovimientos.write(linea.toString());
                linea.setLength(0);
            }
            archivoMovimientos.close();

        }catch (IOException e) {
            ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
        }

    }

}
