package ve.edu.ucab.lab.Controladores;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * En esta clase controladora de datos numéricos correctos el código se enfoca en usar las expresiones regulares
 * para poder confirmar que los datos numéricos ingresados como el teléfono, el pin y/o la cédula de identidad
 * cumplan con una estructura necesaria para el correcto funcionamiento de la aplicación, así nos evitamos el ingreso
 * incorrecto de datos como entradas vacías, carácteres incorrectos o demás de los requeridos. A su vez se adaptó
 * este código de manera general para el correcto uso de la reutilización, pudiendo ser usada en cualquier campo
 * de entrada numérica.
 *
 * @author Antias Manuel
 * @version 2.28.6
 */

public class ControladorSaldoCorrecto{
    /**
     * Expresión regular utilizada para validar el saldo ingresado.
     */
    private String estructuraRegular;
    /**
     * El monto del saldo ingresado que será validado.
     */
    private String monto;
    /**
     * Indicador booleano que refleja si el dato ingresado es correcto según la validación.
     */
    private boolean datoCorrecto;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor que inicializa el monto a validar y configura la expresión regular para la validación.
     *
     * @param monto El monto a validar como saldo, que debe cumplir con el formato definido.
     */

    public ControladorSaldoCorrecto(String monto) {
        this.monto = monto;
        datoCorrecto = false;
        estructuraRegular = "^((1000)|([1-9]\\d{0,2})(\\.\\d{1,2})?)$";
    }

    /**
     * Método que valida si el monto ingresado cumple con la estructura correcta utilizando una expresión regular.
     * Si el monto es incorrecto, muestra un mensaje de error al usuario.
     *
     * @return true si el monto es válido, false si no lo es.
     */

    public boolean confirmarSaldo() {
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",
                "src/main/java/Imagenes/Error.png","","Error.png");
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();

        Pattern patron = Pattern.compile(estructuraRegular);
        Matcher coincidencia = patron.matcher(monto);
        datoCorrecto = coincidencia.matches();
        if (datoCorrecto==false) {
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\"><b>¡Uups parece que cometió un error!</b><br><br>" +
                            "Recuerde solo ingresar <b>números</b> y que la <b>cantidad máxima</b><br> de saldo para su operación " +
                            "es de 1000 bs. A su vez solamente<br> puede usar <b>dos dígitos decimales (.00)</b></p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
        }
        return datoCorrecto;
    }

    /**
     * Método que retorna si el monto ingresado es válido o no.
     *
     * @return true si el monto es válido, false si no lo es.
     */

    public boolean errorMonto() {
        return datoCorrecto;
    }
}
