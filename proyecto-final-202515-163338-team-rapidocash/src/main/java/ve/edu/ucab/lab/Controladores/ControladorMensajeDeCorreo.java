package ve.edu.ucab.lab.Controladores;


/**
 * Este controlador se encarga de redactar un mensaje automático y predeterminado para el correo que envía el sistema
 * a nuestros administradores. A su vez también genera un Tokken aleatorio para este mismo proceso.
 *
 * @version 2.28.4
 * @author Manuel Antias.
 */

public class ControladorMensajeDeCorreo {
    /**
     * Este atributo guarda el texto del mensaje predeterminado.
     */
    String mensaje;
    /**
     * Este atributo guarda el Tokken generado aleatoriamente.
     */
    String tokken;

    /**
     * Mensaje predeterminado enviado al Usuario por correo.
     */
    public ControladorMensajeDeCorreo() {
        tokkenRecuperacion();
        this.mensaje = "Hola, estimado usuario:\n\n" +
                "Hemos recibido una solicitud para acceder a tu cuenta Bancaria RapidoCash\n" +
                "a través de tu correo electrónico registrado en nuestro sistema.\n" +
                "tu token de verificación de identidad es:\n\n" +
                "                        "+"[ "+tokken+" ]\n\n" +
                "Si no has solicitado este código, puede que alguien esté intentando acceder\n" +
                "a tu cuenta bancaria. NO reenvíes este correo electrónico ni des el código a\n" +
                "nadie.\n\n" +
                "Atentamente.\n\n" +
                "El equipo de cuentas bancarias RapidoCash";
    }

    /**
     * Método que se encarga de la creación del tokken de verificación.
     */
    public void tokkenRecuperacion(){
        int aleatorio=(int) (Math.random()*899999+100000);
        tokken=String.valueOf(aleatorio);
    }

    /**
     * Getter mensaje.
     * @return mensaje, retornamos el valor del mensaje para poder ser usado en el envío de correo.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Getter tokken.
     * @return tokken, retornamos el tokken de verificación para poder ser usado en envío del correo y verificación
     * de cambio de pin {@linkplain ControladorPinAdmiCambiado}.
     */
    public String getTokken() {
        return tokken;
    }
}
