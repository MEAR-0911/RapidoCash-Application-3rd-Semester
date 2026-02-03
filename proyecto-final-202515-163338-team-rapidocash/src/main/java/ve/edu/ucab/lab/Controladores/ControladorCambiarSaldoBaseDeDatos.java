package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Este Controlador se encarga de subir a la base de datos el saldo del cliente tras todas las operaciones realizadas
 * en el sistema, para evitar sobrecargar el programa con subidas de datos, se decidió ejecutar dicha clase y sus
 * métodos únicamente en los botones de salir del sistema.
 *
 * @version 2.28.3
 * @author Manuel Antias.
 */

public class ControladorCambiarSaldoBaseDeDatos {
    /**
     * Atributo cliente, se guarda un objeto del tipo {@linkplain Cliente}.
     */
    private Cliente cliente;
    /**
     * Atributo clienteCambio, se guarda otro objeto del tipo {@linkplain Cliente}, que será usado para poder cambiar el
     * valor del saldo del cliente.
     */
    private Cliente clienteCambio;
    /**
     * Atributo controladorClientes, se guarda un objeto de la instancia de la clase
     * {@linkplain ControladorBaseDatosClientes}.
     */
    private ControladorBaseDatosClientes controladorCliente= ControladorBaseDatosClientes.getInstanciaCliente();
    /**
     * Atributo clientes, guarda una lista Map de objetos del tipo {@linkplain Cliente}.
     */
    private Map<String, Cliente> clientes = new LinkedHashMap<>();

    private String rutaImagenes;

    private String rutaBaseDatos;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     * @param cliente es el objeto de tipo cliente que se pasará para poder acceder al número de teléfono del
     *cliente con el que se está trabajando y asociarlo a la base de datos, haciendo luego el cambio efectivo.
     */
    public ControladorCambiarSaldoBaseDeDatos(Cliente cliente) {
        this.cliente = cliente;
        clientes=controladorCliente.getClientes();
    }

    /**
     * En este método primero sacamos el número de teléfono del objeto cliente con el que estamos trabajando (el cliente
     *  que accedió a su cuenta en el sistema), si la persona existe, entonces reemplazamos el monto registrado en el
     *  clienteCambio para luego con ese objeto comenzar a cargar al txt dicho cliente con el saldo modificado, sin
     *  cambiar la posición en la que se encuentra, en vista de que sobreescribimos el txt, con todos los clientes.
     */
    public void cagarNuevosDatos(){

        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes",
                "src/main/java/Imagenes/ArchivoCorrupto.png","DatosClientes","ArchivoCorrupto.png");
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();

        clienteCambio=clientes.get(cliente.getTelefonoCliente());
        clienteCambio.getCuenta().setSaldoCliente(clienteCambio.getCuenta().getSaldoCliente());
        if(clienteCambio!=null){
            try {
                FileWriter archivo = new FileWriter(rutaBaseDatos);
                /**
                 * Se crea este StringBuilder como recipiente de toda los datos que se ingresarán en la BD.
                 */
                StringBuilder linea = new StringBuilder();
                String datos;
                for (Cliente clienteCambio : clientes.values()) {
                    datos = clienteCambio.getPin()+ "," + clienteCambio.getNombreCliente() + "," +
                            clienteCambio.getApellidoCliente() + "," +
                            clienteCambio.getCiCliente() + "," + clienteCambio.getTelefonoCliente() + "," +
                            clienteCambio.getCorreoCliente() +
                            "," + clienteCambio.getCuenta().getTipoCuentaCliente() + "," +
                            clienteCambio.getCuenta().getCuentaCliente() + "," +
                            clienteCambio.getCuenta().getSaldoCliente();
                    linea.append(datos + "\n");
                    /**
                     * Convertimos el StringBuilder en una línea de texto aceptable para poder ser escrita
                     * en la BD.
                     */
                    archivo.write(linea.toString());
                    /**
                     * Sirve para vaciar nuestro recipiente de datos (StringBuilder) y no concatenar
                     * erróneamente cosas de más.
                     */
                    linea.setLength(0);
                }
                archivo.close();
                controladorCliente.cargarClientes();
            }catch (IOException e) {
                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\"><b>¡Archivo no encontrado o Corrupto!</b></p></html>",
                        "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
            }
        }
    }
}
