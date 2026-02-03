package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;

/**
 * Este controlador se encarga de verificar que el ID ingresado por el cliente cumpla con una expresión regular
 * establecida, asegurándonos así que únicamente se ingresen correctamente los datos y no generen errores en la ejecución
 * del programa. Y a su vez hace la validación para que la operación se haga efectiva.
 *
 * @version 2.28.4
 * @author Manuel Antias.
 */

public class ControladorEntradaID {
    /**
     * Este atributo del tipo {@linkplain Cliente} guarda un objeto del tipo cliente para así poder acceder a la cédula
     * del cliente.
     */
    private Cliente cliente;
    /**
     * Este atributo guarda los dígitos de la cédula de identidad ingresada por el cliente.
     */
    private String digitosCiIngresados;
    /**
     * Este atributo guarda los dígitos de la cédula de identidad del cliente registrado.
     */
    private String digitosCiCliente;
    /**
     * Este atributo confirma si la operación llevada a cabo se llevó realizó correctamente.
     */
    private boolean datoCorrecto;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     *
     * @param cliente Objeto cliente que se recibe.
     * @param digitosCiIngresados últimos dos dígitos de la cédula del cliente ingresados.
     */
    public ControladorEntradaID(Cliente cliente, String digitosCiIngresados) {
        this.cliente = cliente;
        this.digitosCiIngresados = digitosCiIngresados;
        /**Esta expresión y el ".replaceAll" nos ayuda a hacer un manejo de cadenas y quedarnos solamente con los
         * dos últimos dígitos de la cédula del cliente registrado*/
        digitosCiCliente = cliente.getCiCliente().replaceAll("^\\d{6}", "");
        datoCorrecto=false;
    }

    public void archivito(String rutaimg,String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",rutaimg,"",png);
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Este método se encarga de verificar si los dos últimos dos dígitos ingresados por el cliente, coinciden
     * con los dos últimos de su cédula registrada, en dando caso de que no se cumpla esta condición entonces se le
     * informará mediante un mensaje de error.
     *
     * @return datoCorrecto, retorna el valor true si toda la operación se llevó a cabo de manera correcta.
     */
    public boolean confirmarCi(){
        if (digitosCiIngresados.equals(digitosCiCliente)) {

            archivito("src/main/java/Imagenes/OperacionRealizadaConExito.png","OperacionRealizadaConExito.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\"><b>¡Su cédula ha sido verificada " +
                            "correctamente!</b></p></html>", "OPERACIÓN EXITOSA", JOptionPane.INFORMATION_MESSAGE, icono);
            datoCorrecto=true;
        }
        else {

            archivito("src/main/java/Imagenes/error-de-base-de-datos.png","error-de-base-de-datos.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\"><b>¡Uups parace que cometió un error!</b><br><br>" +
                            "Los dígitos de la cédula ingresa <b>no coinciden</b><br>con la registrada en la base de datos<br>" +
                            "por favor <b>ingrésela correctamente.</b></p></html>", "FATAL ERROR",
                    JOptionPane.INFORMATION_MESSAGE, icono);
        }
        return datoCorrecto;
    }

    /**
     * Getter datoCorrecto.
     * @return datoCorrecto, retorna el valor de la efectividad del programa para la correcta llamada en la venta. Más
     * específicamente para definir si se llama a la ventana final o no.
     */
    public boolean cedulaCorrecta() {
        return datoCorrecto;
    }
}
