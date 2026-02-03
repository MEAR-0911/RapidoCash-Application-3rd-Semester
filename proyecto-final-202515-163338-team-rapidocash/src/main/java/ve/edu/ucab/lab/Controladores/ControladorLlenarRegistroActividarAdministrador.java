package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;
import ve.edu.ucab.lab.modelo.TransaccionesAdministradores;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Este controlador se encarga de llenar la base de datos de los movimientos de los administradores, para luego
 * ser usado y mostrarlo en su respectiva clase.
 *
 * @version 2.28.3
 * @author Manuel Antias.
 */

public class ControladorLlenarRegistroActividarAdministrador {
    /**
     * Este atributo crea un objeto del tipo {@linkplain ControladorBaseDatosClientes} que es una instancia de
     * dicha clase.
     */
    ControladorBaseDatosClientes controladorCliente= ControladorBaseDatosClientes.getInstanciaCliente();
    /**
     * Este atributo guarda un objeto del tipo {@linkplain TransaccionesAdministradores} para poder hacer uso de sus
     * atributos.
     */
    private TransaccionesAdministradores trasaccionAdmi;
    /**
     * Este atributo guarda una lista de objetos del tipo {@linkplain TransaccionesAdministradores}.
     */
    private List<TransaccionesAdministradores> trasaccionesAdmi=new ArrayList<>();
    /**
     * Este atributo guarda una fecha del tipo LocalDate.
     */
    private LocalDate fechaInicio;
    /**
     * Este atributo guarda la misma fecha LocalDate pero en String y con formato día/mes/año.
     */
    private String fecha;
    /**
     * Este atributo guarda una lista Mapa con una compilación de objetos del tipo {@linkplain Cliente}
     */
    private Map<String, Cliente> clientes = new LinkedHashMap<>();
    /**
     * Este atributo guarda un número de teléfono que es recibido por parte del cliente.
     */
    private String numeroTelefonoCliente;
    /**
     * Guarda un objeto del tipo {@linkplain Cliente}
     */
    private Cliente cliente;

    private String rutaBaseDatos;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     * @param trasaccionAdmi se recibe un objeto del Tipo TransaccionAdmi por parámetro
     */
    public ControladorLlenarRegistroActividarAdministrador(TransaccionesAdministradores trasaccionAdmi) {
        this.trasaccionAdmi = trasaccionAdmi;
        this.fechaInicio=trasaccionAdmi.getFecha();
        fecha=fechaInicio.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        controladorCliente.cargarClientes();
        clientes=controladorCliente.getClientes();
        this.numeroTelefonoCliente=trasaccionAdmi.getNumeroTelefonoCliente();
        trasaccionesAdmi.add(trasaccionAdmi);

    }

    /**
     * Este método se encarga de sobreescribir cargando todos los movimientos que ha realizado el administrador durante
     * la ejecución completa del programa.
     */
    public void subirMovimientosAdmis(){
        try{

            rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/HistorialDeMovimientosAdministrador",
                    "src/main/java/Imagenes/ArchivoCorrupto.png","HistorialDeMovimientosAdministrador",
                    "ArchivoCorrupto.png");
            rutasDeEjecutable.rutas();
            rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
            rutaImagenes=rutasDeEjecutable.getRutaImagenes();

            FileWriter archivoMovimientos = new FileWriter(rutaBaseDatos,true);
            StringBuilder linea = new StringBuilder();
            String dato;
            cliente=clientes.get(numeroTelefonoCliente);
            System.out.println(numeroTelefonoCliente);
            System.out.println(cliente);
            if(cliente!=null){
                for (TransaccionesAdministradores transaccion : trasaccionesAdmi) {
                    dato=fecha+","+transaccion.getUserAdmi()+","+transaccion.getMovimientoAdmi()+
                    ","+cliente.getNombreCliente()+","+cliente.getApellidoCliente()+","+cliente.getTelefonoCliente();
                    linea.append(dato+"\r\n");
                    archivoMovimientos.write(linea.toString());
                    linea.setLength(0);
                }
                archivoMovimientos.close();
            }
            else {
                JOptionPane.showMessageDialog(null,"No se encontro el movimiento del usuario");
            }
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
