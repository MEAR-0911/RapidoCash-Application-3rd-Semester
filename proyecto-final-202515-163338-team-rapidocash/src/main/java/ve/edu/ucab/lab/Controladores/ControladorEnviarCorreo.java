package ve.edu.ucab.lab.Controladores;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Este controlador se encarga de definir las estructuras necesarias para que esta aplicación tenga la capacidad de
 * enviar correos a nuestros estimados administradores. Esto se pensó con el objeto de que los administradores pudiesen
 * recuperar su contraseña en dado caso de que se les olvidase y así evitar el bloqueo de sus cuentas y tuviesen que
 * ir con sus superiores, gracias al uso de un tokken de verificación. Para el correcto funcionamiento se tuvo que crear
 * la cuenta de correo el sistema y luego crear un código de acceso proporcionado por Gmail. Así mismo también se
 * definieron los puertos de ruta pertinentes para que el correo pueda llegar al correo del usuario.
 *
 * @version 2.28.6
 * @author Manuel Antias.
 */
public class ControladorEnviarCorreo {
    /**
     * Este atributo guarda un objeto del tipo {@linkplain ControladorMensajeDeCorreo} con el que se ejecutará
     * el método para que se genere el mensaje y el tokken de verificación de manera aleatoria.
     */
    private ControladorMensajeDeCorreo mensajeDeCorreo=new ControladorMensajeDeCorreo();
    /**
     * Este atributo nos guarda el Tokken que usaremos para validar el cambio de pin pertinente.
     */
    private String tokken;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     */
    public ControladorEnviarCorreo() {
        tokken = mensajeDeCorreo.getTokken();
    }

    public void archivito(String rutaimg,String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",rutaimg,"",png);
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Método que se encarga de Enviar el correo y hacer las conexiones con el puerto de red de Gmail.
     *
     * @param correo
     */
    public void enviarCorreo(String correo) {
        /**Correo del sistema*/
        String correoEnvia="sistemabancariorapidocash@gmail.com";
        String clave="gjsa trsn domk dkya";

        Properties envioCorreo = new Properties();

        /**Puertos para hacer conexión*/
        envioCorreo.put("mail.smtp.host", "smtp.gmail.com");
        envioCorreo.setProperty("mail.smtp.starttls.enable", "true");
        envioCorreo.put("mail.smtp.port", "587");
        envioCorreo.setProperty("mail.smtp.port", "587");
        envioCorreo.put("mail.smtp.user", correoEnvia);
        envioCorreo.setProperty("mail.smtp.auth", "true");

        Session sesion=Session.getDefaultInstance(envioCorreo);
        MimeMessage mail = new MimeMessage(sesion);

        try{
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mail.setSubject("Código de verificación del sistema bancario RapidoCash");
            mail.setText(mensajeDeCorreo.getMensaje());

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia,clave);
            transporte.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));

            archivito("src/main/java/Imagenes/correoEnviado.png","correoEnviado.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡El correo se ha enviado correctamente! " +
                            "<br><br>por favor revise su bandeja de entrada<br></p></html>",
                    "CORREO ENVIADO", JOptionPane.INFORMATION_MESSAGE,icono);
        }catch (Exception ex) {

            archivito("src/main/java/Imagenes/correoPerdido.png","correoPerdido.png");

            ImageIcon icono=new javax.swing.ImageIcon();
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Upps! parece que se ha generado un error<br> " +
                            "en el tráfico de red, por favor realice la operación más tarde.<br>disculpe.</p></html>",
                    "ERROR DE TRÁFICO", JOptionPane.INFORMATION_MESSAGE,icono);
        }

    }

    /**
     * Getter tokken.
     * @return tokken, retorna el valor del tokken para hacer las comparaciones en el cambio de pin.
     */
    public String getTokken() {
        return tokken;
    }
}
