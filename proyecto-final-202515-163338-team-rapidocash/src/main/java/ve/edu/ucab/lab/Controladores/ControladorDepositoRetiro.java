package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;

/**
 * Este controlador se enfoca en realizar las operaciones lógicas del depósito y retiro (Suma y resta de decimales)
 *
 * @version 2.28.3
 * @author Manuel Antias.
 */

public class ControladorDepositoRetiro {
    /**
     * Este atributo del tipo {@linkplain Cliente} guarda un objeto del tipo Cliente.
     */
    private Cliente cliente;
    /**
     * Este atributo guarda el monto de la operación a sumar o restar de las cantidades del saldo.
     */
    private double monto;
    /**
     * Este atributo guarda si la operación se llevó a cabo con éxito.
     */
    private boolean montoEfectivo;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Controlador de la clase.
     * @param cliente el objeto cliente que se recibe para poder modificar el saldo del cliente.
     * @param monto el monto con las que se harán las operaciones lógicas.
     */
    public ControladorDepositoRetiro(Cliente cliente, double monto) {
        this.cliente = cliente;
        this.monto = monto;
        montoEfectivo = false;
    }

    /**
     * Este método se encarga de hacer el depósito pertinente del dinero, haciendo una suma del monto actual del cliente
     * a su vez, se procura de que el monto ingresado no tenga ninguna particularidad que afecte al programa. También
     * se verifica que el monto a depositar no sea mayor a 1000 unidades.
     */
    public void deposito(){

        archivito("src/main/java/Imagenes/OperacionRealizadaConExito.png","OperacionRealizadaConExito.png");

        cliente.getCuenta().setSaldoCliente(cliente.getCuenta().getSaldoCliente()+monto);
        ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
        Image img = icono.getImage();
        Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        icono = new ImageIcon(scaledImage);
        JOptionPane.showMessageDialog(null,
                "<html><p style=\"color:black;font-size:15px;\"><b>¡Su deposito se ha llevado a cabo " +
                        "exitósamente!</b></p></html>", "OPERACIÓN EXITOSA", JOptionPane.INFORMATION_MESSAGE, icono);
    }

    public void archivito(String rutaimg,String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",rutaimg,"",png);
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Este método se encarga de hacer el retiro pertinente del dinero, haciendo una resta del monto actual del cliente
     * a su vez, se procura de que el monto ingresado no tenga ninguna particularidad que afecte al programa. También
     * se verifica que el monto a retirar no sea mayor a 1000 unidades. En dado caso de que el monto a retirar sea
     * mayor al monto actual del cliente en la cuenta, se dará un error.
     *
     * @return montoEfectivo, retorna si la operación se llevó a cabo de manera efectiva, para definir si se llama
     * a la ventana encargada de verificar el ID y hacer efectiva la operación.
     */
    public boolean retiro(){
        if (monto > cliente.getCuenta().getSaldoCliente()){

            archivito("src/main/java/Imagenes/fondos-insuficientes.png","fondos-insuficientes.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\"><b>¡Uups parece que cometió un error!</b><br><br>" +
                            "El monto a retirar <b>es superior al saldo de la cuenta</b>. Por favor ingrese el monto<br>" +
                            "nuevamente.</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
        } else if (monto <= cliente.getCuenta().getSaldoCliente()) {
            cliente.getCuenta().setSaldoCliente(cliente.getCuenta().getSaldoCliente()-monto);
            montoEfectivo = true;
        }
        return montoEfectivo;
    }

    /**
     * Getter montoEfectivo.
     * @return montoEfectivo, retorna si la operación se llevó a cabo de manera efectiva (true/false)
     */
    public boolean montoEfectivo() {
        return montoEfectivo;
    }
}